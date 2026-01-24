package com.astroganit.api.serviceImpl;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import com.astroganit.api.entities.Payment;
import com.astroganit.api.entities.User;
import com.astroganit.api.entities.UserSubscription;
import com.astroganit.api.model.CreateOrderRequest;
import com.astroganit.api.model.NotesDto;
import com.astroganit.api.model.RazorpayOrderDto;
import com.astroganit.api.payload.PaymentStatus;
import com.astroganit.api.repository.PaymentRepository;
import com.astroganit.api.repository.SubscriptionRepository;
import com.astroganit.api.repository.UserRepo;
import com.astroganit.lib.panchang.util.AppEnums;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

import jakarta.transaction.Transactional;

@Service
public class PaymentService {
	private final SubscriptionRepository subscriptionRepository;
	private final PaymentRepository paymentRepository;
	private final UserRepo userRepository;
	@Value("${razorpay.key_id}")
	private String razorpayKey;

	@Value("${razorpay.key_secret}")
	private String razorpaySecret;

	public PaymentService(PaymentRepository paymentRepository, SubscriptionRepository subscriptionRepository,
			UserRepo userRepository) {
		this.paymentRepository = paymentRepository;
		this.subscriptionRepository = subscriptionRepository;
		this.userRepository = userRepository;
	}

	public RazorpayOrderDto createOrderSafely(CreateOrderRequest request) throws Exception {
		// 1️⃣ Validate input
		if (request == null) {
			throw new Exception("Invalid request");
		}
		Long userId = request.getUserId();
		if (userId <= 0) {
			userId = userRepository.findUserIdByLoginId(request.getLoginId());
			if (userId == null) {
				throw new RuntimeException("User not found");
			}
		}

		if (request.getAmount() <= 0) {
			throw new Exception("Invalid amount");
		}
		if (request.getCurrency() == null || request.getCurrency().isBlank()) {
			throw new Exception("Invalid currency");
		}

		// 2️⃣ Generate idempotency reference
		String referenceId = UUID.randomUUID().toString();

		RazorpayClient client;
		try {
			client = new RazorpayClient(razorpayKey, razorpaySecret);
		} catch (RazorpayException e) {
			throw new Exception("Payment gateway initialization failed", e);
		}

		try {
			// 3️⃣ Build Razorpay order request
			JSONObject orderRequest = new JSONObject();
			orderRequest.put("amount", request.getAmount() * 100); // paise
			orderRequest.put("currency", request.getCurrency());
			orderRequest.put("payment_capture", 1);
			orderRequest.put("receipt", referenceId);

			JSONObject notes = new JSONObject();
			notes.put("userId", userId);
			notes.put("paymentFor", request.getPaymentFor());
			orderRequest.put("notes", notes);

			// 4️⃣ Create Razorpay order (can fail)
			Order order = client.orders.create(orderRequest);

			// 5️⃣ Save payment (can fail)
			Payment payment = new Payment();
			payment.setUserId(userId);
			payment.setAmount(request.getAmount());
			payment.setCurrency(request.getCurrency());
			payment.setPaymentFor(request.getPaymentFor());
			payment.setReferenceId(referenceId);
			payment.setOrderId(order.get("id"));
			payment.setStatus(PaymentStatus.CREATED.name());
			payment.setPaymentMethod("RAZORPAY");
			payment.setPlanId(request.getPlanId());
			payment.setDurationDays(request.getDurationDays());
			payment.setGateway("RAZORPAY");
			payment.setSignatureVerified(false);
			paymentRepository.save(payment);

			// 6️⃣ Return minimal safe response
			return mapToDto(order);

		} catch (RazorpayException ex) {
			throw new Exception("Unable to create payment order. Please try again.");
		} catch (DataAccessException ex) {
			throw new Exception("Payment initialization failed. Please retry.");
		} catch (Exception ex) {
			throw new Exception("Unexpected error occurred");
		}
	}

