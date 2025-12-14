package com.astroganit.lib.panchang.util;

public class AppResultConstant {
	//Register user
	public static final int SUCCESSFUL = 1; // Successful
	public static final int USER_ALREADY_REGISTER = 2; // Internal error
	//Login user
	public static final int INVALID_PARAMETER = 2; // invalid parameter
	public static final int USER_NOT_ACTIVE = 3;// User is not active
	public static final int INCORRECT_PASSWORD = 4; // Incorrect password
	public static final int USER_NOT_FOUND = 5; // User not found
	//Otp send
	public static final int LIMIT_REACHED = 10; // OTP limit reached
	public static final int SMS_ERROR = 11; // SMS sending failed
	public static final int SERVER_ERROR = 12; // Internal error
	public static final int OTP_SENT = 13; // Otp sent
	//Verify otp
	public static final int OTP_VERIFIED_SUCCESSFULLY = 1;
	public static final int OTP_INVALID = 2; // Incorrect OTP
	public static final int OTP_EXPIRED = 3; // Expired
	public static final int OTP_ALREADY_USED = 4; // OTP already used
	public static final int MAX_ATTEMPTS_REACHED = 6; // 3 attempts done
	public static final int OTP_NOT_FOUND = 7; // No OTP exists
	//Activate user
	public static final int USER_ALREADY_ACTIVE = 8; 
	//DeActivate user
	public static final int USER_ALREADY_DEACTIVATE = 9; 
}
