package com.astroganit.api.payload;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class LoginRequestDto {

	@NotBlank(message = "Login ID is required")
	private String loginId;

	@Pattern(regexp = "^[0-9]{10}$", message = "Invalid mobile number")
	private String mobile;

	@NotBlank(message = "Mobile country code is required")
	private String mobilecc;

	private String deviceId;

	private String appVersion;

	private String androidVersion;

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getMobilecc() {
		return mobilecc;
	}

	public void setMobilecc(String mobilecc) {
		this.mobilecc = mobilecc;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getAppVersion() {
		return appVersion;
	}

	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}

	public String getAndroidVersion() {
		return androidVersion;
	}

	public void setAndroidVersion(String androidVersion) {
		this.androidVersion = androidVersion;
	}

	// getters & setters

}