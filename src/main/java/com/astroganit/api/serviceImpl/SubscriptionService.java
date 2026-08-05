package com.astroganit.api.serviceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.astroganit.api.entities.Plan;
import com.astroganit.api.entities.User;
import com.astroganit.api.entities.UserSubscription;
import com.astroganit.api.exception.AppException;
import com.astroganit.api.repository.PlanRepository;
import com.astroganit.api.repository.SubscriptionRepository;
import com.astroganit.api.repository.UserRepo;
import com.astroganit.api.response.VerifyPaymentResponse;
import com.astroganit.api.util.ResultCode;
import com.astroganit.lib.panchang.util.AppEnums;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class SubscriptionService {

	private final SubscriptionRepository subscriptionRepository;
	private final UserRepo userRepository;
	private final PlanRepository planRepository;

	private static final Logger log = LoggerFactory.getLogger(SubscriptionService.class);

	public SubscriptionService(SubscriptionRepository subscriptionRepository, UserRepo userRepository,
			PlanRepository planRepository) {
		this.subscriptionRepository = subscriptionRepository;
		this.userRepository = userRepository;
		this.planRepository = planRepository;
	}

	@Transactional
	public VerifyPaymentResponse getActiveSubscription() {
		User user = getLoggedInUser();
		UserSubscription subscription = subscriptionRepository.findByUserId(user.getId()).orElse(null);

		if (subscription != null && subscription.getEndDate().isBefore(LocalDateTime.now())
				&& !AppEnums.SubscriptionStatus.EXPIRED.name().equals(subscription.getStatus())) {

			subscription.setStatus(AppEnums.SubscriptionStatus.EXPIRED.name());
			subscriptionRepository.save(subscription);
		}

		return mapToVerifyResponse(subscription);
	}

	@Transactional
	public VerifyPaymentResponse getActiveSubscription(User user) {

		UserSubscription subscription = subscriptionRepository.findByUserId(user.getId()).orElse(null);

		if (subscription != null && subscription.getEndDate().isBefore(LocalDateTime.now())
				&& !AppEnums.SubscriptionStatus.EXPIRED.name().equals(subscription.getStatus())) {

			subscription.setStatus(AppEnums.SubscriptionStatus.EXPIRED.name());
			subscriptionRepository.save(subscription);
		}
		System.out.println("verifyOtpV2-8");
		return mapToVerifyResponse(subscription);
	}

	// 🔐 Common method to get logged-in user
	private User getLoggedInUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		if (auth == null || !auth.isAuthenticated() || "anonymousUser".equals(auth.getName())) {
			throw new AppException(ResultCode.UNAUTHORIZED);
		}

		String loginId = auth.getName();
		log.info("Logged in user: {}", loginId);

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
