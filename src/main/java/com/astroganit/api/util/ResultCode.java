package com.astroganit.api.util;

public enum ResultCode {
	// General
	SUCCESS(1, "Successful"), 
	PROFILE_UPDATE_SUCCESSFUL(1, "Profile updated successfully"), 
	EXCEPTION(0, "Exception"),
	INTERNAL_SERVER_ERROR(19, "Internal Server Error"), 
	UNAUTHORIZED(15, "Unauthorized"),
	USER_NOT_FOUND(5, "User not found"),

	// Register user
	USER_ALREADY_REGISTER(2, "User already registered"),

	// Login user
	INVALID_PARAMETER(2, "Invalid parameter"),
	USER_NOT_ACTIVE(3, "User not active"),
	INCORRECT_PASSWORD(4, "Incorrect password"),

	// OTP send
	LIMIT_REACHED(10, "Maximum OTP requests reached. Please try again later."), 
	SMS_ERROR(11, "SMS sending failed"),
	SERVER_ERROR(12, "Internal server error"), 
	OTP_SENT(13, "OTP resent successfully"),
	TOO_SOON(14, "OTP already sent. Please wait 30 seconds before requesting a new OTP."),

	// Verify OTP
	OTP_INVALID(2, "Invalid OTP"), 
	OTP_EXPIRED(3, "OTP expired"), 
	OTP_ALREADY_USED(4, "OTP already Used"),
	MAX_ATTEMPTS_REACHED(6, "Max attempts reached"), 
	OTP_NOT_FOUND(7, "OTP not found"),
	OTP_VERIFICATION_FAILED(8, "Failed to verify OTP and complete login"),

	// User status
	USER_ALREADY_ACTIVE(8, "User already active"), 
	USER_ALREADY_DEACTIVATE(9, "User already deactivated"),
	USER_NOT_VERIFIED(16, "User not verified"),

	// Subscription
	SUBSCRIPTION_NOT_FOUND(0, "Subscription not found"),
	// feature
	FEATURE_NOT_ALLOWED(17, "This feature is not available in your current subscription plan."),
	DATA_NOT_FOUND(18, "The requested data does not exist."),
	PLAN_NOT_FOUND(20,"Plan not found"),
	INVALID_REQUEST(21,"Invalid request"),
	AMOUNT_IS_REQUIRED(22,"Amount is required"),
	INVALID_PLAN_AMOUNT(23,"Invalid plan amount"),
	PLAN_NOT_AVAILABLE(24,"Plan is not available"),
	PAYMENT_GATEWAY_ERROR(25,"Unable to create payment order. Please try again."),
	PAYMENT_INITIALIZATION_FAILED(26,"Payment initialization failed. Please retry."),
	RESOURCE_NOT_FOUND(27,"Resource not found"),
	USER_PASSWORD_NOT_CORRECT(28,"Invalid username or password"),
	PAYMENT_NOT_FOUND(29,""),
	PAYMENT_ALREADY_FAILED(30,""),
	INVALID_PAYMENT(31,""),
	INVALID_PAYMENT_SIGNATURE(32,""),
	ACCOUNT_PENDING_DELETION(33, "Account is pending deletion."),
	ACCOUNT_NOT_PENDING_DELETION(34, "Account is not pending deletion."),
	PROFILE_FETCH_SUCCESSFUL(35, "Profile fetched successfully");
	private final int code;
	private final String message;

	ResultCode(int code, String message) {
	        this.code = code;
	        this.message = message;
	    }

	public int getCode() {
	        return code;
	    }

	public String getMessage() {
	        return message;
	    }

	// Optional: get enum from code (useful but risky due to duplicates)
	public static ResultCode fromCode(int code) {
	        for (ResultCode value : ResultCode.values()) {
	            if (value.code == code) {
	                return value; // returns first match
	            }
	        }
	        return null;
	    }
}