	@Transactional
	public UserSubscription finalizePayment(String orderId, String paymentId) {

		Payment payment = paymentRepository.findByOrderId(orderId)
				.orElseThrow(() -> new RuntimeException("Payment not found"));

		// 🔐 Idempotency: already processed
		if (PaymentStatus.SUCCESS.name().equals(payment.getStatus())) {
			// Return existing subscription instead of null
			return subscriptionRepository.findByPaymentId(payment.getPaymentId())
					.orElseThrow(() -> new RuntimeException("Subscription not found for successful payment"));
		}

		// ❌ Do not revive failed payments
		if (PaymentStatus.FAILED.name().equals(payment.getStatus())) {
			throw new RuntimeException("Payment already failed");
		}

		// 🔍 Validate paymentId mismatch (only if already set)
		if (payment.getPaymentId() != null && !payment.getPaymentId().equals(paymentId)) {
			throw new RuntimeException("Payment ID mismatch");
		}

		// 1️⃣ Mark payment SUCCESS (state first)
		payment.setSignatureVerified(true);
		payment.setStatus(PaymentStatus.SUCCESS.name());
		payment.setPaymentId(paymentId);
		paymentRepository.save(payment);

		// 2️⃣ Activate subscription (idempotent)
		return activateSubscription(payment.getUserId(), payment.getPlanId(), payment.getDurationDays(), paymentId);
	}

	@Transactional
	public UserSubscription activateSubscription(long userId, int planId, int durationDays, String paymentId) {
		Optional<UserSubscription> existing = subscriptionRepository.findByUserId(userId);
		LocalDateTime start = LocalDateTime.now();

		try {
			if (existing.isPresent()) {
				UserSubscription sub = existing.get();
				sub.setPlanId(planId);
				sub.setStartDate(start);
				sub.setEndDate(start.plusDays(durationDays));
				sub.setPaymentId(paymentId);
				sub.setStatus(AppEnums.SubscriptionStatus.ACTIVE.name());
				return subscriptionRepository.save(sub); // ✅ UPDATE
			} else {
				UserSubscription subscription = new UserSubscription();
				subscription.setUserId(userId);
				subscription.setPlanId(planId);
				subscription.setStartDate(start);
				subscription.setEndDate(start.plusDays(durationDays));
				subscription.setStatus(AppEnums.SubscriptionStatus.ACTIVE.name());
				subscription.setPaymentId(paymentId);
				return subscriptionRepository.save(subscription);
			}

		} catch (DataIntegrityViolationException ex) {
			// another thread already created it
			return subscriptionRepository.findByPaymentId(paymentId).orElseThrow(() -> ex);
		}
	}

	private RazorpayOrderDto mapToDto(Order order) {

		RazorpayOrderDto dto = new RazorpayOrderDto();

		dto.setId(getString(order, "id"));
		dto.setEntity(getString(order, "entity"));
		dto.setAmount(getInt(order, "amount"));
		dto.setAmountPaid(getInt(order, "amount_paid"));
		dto.setAmountDue(getInt(order, "amount_due"));
		dto.setCurrency(getString(order, "currency"));
		dto.setReceipt(getString(order, "receipt"));
		dto.setStatus(getString(order, "status"));
		dto.setAttempts(getInt(order, "attempts"));
		dto.setOfferId(getString(order, "offer_id"));

		// created_at (Date → Long)
		dto.setCreatedAt(getLongFromDate(order, "created_at"));

		// notes
		JSONObject notesJson = getObject(order, "notes");
		if (notesJson != null) {
			NotesDto notes = new NotesDto();
			notes.setUserId(
					notesJson.has("userId") && !notesJson.isNull("userId") ? notesJson.getLong("userId") : null);
			notes.setPaymentFor(notesJson.optString("paymentFor", null));
			dto.setNotes(notes);
		}

		return dto;
	}

	private String getString(Order order, String key) {
		Object value = order.get(key);
		return value == JSONObject.NULL || value == null ? null : value.toString();
	}

	private Integer getInt(Order order, String key) {
		Object value = order.get(key);
		return value == JSONObject.NULL || value == null ? null : ((Number) value).intValue();
	}

	private Long getLongFromDate(Order order, String key) {
		Object value = order.get(key);
		if (value == JSONObject.NULL || value == null)
			return null;
		if (value instanceof Date date) {
			return date.getTime();
		}
		return null;
	}

	private JSONObject getObject(Order order, String key) {
		Object value = order.get(key);
		return value instanceof JSONObject ? (JSONObject) value : null;
	}

}
