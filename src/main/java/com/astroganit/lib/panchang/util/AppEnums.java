package com.astroganit.lib.panchang.util;

public class AppEnums {

	// -------------------------
	// LOGIN STATUS ENUM
	// -------------------------
	public enum LoginStatus {
		SUCCESS, // Login success
		INVALID_OTP, // Wrong OTP
		EXPIRED_OTP, // OTP expired
		OTP_ALREADY_USED, // OTP already used
		MAX_ATTEMPTS, // Max OTP attempts reached
		USER_NOT_FOUND // User does not exist
	}

	// -------------------------
	// OTP STATUS ENUM
	// -------------------------
	public enum OtpStatus {
		OTP_SENT, // OTP successfully sent
		LIMIT_REACHED, // OTP limit reached
		SMS_ERROR, // SMS sending failed
		SERVER_ERROR // Internal error
	}

	public enum VerifyOtpStatus {
		OTP_VERIFIED_SUCCESSFULLY, // OTP correct
		OTP_INVALID, // Incorrect OTP
		OTP_EXPIRED, // Expired
		OTP_ALREADY_USED, // OTP already used
		MAX_ATTEMPTS_REACHED, // 3 attempts done
		OTP_NOT_FOUND // No OTP exists
	}

	public enum SubscriptionStatus {
		ACTIVE, EXPIRED, CANCELLED
	}

	public enum PaymentStatus {
		CREATED, SUCCESS, FAILED
	}
}
