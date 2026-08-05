package com.astroganit.api.payload;

public class LoginOtpResponse {
	private boolean otpSent;
	private String loginId;
	private String mobileCC;

	public boolean isOtpSent() {
		return otpSent;
	}

	public void setOtpSent(boolean otpSent) {
		this.otpSent = otpSent;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getMobileCC() {
		return mobileCC;
	}

	public void setMobileCC(String mobileCC) {
		this.mobileCC = mobileCC;
	}
	

}