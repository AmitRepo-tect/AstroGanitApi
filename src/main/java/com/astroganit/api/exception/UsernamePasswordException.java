package com.astroganit.api.exception;

import com.astroganit.api.util.ResultCode;

public class UsernamePasswordException extends AppException {

	public UsernamePasswordException() {
		super(ResultCode.USER_PASSWORD_NOT_CORRECT);
	}

	public UsernamePasswordException(String message) {
		super(ResultCode.USER_PASSWORD_NOT_CORRECT);
	}
}
