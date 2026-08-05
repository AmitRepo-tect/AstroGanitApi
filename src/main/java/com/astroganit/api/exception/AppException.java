package com.astroganit.api.exception;

import java.util.Objects;

import com.astroganit.api.util.ResultCode;

public class AppException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private final ResultCode resultCode;

	public AppException(ResultCode resultCode) {
		super(Objects.requireNonNull(resultCode, "ResultCode cannot be null").getMessage());
		this.resultCode = resultCode;
	}

	public ResultCode getResultCode() {
		return resultCode;
	}

	public int getCode() {
		return resultCode.getCode();
	}

	@Override
	public String getMessage() {
		return resultCode.getMessage();
	}
}