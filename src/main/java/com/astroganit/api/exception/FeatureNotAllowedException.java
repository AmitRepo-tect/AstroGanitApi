package com.astroganit.api.exception;

public class FeatureNotAllowedException extends AppException {

	public FeatureNotAllowedException(String message) {
		super(ErrorCodes.FEATURE_NOT_ALLOWED, message);
	}
}