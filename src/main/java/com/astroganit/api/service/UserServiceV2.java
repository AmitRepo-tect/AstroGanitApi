package com.astroganit.api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.astroganit.api.payload.ActivateUserResponse;
import com.astroganit.api.payload.DeleteAccountResponse;
import com.astroganit.api.payload.LoginOtpResponse;
import com.astroganit.api.payload.LoginRequestDto;
import com.astroganit.api.payload.LoginResponse;
import com.astroganit.api.payload.OTPDto;
import com.astroganit.api.payload.ResendOtpResponse;
import com.astroganit.api.payload.Response;
import com.astroganit.api.payload.ResponseNew;
import com.astroganit.api.payload.UserDto;
import com.astroganit.api.payload.UserResponse;

@Service
public interface UserServiceV2 {

	ResponseNew<LoginOtpResponse> requestLoginOtp2(LoginRequestDto loginRequestDto);

	ResponseNew<LoginResponse> verifyOtpV2(String mobile, String otpCode);

	ResponseNew<ResendOtpResponse> resendOTP(String mobile, boolean restoreFlow);

	ResponseNew<UserResponse> updateUserProfileV1(UserDto user);

	ResponseNew<UserResponse> getUserProfile();

	ResponseNew<DeleteAccountResponse> deleteUserV2();
	
	void generateOtp(String mobile);
	
}
