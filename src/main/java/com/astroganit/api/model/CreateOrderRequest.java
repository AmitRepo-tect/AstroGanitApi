package com.astroganit.api.model;

import java.io.Serializable;

public class CreateOrderRequest implements Serializable {
	private double amount;
	private String currency;
	private long userId;
	private String loginId;
	private int planId;
	private int durationDays;
	private String paymentFor;

	public CreateOrderRequest() {

	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getPaymentFor() {
		return paymentFor;
	}

	public void setPaymentFor(String paymentFor) {
		this.paymentFor = paymentFor;
	}

	public int getPlanId() {
		return planId;
	}

	public void setPlanId(int planId) {
		this.planId = planId;
	}

	public int getDurationDays() {
		return durationDays;
	}

	public void setDurationDays(int durationDays) {
		this.durationDays = durationDays;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

}
