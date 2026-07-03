package com.astroganit.api.controller;

import com.astroganit.api.payload.ApiResponse;
import com.astroganit.api.payload.OTPDto;
import com.astroganit.api.payload.Response;
import com.astroganit.api.payload.ResponseNew;
import com.astroganit.api.payload.UserDto;
import com.astroganit.api.payload.UserResponse;
import com.astroganit.api.service.UserService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({ "/api/user" })
public class UserController {
	@Autowired
	private UserService userService;

	@PostMapping({ "/login_v1" })
	public ResponseEntity<ResponseNew<List<UserResponse>>> loginNew(@RequestBody UserDto userDto) {
		ResponseNew<List<UserResponse>> loginResponse = this.userService.requestLoginOtp(userDto);
		return ResponseEntity.ok(loginResponse);
	}

	@GetMapping({ "/sendotp/{mobile}" })
	public ResponseEntity<ResponseNew<Void>> sendOTP(@PathVariable String mobile) {
		ResponseNew<Void> response = this.userService.resendOTP(mobile);
		return ResponseEntity.ok(response);
	}

	@PostMapping({ "/validateotp" })
	public ResponseEntity<ResponseNew<List<String>>> validateOTP(@RequestBody OTPDto otpDto) {
		ResponseNew<List<String>> validateOTP = this.userService.verifyOtp(otpDto.getMobile(), otpDto.getOtp());
		return ResponseEntity.ok(validateOTP);
	}

	@PostMapping({ "/update/profile" })
	public ResponseEntity<ResponseNew<List<UserResponse>>> updateUserProfileV1(@RequestBody UserDto useDto) {
		ResponseNew<List<UserResponse>> updateUserDto = this.userService.updateUserProfileV1(useDto);
		return ResponseEntity.ok(updateUserDto);
	}
	@GetMapping("/profile")
	public ResponseEntity<ResponseNew<List<UserResponse>>> getUserProfile() {
	    ResponseNew<List<UserResponse>> response =userService.getUserProfile();
	    return ResponseEntity.ok(response);
	}
	/*
	 * @PostMapping({ "/update/profile" }) public ResponseEntity<Response>
	 * updateUserProfile(@RequestBody UserDto useDto) { Response updateUserDto =
	 * this.userService.updateUserProfile(useDto); return
	 * ResponseEntity.ok(updateUserDto); }
	 */

	@PostMapping({ "/login" })
	public ResponseEntity<Response> login(@RequestBody UserDto userDto) {
		Response loginResponse = this.userService.loginUser(userDto);
		return ResponseEntity.ok(loginResponse);
	}

	@PostMapping({ "/activateuser/{mobile}" })
	public ResponseEntity<Response> activateUser(@PathVariable String mobile) {
		Response activateUser = this.userService.activateUser();
		return ResponseEntity.ok(activateUser);
	}

	@PostMapping({ "/deactivateuser/{mobile}" })
	public ResponseEntity<Response> dectivateUser(@PathVariable String mobile) {
		Response dectivateUser = this.userService.deactivateUser();
		return ResponseEntity.ok(dectivateUser);
	}

	@GetMapping({ "/delete/{id}" })
	public ResponseEntity<?> deleteUser(@PathVariable Long id) {
		this.userService.deleteUser(id);
		return new ResponseEntity(new ApiResponse("user deleted successfully"), HttpStatus.OK);
	}

	@PostMapping({ "/update/password" })
	public ResponseEntity<Response> updatePassword(@RequestBody UserDto useDto) {
		Response updatePassword = this.userService.updatePassword(useDto);
		return ResponseEntity.ok(updatePassword);
	}
}
