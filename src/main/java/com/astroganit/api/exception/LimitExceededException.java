package com.astroganit.api.exception;

public class LimitExceededException extends AppException {

	public LimitExceededException(String message) {
		super(ErrorCodes.LIMIT_EXCEEDED, message);
	}
}