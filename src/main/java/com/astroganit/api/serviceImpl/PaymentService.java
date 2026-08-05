package com.astroganit.api.serviceImpl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.astroganit.api.model.RazorpayOrderResponse;
import com.astroganit.api.payload.PaymentStatus;
import com.astroganit.api.repository.PaymentRepository;
import com.astroganit.api.repository.PlanRepository;
import com.astroganit.api.repository.SubscriptionRepository;
import com.astroganit.api.repository.UserRepo;
import com.astroganit.api.response.VerifyPaymentResponse;
import com.astroganit.api.service.BirthDetailService;
import com.astroganit.api.util.ResultCode;
import com.astroganit.lib.panchang.util.AppEnums;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PaymentService {
	private static final Logger log = LoggerFactory.getLogger(PaymentService.class);
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
	public RazorpayOrderResponse createOrderSafely(CreateOrderRequest request) {

		if (request == null) {
			throw new AppException(ResultCode.INVALID_REQUEST);
		}

		User user = getLoggedInUser();

		Plan plan = planRepository.findById(request.getPlanId())
				.orElseThrow(() -> new AppException(ResultCode.PLAN_NOT_FOUND));

		if (!Boolean.TRUE.equals(plan.getIsActive())) {
			throw new AppException(ResultCode.PLAN_NOT_AVAILABLE);
		}

		if (request.getAmount() == null) {
			throw new AppException(ResultCode.AMOUNT_IS_REQUIRED);
		}

		if (request.getAmount().compareTo(plan.getPrice()) != 0) {
			throw new AppException(ResultCode.INVALID_PLAN_AMOUNT);
		}

		if (request.getPaymentFor() == null || request.getPaymentFor().isBlank()) {
			throw new AppException(ResultCode.INVALID_REQUEST);
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
			return mapToResponse(order);
		} catch (RazorpayException e) {
			System.out.println(e.getMessage());
			log.error("Failed to create Razorpay order", e);
			throw new AppException(ResultCode.PAYMENT_GATEWAY_ERROR);
		} catch (DataAccessException e) {
			log.error("Failed to save payment", e);
			throw new AppException(ResultCode.PAYMENT_INITIALIZATION_FAILED);
		}
	}

	@Transactional
	public VerifyPaymentResponse finalizePayment(String orderId, String razorpayPaymentId) {

		Payment payment = paymentRepository.findByOrderId(orderId)
				.orElseThrow(() -> new AppException(ResultCode.PAYMENT_NOT_FOUND));

		// Already processed (idempotent)

		if (PaymentStatus.SUCCESS.name().equals(payment.getStatus())) {

			UserSubscription subscription = subscriptionRepository.findByPaymentId(payment.getId())
					.orElseThrow(() -> new AppException(ResultCode.SUBSCRIPTION_NOT_FOUND));

			return mapToVerifyResponse(subscription);
		}

		// Already failed
		if (PaymentStatus.FAILED.name().equals(payment.getStatus())) {
			throw new AppException(ResultCode.PAYMENT_ALREADY_FAILED);
		}

		// Prevent payment id tampering
		if (payment.getPaymentId() != null && !payment.getPaymentId().equals(razorpayPaymentId)) {
			throw new AppException(ResultCode.INVALID_PAYMENT);
		}

		payment.setSignatureVerified(true);
		payment.setStatus(PaymentStatus.SUCCESS.name());
		payment.setPaymentId(razorpayPaymentId);
		payment.setPaymentDate(LocalDateTime.now());

		paymentRepository.save(payment);
		UserSubscription userSubscription = activateSubscription(payment.getUserId(), payment.getPlanId(),
				payment.getDurationDays(), payment.getId(), // DB payment id
				payment.getAmount());
		return mapToVerifyResponse(userSubscription);
	}

	@Transactional
	public UserSubscription activateSubscription(long userId, int planId, int durationDays, Long paymentDbId,
			BigDecimal amountPaid) {

		Optional<UserSubscription> existing = subscriptionRepository.findByUserId(userId);

		LocalDateTime start = LocalDateTime.now();
		LocalDateTime end = start.plusDays(durationDays);

		try {

			UserSubscription subscription;

			if (existing.isPresent()) {

				subscription = existing.get();

			} else {

				subscription = new UserSubscription();
				subscription.setUserId(userId);

			}

			subscription.setPlanId(planId);
			subscription.setPaymentId(paymentDbId);
			subscription.setAmountPaid(amountPaid);

			subscription.setStartDate(start);
			subscription.setEndDate(end);
			subscription.setRenewalDate(end);

			subscription.setStatus(AppEnums.SubscriptionStatus.ACTIVE.name());
			subscription.setAutoRenew(false);

			return subscriptionRepository.save(subscription);

		} catch (DataIntegrityViolationException ex) {

			return subscriptionRepository.findByPaymentId(paymentDbId).orElseThrow(() -> ex);
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

	private RazorpayOrderResponse mapToResponse(Order order) {

		RazorpayOrderResponse response = new RazorpayOrderResponse();
		response.setId(getString(order, "id"));
		response.setAmount(getInt(order, "amount"));
		response.setCurrency(getString(order, "currency"));

		return response;
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

	private VerifyPaymentResponse mapToVerifyResponse(UserSubscription subscription) {

		VerifyPaymentResponse response = new VerifyPaymentResponse();
		if (subscription != null) {
			response.setSubscriptionId(subscription.getId());
			response.setPlanId(subscription.getPlanId());
			Plan plan = planRepository.findById(subscription.getPlanId()).orElse(null);
			response.setPlanName(plan != null ? plan.getNameEn() : "");
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			response.setStartDate(subscription.getStartDate().format(formatter));
			response.setEndDate(subscription.getEndDate().format(formatter));
			response.setStatus(subscription.getStatus());
			response.setAutoRenew(Boolean.TRUE.equals(subscription.getAutoRenew()));
			response.setHasSubscription(true);
		} else {
			response.setHasSubscription(false);
		}
		return response;
	}

}
