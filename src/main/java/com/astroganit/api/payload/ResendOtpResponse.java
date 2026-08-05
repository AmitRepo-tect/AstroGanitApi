package com.astroganit.api.payload;

public class ResendOtpResponse {

	private boolean otpSent;
	private int remainingAttempts;
	private long retryAfterSeconds;
	private String messsage;

	public boolean isOtpSent() {
		return otpSent;
	}

	public void setOtpSent(boolean otpSent) {
		this.otpSent = otpSent;
	}

	public int getRemainingAttempts() {
		return remainingAttempts;
	}

	public void setRemainingAttempts(int remainingAttempts) {
		this.remainingAttempts = remainingAttempts;
	}

	public long getRetryAfterSeconds() {
		return retryAfterSeconds;
	}

	public void setRetryAfterSeconds(long retryAfterSeconds) {
		this.retryAfterSeconds = retryAfterSeconds;
	}

	public String getMesssage() {
		return messsage;
	}

	public void setMesssage(String messsage) {
		this.messsage = messsage;
	}

	// getters/setters

}