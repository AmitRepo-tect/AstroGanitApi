package com.astroganit.api.exception;

public class ResourceNotFoundException extends AppException {

	private static final String DEFAULT_MESSAGE = "Resource not found";

	public ResourceNotFoundException(String msg) {
		super(ErrorCodes.RESOURCE_NOT_FOUND, msg);
	}

	public ResourceNotFoundException() {
		super(ErrorCodes.RESOURCE_NOT_FOUND, DEFAULT_MESSAGE);
	}
}