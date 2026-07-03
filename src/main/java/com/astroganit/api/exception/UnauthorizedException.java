package com.astroganit.api.exception;

public class UnauthorizedException extends AppException {

	public UnauthorizedException(String message) {
		super(ErrorCodes.UNAUTHORIZED, message);
	}
}