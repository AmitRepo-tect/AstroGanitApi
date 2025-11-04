package com.astroganit.api.serviceImpl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.astroganit.api.entities.Plan;
import com.astroganit.api.entities.User;
import com.astroganit.api.entities.UserSubscription;
import com.astroganit.api.repository.PlanRepository;
import com.astroganit.api.repository.SubscriptionRepository;
import com.astroganit.api.repository.UserRepo;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Transactional
public class SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;
    private final UserRepo userRepository;
    private final PlanRepository planRepository;

    public SubscriptionService(SubscriptionRepository subscriptionRepository,
                               UserRepo userRepository,
                               PlanRepository planRepository) {
        this.subscriptionRepository = subscriptionRepository;
        this.userRepository = userRepository;
        this.planRepository = planRepository;
    }

    public UserSubscription activateSubscription(int userId, int planId, int durationDays,String paymentId) {

        // ✅ 1. Fetch the user and plan entities
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        Plan plan = planRepository.findById(planId)
                .orElseThrow(() -> new RuntimeException("Plan not found with id: " + planId));

        // ✅ 2. Create a new subscription
        UserSubscription subscription = new UserSubscription();
        subscription.setUser(user);
        subscription.setPlan(plan);
        subscription.setStartDate(LocalDateTime.now());
        subscription.setEndDate(LocalDateTime.now().plusDays(durationDays));
        subscription.setStatus("active");
        subscription.setPaymentId(paymentId);
        // ✅ 3. Save and return
        return subscriptionRepository.save(subscription);
    }

    public Optional<UserSubscription> getActiveSubscription(int userId) {
        return subscriptionRepository.findByUserIdAndStatus(userId, "active");
    }
}
