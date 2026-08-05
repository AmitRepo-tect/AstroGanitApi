package com.astroganit.api.exception;

import com.astroganit.api.payload.ResponseNew;
import com.astroganit.api.util.ResultCode;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {

		Map<String, String> errors = new HashMap<>();

		ex.getBindingResult().getFieldErrors().forEach(error -> {
			errors.put(error.getField(), error.getDefaultMessage());
		});

		ResponseNew<Map<String, String>> res = new ResponseNew<Map<String, String>>();
		res.setData(errors);
		res.setErrorMessage("Validation failed");
		res.setStatus(HttpStatus.BAD_REQUEST);
		res.setResultCode(ErrorCodes.VALIDATION_FAILED);
		res.setMessage("Validation failed");

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
	}

	@ExceptionHandler(NumberFormatException.class)
	public ResponseEntity<Object> numberFormatExceptionHandler(NumberFormatException ex) {

		ResponseNew<List<String>> res = new ResponseNew<List<String>>();
		res.setData(Collections.emptyList());
		res.setErrorMessage("Invalid number format");
		res.setStatus(HttpStatus.BAD_REQUEST);
		res.setResultCode(ErrorCodes.EXCEPTION);
		res.setMessage("Invalid input format");
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
	}

	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {

		ResponseNew<List<String>> res = new ResponseNew<List<String>>();
		res.setData(Collections.emptyList());
		res.setErrorMessage("HTTP method not allowed");
		res.setStatus(HttpStatus.METHOD_NOT_ALLOWED);
		res.setStatusCode(HttpStatus.METHOD_NOT_ALLOWED.value());
		res.setResultCode(ErrorCodes.EXCEPTION);
		res.setMessage("HTTP method not allowed");
		return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(res);

	}

	/*
	 * @ExceptionHandler(AppException.class) public ResponseEntity<Object>
	 * handleAppException(AppException ex) { ResponseNew<List<String>> res = new
	 * ResponseNew<List<String>>(); res.setData(Collections.emptyList());
	 * res.setErrorMessage(ex.getMessage()); res.setStatus(HttpStatus.OK);
	 * res.setStatusCode(HttpStatus.OK.value()); res.setResultCode(ex.getCode());
	 * res.setMessage(ex.getMessage()); return
	 * ResponseEntity.status(HttpStatus.OK).body(res); }
	 */
	@ExceptionHandler(AppException.class)
	public ResponseEntity<Object> handleAppException(AppException ex) {
		System.out.println(ex.getCode() + "--" + ex.getMessage());
		//HttpStatus status = mapToHttpStatus(ex.getCode());
		HttpStatus status = ex.getResultCode() != null
		        ? mapToHttpStatus(ex.getResultCode())
		        : HttpStatus.BAD_REQUEST;
		ResponseNew<List<String>> res = new ResponseNew<>();
		res.setData(Collections.emptyList());
		res.setErrorMessage(ex.getMessage());
		res.setStatus(status); // ✔ correct status
		res.setStatusCode(status.value()); // ✔ correct code
		res.setResultCode(ex.getCode());
		res.setMessage(ex.getMessage());
		return ResponseEntity.status(status).body(res); // 🔥 IMPORTANT
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleException(Exception ex) {
		ResponseNew<List<String>> res = new ResponseNew<List<String>>();
		res.setData(Collections.emptyList());
		res.setErrorMessage("Something went wrong");
		res.setMessage("Internal server error");
		res.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
		res.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		res.setResultCode(ErrorCodes.EXCEPTION);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
	}

	private HttpStatus mapToHttpStatus(int code) {

		if (code == ResultCode.UNAUTHORIZED.getCode()) {
			return HttpStatus.UNAUTHORIZED; // 401
		}
		if (code == ResultCode.SUBSCRIPTION_NOT_FOUND.getCode()) {
			return HttpStatus.FORBIDDEN; // 403
		}
		if (code == ResultCode.LIMIT_REACHED.getCode()) {
			return HttpStatus.TOO_MANY_REQUESTS; // 429
		}
		if (code == ResultCode.PAYMENT_GATEWAY_ERROR.getCode()) {
		    return HttpStatus.BAD_GATEWAY; // 502
		}

		return HttpStatus.BAD_REQUEST;
	}
	private HttpStatus mapToHttpStatus(ResultCode resultCode) {
	    switch (resultCode) {
	        case SUCCESS:
	        case PROFILE_UPDATE_SUCCESSFUL:
	        case OTP_SENT:
	            return HttpStatus.OK;
	        case UNAUTHORIZED:
	            return HttpStatus.UNAUTHORIZED;
	        case USER_NOT_ACTIVE:
	        case USER_NOT_VERIFIED:
	            return HttpStatus.FORBIDDEN;
	        case USER_NOT_FOUND:
	        case PLAN_NOT_FOUND:
	        case DATA_NOT_FOUND:
	        case SUBSCRIPTION_NOT_FOUND:
	            return HttpStatus.NOT_FOUND;
	        case LIMIT_REACHED:
	        case TOO_SOON:
	            return HttpStatus.TOO_MANY_REQUESTS;
	        case PAYMENT_GATEWAY_ERROR:
	            return HttpStatus.BAD_GATEWAY;
	        case PAYMENT_INITIALIZATION_FAILED:
	        case INTERNAL_SERVER_ERROR:
	        case SERVER_ERROR:
	            return HttpStatus.INTERNAL_SERVER_ERROR;
	        case ACCOUNT_PENDING_DELETION:
	            return HttpStatus.CONFLICT;
	        default:
	            return HttpStatus.BAD_REQUEST;
	    }
	}
}
