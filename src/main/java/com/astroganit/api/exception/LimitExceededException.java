package com.astroganit.api.exception;

import com.astroganit.api.util.ResultCode;

public class LimitExceededException extends AppException {

	public LimitExceededException(String message) {
		super(ResultCode.LIMIT_REACHED);
	}
}