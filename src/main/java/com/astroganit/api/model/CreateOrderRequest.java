package com.astroganit.api.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class CreateOrderRequest implements Serializable {
	
	private BigDecimal amount;
	private int planId;
	private String paymentFor;

	public CreateOrderRequest() {

	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public int getPlanId() {
		return planId;
	}

	public void setPlanId(int planId) {
		this.planId = planId;
	}

	public String getPaymentFor() {
		return paymentFor;
	}

	public void setPaymentFor(String paymentFor) {
		this.paymentFor = paymentFor;
	}

}
