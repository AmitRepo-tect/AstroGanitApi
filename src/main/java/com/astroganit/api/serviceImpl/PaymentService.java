package com.astroganit.api.serviceImpl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import com.astroganit.api.entities.Payment;
import com.astroganit.api.entities.Plan;
import com.astroganit.api.entities.User;
import com.astroganit.api.entities.UserSubscription;
import com.astroganit.api.exception.AppException;
import com.astroganit.api.model.CreateOrderRequest;
import com.astroganit.api.model.NotesDto;
import com.astroganit.api.model.RazorpayOrderDto;
import com.astroganit.api.payload.PaymentStatus;
import com.astroganit.api.repository.PaymentRepository;
import com.astroganit.api.repository.PlanRepository;
import com.astroganit.api.repository.SubscriptionRepository;
import com.astroganit.api.repository.UserRepo;
import com.astroganit.api.util.ResultCode;
import com.astroganit.lib.panchang.util.AppEnums;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PaymentService {
	private final SubscriptionRepository subscriptionRepository;
	private final PaymentRepository paymentRepository;
	private final UserRepo userRepository;
	private final PlanRepository planRepository;
	@Value("${razorpay.key_id}")
	private String razorpayKey;

	@Value("${razorpay.key_secret}")
	private String razorpaySecret;

	public PaymentService(PaymentRepository paymentRepository, SubscriptionRepository subscriptionRepository,
			UserRepo userRepository, PlanRepository planRepository) {
		this.paymentRepository = paymentRepository;
		this.subscriptionRepository = subscriptionRepository;
		this.userRepository = userRepository;
		this.planRepository = planRepository;
	}

	@Transactional(rollbackFor = Exception.class)
	public RazorpayOrderDto createOrderSafely(CreateOrderRequest request) throws Exception {

		if (request == null) {
			throw new IllegalArgumentException("Invalid request");
		}

		User user = getLoggedInUser();

		Plan plan = planRepository.findById(request.getPlanId())
				.orElseThrow(() -> new AppException(ResultCode.PLAN_NOT_FOUND));

		if (request.getAmount() == null) {
			throw new IllegalArgumentException("Amount is required");
		}

		if (request.getAmount().compareTo(plan.getPrice()) != 0) {
			throw new IllegalArgumentException("Invalid plan amount");
		}

		String referenceId = UUID.randomUUID().toString();

		try {

			RazorpayClient client = new RazorpayClient(razorpayKey, razorpaySecret);

			JSONObject orderRequest = new JSONObject();
			orderRequest.put("amount", plan.getPrice().multiply(BigDecimal.valueOf(100)).intValueExact());
			orderRequest.put("currency", plan.getCurrency());
			orderRequest.put("payment_capture", 1);
			orderRequest.put("receipt", referenceId);

			JSONObject notes = new JSONObject();
			notes.put("userId", user.getId());
			notes.put("planId", plan.getId());
			notes.put("paymentFor", request.getPaymentFor());

			orderRequest.put("notes", notes);

			Order order = client.orders.create(orderRequest);

			Payment payment = new Payment();
			payment.setUserId(user.getId());
			payment.setPlanId(plan.getId());
			payment.setAmount(plan.getPrice());
			payment.setCurrency(plan.getCurrency());
			payment.setDurationDays(plan.getDurationDays());
			payment.setPaymentFor(request.getPaymentFor());
			payment.setReferenceId(referenceId);
			payment.setOrderId(order.get("id"));
			payment.setGateway("RAZORPAY");
			payment.setPaymentMethod("RAZORPAY");
			payment.setStatus(PaymentStatus.CREATED.name());
			payment.setSignatureVerified(false);

			paymentRepository.save(payment);

			return mapToDto(order);

		} catch (RazorpayException e) {
			throw new Exception("Unable to create payment order. Please try again.", e);
		} catch (DataAccessException e) {
			throw new Exception("Payment initialization failed. Please retry.", e);
		}
	}

	@Transactional
	public UserSubscription finalizePayment(String orderId, String paymentId) {

		Payment payment = paymentRepository.findByOrderId(orderId)
				.orElseThrow(() -> new RuntimeException("Payment not found"));

		// 🔐 Idempotency: already processed
		if (PaymentStatus.SUCCESS.name().equals(payment.getStatus())) {
			// Return existing subscription instead of null
			return subscriptionRepository.findByPaymentId(Long.parseLong(payment.getPaymentId()))
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
				sub.setPaymentId(Long.parseLong(paymentId));
				sub.setStatus(AppEnums.SubscriptionStatus.ACTIVE.name());
				return subscriptionRepository.save(sub); // ✅ UPDATE
			} else {
				UserSubscription subscription = new UserSubscription();
				subscription.setUserId(userId);
				subscription.setPlanId(planId);
				subscription.setStartDate(start);
				subscription.setEndDate(start.plusDays(durationDays));
				subscription.setStatus(AppEnums.SubscriptionStatus.ACTIVE.name());
				subscription.setPaymentId(Long.parseLong(paymentId));
				return subscriptionRepository.save(subscription);
			}

		} catch (DataIntegrityViolationException ex) {
			// another thread already created it
			return subscriptionRepository.findByPaymentId(Long.parseLong(paymentId)).orElseThrow(() -> ex);
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

	private User getLoggedInUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		if (auth == null || !auth.isAuthenticated() || "anonymousUser".equals(auth.getName())) {
			throw new AppException(ResultCode.UNAUTHORIZED);
		}

		String loginId = auth.getName();
		// log.info("Logged in user: {}", loginId);

		return userRepository.findByLoginId(loginId).orElseThrow(() -> new AppException(ResultCode.USER_NOT_FOUND));
	}

}
