package com.astroganit.api.serviceImpl;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.astroganit.api.entities.Plan;
import com.astroganit.api.entities.UserSubscription;
import com.astroganit.api.payload.ResponseNew;
import com.astroganit.api.repository.PlanRepository;
import com.astroganit.api.repository.SubscriptionRepository;
import com.astroganit.api.repository.UserRepo;
import com.astroganit.lib.panchang.util.AppResultConstant;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class SubscriptionService {

	private final SubscriptionRepository subscriptionRepository;
	private final PlanRepository planRepository;
	private final UserRepo userRepository;

	public SubscriptionService(SubscriptionRepository subscriptionRepository, PlanRepository planRepository,
			UserRepo userRepository) {
		this.subscriptionRepository = subscriptionRepository;
		this.planRepository = planRepository;
		this.userRepository = userRepository;
	}

	public UserSubscription activateSubscription(long userId, int planId, int durationDays, String paymentId) {

		// ✅ 2. Create a new subscription
		UserSubscription subscription = new UserSubscription();
		subscription.setUserId(userId);
		subscription.setPlanId(planId);
		subscription.setStartDate(LocalDateTime.now());
		subscription.setEndDate(LocalDateTime.now().plusDays(durationDays));
		subscription.setStatus("active");
		subscription.setPaymentId(paymentId);

		return subscriptionRepository.save(subscription);
	}

	@Transactional
	public ResponseNew<UserSubscription> getActiveSubscription(long id, String loginId) {

		ResponseNew<UserSubscription> response = new ResponseNew<UserSubscription>();
		response.setErrorMessage("");
		response.setStatus(HttpStatus.OK);
		try {
			Long userId = id;
			if (userId <= 0) {
				userId = userRepository.findUserIdByLoginId(loginId);
				if (userId == null) {
					throw new RuntimeException("User not found");
				}
			}

			UserSubscription userSubscription = subscriptionRepository.findByUserId(userId).orElse(null);
			if (userSubscription != null && userSubscription.getEndDate().isBefore(LocalDateTime.now())) {
				userSubscription.setStatus("EXPIRED");
				subscriptionRepository.save(userSubscription);
			}

			// UserSubscription userSubscription =
			// subscriptionRepository.findByUserIdAndStatus(userId, "active");
			if (userSubscription != null) {
				response.setResultCode(AppResultConstant.SUCCESSFUL);
				response.setMessage("Successfully");
				response.setData(userSubscription);
			} else {
				response.setResultCode(AppResultConstant.SUBSCRIPTION_NOT_FOUND);
				response.setMessage("Subscription not found.");
			}

		} catch (Exception e) {
			response.setResultCode(AppResultConstant.EXCEPTION);
			response.setErrorMessage(e.getMessage());
		}

		return response;
	}

	public ResponseNew<List<Plan>> getActivePlans() {
		ResponseNew<List<Plan>> response = new ResponseNew<List<Plan>>();
		response.setErrorMessage("");
		response.setStatus(HttpStatus.OK);
		response.setResultCode(AppResultConstant.SUCCESSFUL);
		response.setMessage("Successfully");
		response.setData(planRepository.findByIsActiveTrue());
		return response;
	}
}
