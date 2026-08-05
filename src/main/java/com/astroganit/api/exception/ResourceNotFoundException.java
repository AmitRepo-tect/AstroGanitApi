package com.astroganit.api.exception;

import com.astroganit.api.util.ResultCode;

public class ResourceNotFoundException extends AppException {


	public ResourceNotFoundException(String msg) {
		super(ResultCode.RESOURCE_NOT_FOUND);
	}

	public ResourceNotFoundException() {
		super(ResultCode.RESOURCE_NOT_FOUND);
	}
}