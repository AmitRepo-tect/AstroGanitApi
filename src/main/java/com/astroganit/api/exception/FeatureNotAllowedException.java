package com.astroganit.api.exception;

import com.astroganit.api.util.ResultCode;

public class FeatureNotAllowedException extends AppException {

	public FeatureNotAllowedException(String message) {
		super(ResultCode.FEATURE_NOT_ALLOWED);
	}
}