package com.astroganit.api.service;

import org.springframework.stereotype.Service;

import com.astroganit.api.payload.OTPDto;
import com.astroganit.api.payload.Response;
import com.astroganit.api.payload.UserDto;

@Service
public interface UserService {
   UserDto registerNewUser(UserDto user);

   Response updateUserProfile(UserDto user, String userId);

   Boolean checkMobileNumberExit(String mobile);

   Response loginUser(UserDto userDto);

   Response updatePassword(UserDto userDto);

   Response sendOTP(String mobile);

   String sendOTPForLoginSignup(String mobile);

   Response validateOTP(OTPDto otpDto);

   void deleteUser(Integer userId);

   Response deactivateUser(String mobile);

   Response activateUser(String mobile);
}
