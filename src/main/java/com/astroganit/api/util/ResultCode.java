package com.astroganit.api.util;

public enum ResultCode {
	    //General
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
	    LIMIT_REACHED(10, "OTP limit reached"),
	    SMS_ERROR(11, "SMS sending failed"),
	    SERVER_ERROR(12, "Internal server error"),
	    OTP_SENT(13, "OTP sent successfully"),
	    TOO_SOON(14, "OTP already sent. Please wait 30 seconds before requesting a new OTP."),

	    // Verify OTP
	    OTP_INVALID(2, "Invalid OTP"),
	    OTP_EXPIRED(3, "OTP expired"),
	    OTP_ALREADY_USED(4, "OTP already verified"),
	    MAX_ATTEMPTS_REACHED(6, "Max attempts reached"),
	    OTP_NOT_FOUND(7, "OTP not found"),
	    OTP_VERIFICATION_FAILED(8,"Failed to verify OTP and complete login"),

	    // User status
	    USER_ALREADY_ACTIVE(8, "User already active"),
	    USER_ALREADY_DEACTIVATE(9, "User already deactivated"),
	    USER_NOT_VERIFIED(16, "User not verified"),

	    // Subscription
	    SUBSCRIPTION_NOT_FOUND(0, "Subscription not found"),
		//feature
	    FEATURE_NOT_ALLOWED(17, "This feature is not available in your current subscription plan."),
	    DATA_NOT_FOUND(18, "The requested data does not exist.");
	
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

