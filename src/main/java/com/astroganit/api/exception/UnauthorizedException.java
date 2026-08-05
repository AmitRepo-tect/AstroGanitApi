package com.astroganit.api.exception;

import com.astroganit.api.util.ResultCode;

public class UnauthorizedException extends AppException {

	public UnauthorizedException(String message) {
		super(ResultCode.UNAUTHORIZED);
	}
}