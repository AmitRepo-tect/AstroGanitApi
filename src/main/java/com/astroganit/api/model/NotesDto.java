package com.astroganit.api.model;

public class NotesDto {
	private Long userId;
	private String paymentFor;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getPaymentFor() {
		return paymentFor;
	}

	public void setPaymentFor(String paymentFor) {
		this.paymentFor = paymentFor;
	}

}
