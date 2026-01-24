package com.astroganit.api.model;

public class RazorpayOrderDto {

	private String id;
	private String entity;
	private int amount;
	private int amountPaid;
	private int amountDue;
	private String currency;
	private String receipt;
	private String status;
	private int attempts;
	private Long createdAt;
	private String offerId;
	private NotesDto notes;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEntity() {
		return entity;
	}

	public void setEntity(String entity) {
		this.entity = entity;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getAmountPaid() {
		return amountPaid;
	}

	public void setAmountPaid(int amountPaid) {
		this.amountPaid = amountPaid;
	}

	public int getAmountDue() {
		return amountDue;
	}

	public void setAmountDue(int amountDue) {
		this.amountDue = amountDue;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getReceipt() {
		return receipt;
	}

	public void setReceipt(String receipt) {
		this.receipt = receipt;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getAttempts() {
		return attempts;
	}

	public void setAttempts(int attempts) {
		this.attempts = attempts;
	}

	public Long getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
	}

	public String getOfferId() {
		return offerId;
	}

	public void setOfferId(String offerId) {
		this.offerId = offerId;
	}

	public NotesDto getNotes() {
		return notes;
	}

	public void setNotes(NotesDto notes) {
		this.notes = notes;
	}

}
