package com.astroganit.api.exception;

import com.astroganit.api.util.ResultCode;

public class UserNotFoundException extends AppException {

	public UserNotFoundException(String message) {
		super(ResultCode.USER_NOT_FOUND);
	}
}