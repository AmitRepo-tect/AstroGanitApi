package com.astroganit.api.service;


import org.springframework.stereotype.Service;
import com.astroganit.api.payload.DeleteAccountResponse;
import com.astroganit.api.payload.LoginOtpResponse;
import com.astroganit.api.payload.LoginRequestDto;
import com.astroganit.api.payload.LoginResponse;
import com.astroganit.api.payload.ResendOtpResponse;
import com.astroganit.api.payload.ResponseNew;
import com.astroganit.api.payload.UserDto;
import com.astroganit.api.payload.UserResponse;

@Service
public interface UserServiceV2 {

	ResponseNew<LoginOtpResponse> requestLoginOtp(LoginRequestDto loginRequestDto);

	ResponseNew<LoginResponse> verifyOtp(String mobile, String otpCode);

	ResponseNew<ResendOtpResponse> resendOTP(String mobile, boolean restoreFlow);

	ResponseNew<UserResponse> updateUserProfile(UserDto user);

	ResponseNew<UserResponse> getUserProfile();

	ResponseNew<DeleteAccountResponse> deleteUser();

}
