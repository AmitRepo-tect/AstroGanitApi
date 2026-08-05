package com.astroganit.api.payload;

import com.astroganit.api.response.VerifyPaymentResponse;

public class ActivateUserResponse {

	private UserResponse user;

	private VerifyPaymentResponse subscription;

	public UserResponse getUser() {
		return user;
	}

	public void setUser(UserResponse user) {
		this.user = user;
	}

	public VerifyPaymentResponse getSubscription() {
		return subscription;
	}

	public void setSubscription(VerifyPaymentResponse subscription) {
		this.subscription = subscription;
	}

}