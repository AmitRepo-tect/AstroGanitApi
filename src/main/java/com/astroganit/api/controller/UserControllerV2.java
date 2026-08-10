package com.astroganit.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.astroganit.api.payload.DeleteAccountResponse;
import com.astroganit.api.payload.LoginOtpResponse;
import com.astroganit.api.payload.LoginRequestDto;
import com.astroganit.api.payload.LoginResponse;
import com.astroganit.api.payload.OTPRequestDto;
import com.astroganit.api.payload.ResendOtpResponse;
import com.astroganit.api.payload.ResponseNew;
import com.astroganit.api.payload.UserDto;
import com.astroganit.api.payload.UserResponse;
import com.astroganit.api.service.UserServiceV2;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v2/user")
public class UserControllerV2 {

	private final UserServiceV2 userServiceV2;

	public UserControllerV2(UserServiceV2 userServiceV2) {
		this.userServiceV2 = userServiceV2;
	}

	@PostMapping("/login")
	public ResponseEntity<ResponseNew<LoginOtpResponse>> login(@RequestBody LoginRequestDto request) {
		ResponseNew<LoginOtpResponse> loginResponse = this.userServiceV2.requestLoginOtp(request);
		return ResponseEntity.ok(loginResponse);
	}

	@GetMapping("/sendotp/{mobile}")
	public ResponseEntity<ResponseNew<ResendOtpResponse>> resendOtp(@PathVariable String mobile,
			@RequestParam(defaultValue = "false") boolean restoreFlow) {
		ResponseNew<ResendOtpResponse> response = this.userServiceV2.resendOTP(mobile, restoreFlow);
		return ResponseEntity.ok(response);
	}

	@PostMapping("/validateotp")
	public ResponseEntity<ResponseNew<LoginResponse>> validateOTP(@Valid @RequestBody OTPRequestDto request) {
		ResponseNew<LoginResponse> validateOTP = this.userServiceV2.verifyOtp(request.getMobile(), request.getOtp());
		return ResponseEntity.ok(validateOTP);
	}

	@DeleteMapping("/delete")
	public ResponseEntity<ResponseNew<DeleteAccountResponse>> deleteUser() {
		return ResponseEntity.ok(userServiceV2.deleteUser());
	}

	@PutMapping("/profile")
	public ResponseEntity<ResponseNew<UserResponse>> updateUserProfile(@RequestBody UserDto userDto) {
		ResponseNew<UserResponse> updateUserDto = this.userServiceV2.updateUserProfile(userDto);
		return ResponseEntity.ok(updateUserDto);
	}

	@GetMapping("/profile")
	public ResponseEntity<ResponseNew<UserResponse>> getUserProfile() {
		ResponseNew<UserResponse> response = this.userServiceV2.getUserProfile();
		return ResponseEntity.ok(response);
	}
}