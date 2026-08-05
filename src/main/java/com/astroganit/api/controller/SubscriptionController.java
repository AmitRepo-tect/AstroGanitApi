package com.astroganit.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.astroganit.api.entities.UserSubscription;
import com.astroganit.api.serviceImpl.SubscriptionService;
import com.astroganit.api.util.ResultCode;
import com.astroganit.api.payload.ResponseNew;
import com.astroganit.api.response.VerifyPaymentResponse;

@RestController
@RequestMapping("/api/subscription")
public class SubscriptionController {

	private final SubscriptionService subscriptionService;

	public SubscriptionController(SubscriptionService subscriptionService) {
		this.subscriptionService = subscriptionService;
	}

	@GetMapping("/active")
	public ResponseEntity<ResponseNew<VerifyPaymentResponse>> getActiveSubscription() {

		VerifyPaymentResponse subscription = subscriptionService.getActiveSubscription();
		ResponseNew<VerifyPaymentResponse> response = new ResponseNew<>();
		response.setStatus(HttpStatus.OK);
		response.setStatusCode(HttpStatus.OK.value());
		response.setResultCode(ResultCode.SUCCESS.getCode());
		response.setMessage(ResultCode.SUCCESS.getMessage());
		response.setErrorMessage("");
		response.setData(subscription);
		return ResponseEntity.ok(response);
	}

}
