package com.astroganit.api.controller;

import org.springframework.web.bind.annotation.*;

import com.astroganit.api.entities.UserSubscription;
import com.astroganit.api.model.VerifyPaymentResponse;
import com.astroganit.api.serviceImpl.SubscriptionService;

import java.util.Optional;

@RestController
@RequestMapping("/api/subscription")
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    public SubscriptionController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @PostMapping("/activate")
    public VerifyPaymentResponse activate(@RequestBody SubscriptionRequest request) {
        return subscriptionService.activateSubscription(
                request.getUserId(),
                request.getPlanId(),
                request.getDurationDays(),
                request.getPaymentId()
        );
    }

    @GetMapping("/{userId}")
    public Optional<UserSubscription> getActiveSubscription(@PathVariable int userId) {
        return subscriptionService.getActiveSubscription(userId);
    }

    static class SubscriptionRequest {
        private long userId;
        private int planId;
        private int durationDays;
        private String paymentId;

        // getters & setters
        public long getUserId() { return userId; }
        public void setUserId(long userId) { this.userId = userId; }

        public int getPlanId() { return planId; }
        public void setPlanId(int planId) { this.planId = planId; }

        public int getDurationDays() { return durationDays; }
        public void setDurationDays(int durationDays) { this.durationDays = durationDays; }
        public String getPaymentId() { return paymentId; }
        public void setPaymentId(String paymentId) { this.paymentId = paymentId; }
    }
}
