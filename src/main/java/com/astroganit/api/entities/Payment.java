package com.astroganit.api.entities;

import java.time.LocalDateTime;
import jakarta.persistence.*;

@Entity
@Table(name = "payments")
public class Payment {

	/* ========= PRIMARY KEY ========= */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id; // DB primary key

	/* ========= RAZORPAY IDS ========= */
	@Column(name = "order_id", length = 100, unique = true)
	private String orderId;

	@Column(name = "payment_id", length = 100, unique = true)
	private String paymentId; // Razorpay payment ID (after success)

	/* ========= BUSINESS DATA ========= */
	@Column(name = "user_id", nullable = false)
	private long userId;

	@Column(name = "plan_id", nullable = false)
	private int planId;

	@Column(name = "duration_days", nullable = false)
	private int durationDays;

	@Column(nullable = false)
	private double amount;

	@Column(length = 10, nullable = false)
	private String currency;

	@Column(length = 50, nullable = false)
	private String status; // CREATED, SUCCESS, FAILED

	@Column(name = "payment_method", length = 50)
	private String paymentMethod;

	@Column(name = "payment_for", length = 255)
	private String paymentFor;

	/* ========= TRACKING ========= */
	@Column(name = "reference_id", length = 255, unique = true)
	private String referenceId;

	@Column(length = 50)
	private String gateway; // RAZORPAY

	@Column(name = "failure_reason", length = 255)
	private String failureReason;

	@Column(name = "signature_verified")
	private boolean signatureVerified = false;

	/* ========= AUDIT ========= */
	@Column(name = "create_date", updatable = false)
	private LocalDateTime createDate;

	@Column(name = "update_date")
	private LocalDateTime updateDate;

	/* ========= LIFECYCLE ========= */
	@PrePersist
	protected void onCreate() {
		this.createDate = LocalDateTime.now();
		this.updateDate = LocalDateTime.now();
	}

	@PreUpdate
	protected void onUpdate() {
		this.updateDate = LocalDateTime.now();
	}

	/* ========= GETTERS & SETTERS ========= */
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public String getPaymentFor() {
		return paymentFor;
	}

	public void setPaymentFor(String paymentFor) {
		this.paymentFor = paymentFor;
	}

	public String getReferenceId() {
		return referenceId;
	}

	public void setReferenceId(String referenceId) {
		this.referenceId = referenceId;
	}

	public String getGateway() {
		return gateway;
	}

	public void setGateway(String gateway) {
		this.gateway = gateway;
	}

	public String getFailureReason() {
		return failureReason;
	}

	public void setFailureReason(String failureReason) {
		this.failureReason = failureReason;
	}

	public boolean getSignatureVerified() {
		return signatureVerified;
	}

	public void setSignatureVerified(boolean signatureVerified) {
		this.signatureVerified = signatureVerified;
	}

	public LocalDateTime getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}

	public LocalDateTime getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(LocalDateTime updateDate) {
		this.updateDate = updateDate;
	}
}
