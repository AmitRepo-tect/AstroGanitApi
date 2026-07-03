package com.astroganit.api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.astroganit.api.payload.OTPDto;
import com.astroganit.api.payload.Response;
import com.astroganit.api.payload.ResponseNew;
import com.astroganit.api.payload.UserDto;
import com.astroganit.api.payload.UserResponse;

@Service
public interface UserService {
	ResponseNew<List<UserResponse>> requestLoginOtp(UserDto userDto);

	ResponseNew<List<String>> verifyOtp(String mobile, String otpCode);

	ResponseNew<Void> resendOTP(String mobile);

	ResponseNew<List<UserResponse>> updateUserProfileV1(UserDto user);
	
	ResponseNew<List<UserResponse>> getUserProfile();

	UserDto registerNewUser(UserDto user);

	/*
	 * Response updateUserProfile(UserDto user);
	 */
	Boolean checkMobileNumberExit(String mobile);

	Response loginUser(UserDto userDto);

	Response updatePassword(UserDto userDto);

	String sendOTPForLoginSignup(String mobile);

	void generateOtp(String mobile);

	Response validateOTP(OTPDto otpDto);

	void deleteUser(Long userId);

	Response deactivateUser();

	Response activateUser();
}
