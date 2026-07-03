package com.astroganit.api.exception;

public class UsernamePasswordException extends AppException {

	public UsernamePasswordException() {
		super(ErrorCodes.USER_PASSWORD_NOT_CORRECT, "Invalid username or password");
	}

	public UsernamePasswordException(String message) {
		super(ErrorCodes.USER_PASSWORD_NOT_CORRECT, message);
	}
}
