package com.astroganit.api.exception;

public class UserNotFoundException extends AppException {

	public UserNotFoundException(String message) {
		super(ErrorCodes.USER_NOT_FOUND, message);
	}
}