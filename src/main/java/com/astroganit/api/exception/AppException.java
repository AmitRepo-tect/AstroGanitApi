package com.astroganit.api.exception;

import com.astroganit.api.util.ResultCode;

public class AppException extends RuntimeException {

	private final int code;
	private final String message;

	public AppException(int code, String message) {
		super(message);
		this.code = code;
		this.message = message;
	}

	public AppException(ResultCode resultCode) {
		super(resultCode.getMessage());
		this.code = resultCode.getCode();
		this.message = resultCode.getMessage();
	}

	public int getCode() {
		return code;
	}

	@Override
	public String getMessage() {
		return message;
	}
}