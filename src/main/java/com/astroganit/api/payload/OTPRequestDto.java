package com.astroganit.api.payload;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class OTPRequestDto {

	@NotBlank(message = "Mobile number is required")
	@Pattern(regexp = "^[0-9]{10}$", message = "Invalid mobile number")
	private String mobile;

	@NotBlank(message = "OTP is required")
	@Pattern(regexp = "^[0-9]{4,6}$", message = "Invalid OTP")
	private String otp;

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}
}