package com.astroganit.api.payload;

import com.astroganit.api.response.VerifyPaymentResponse;

public class LoginResponse {

	private String accessToken;
	private UserResponse user;
	private VerifyPaymentResponse subscription; // optional

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

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