package com.astroganit.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.astroganit.api.entities.Plan;
import com.astroganit.api.entities.UserSubscription;
import com.astroganit.api.serviceImpl.SubscriptionService;
import com.astroganit.api.model.SubscriptionRequest;
import com.astroganit.api.payload.ResponseNew;
import java.util.List;

@RestController
@RequestMapping("/api/subscription")
public class SubscriptionController {

	private final SubscriptionService subscriptionService;

	public SubscriptionController(SubscriptionService subscriptionService) {
		this.subscriptionService = subscriptionService;
	}

	@GetMapping("/plans")
	public ResponseEntity<ResponseNew<List<Plan>>> getPlans() {
		return ResponseEntity.ok(subscriptionService.getActivePlans());
	}

	@PostMapping("/activate")
	public UserSubscription activate(@RequestBody SubscriptionRequest request) {
		return subscriptionService.activateSubscription(request.getUserId(), request.getPlanId(),
				request.getDurationDays(), request.getPaymentId());
	}

	@GetMapping("/getSubscription/{userId}/{loginId}")
	public ResponseEntity<ResponseNew<UserSubscription>> getActiveSubscription(@PathVariable long userId,
			@PathVariable String loginId) {
		return ResponseEntity.ok(subscriptionService.getActiveSubscription(userId, loginId));
	}

}
