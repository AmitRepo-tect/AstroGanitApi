package com.astroganit.api.controller;

import com.astroganit.api.entities.User;
import com.astroganit.api.exception.AppException;
import com.astroganit.api.exception.UsernamePasswordException;
import com.astroganit.api.payload.JwtAuthRequest;
import com.astroganit.api.payload.Response;
import com.astroganit.api.payload.ResponseNew;
import com.astroganit.api.payload.UserDto;
import com.astroganit.api.payload.UserResponse;
import com.astroganit.api.repository.UserRepo;
import com.astroganit.api.service.UserService;
import com.astroganit.api.util.ResultCode;
import com.astroganit.lib.panchang.util.AppResultConstant;
import com.astroganit.security.JwtTokenHelper;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({ "/api/ganit/v1/auth" })
public class AuthController {
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private JwtTokenHelper jwtTokenHelper;
	@Autowired
	private UserDetailsService userDetailService;
	@Autowired
	private AuthenticationManager authenticationManger;
	@Autowired
	private UserService userService;
	@Autowired
	private ModelMapper modelMapper;

	@PostMapping({ "/token" })
	public ResponseEntity<Response> createToken(@RequestBody JwtAuthRequest request) throws Exception {
		this.authenticate(request.getUsername(), request.getPassword());
		UserDetails userDetails = this.userDetailService.loadUserByUsername(request.getUsername());
		String generateToken = this.jwtTokenHelper.generateToken(userDetails);
		Response response = new Response();
		response.setErrorMessage("");
		response.setStatus(HttpStatus.OK);
		response.setResultCode(AppResultConstant.SUCCESSFUL);
		response.setData(Arrays.asList(generateToken));
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@PostMapping("/token_otp")
	public ResponseEntity<ResponseNew<List<String>>> createTokenWithOtp(@RequestBody JwtAuthRequest request) {

		// 1. Verify OTP (already done in your verifyOtp method)
		User user = userRepo.findByLoginId(request.getUsername())
				.orElseThrow(() -> new AppException(ResultCode.USER_NOT_FOUND));

		// 2. Generate token WITHOUT password
		UserDetails userDetails = userDetailService.loadUserByUsername(user.getLoginId());
		String token = jwtTokenHelper.generateToken(userDetails);

		ResponseNew<List<String>> response = new ResponseNew<List<String>>();
		response.setStatus(HttpStatus.OK);
		response.setResultCode(ResultCode.SUCCESS.getCode());
		response.setMessage(ResultCode.SUCCESS.getMessage());
		response.setData(Arrays.asList(token));
		return ResponseEntity.ok(response);
	}

	@PostMapping("/generate-token")
	public ResponseEntity<Response> generateToken(@RequestBody JwtAuthRequest request) {

		// 1. Verify OTP (already done in your verifyOtp method)
		User user = userRepo.findByLoginId(request.getUsername())
				.orElseThrow(() -> new AppException(ResultCode.USER_NOT_FOUND));
		if (!user.isUserVerified()) {
			throw new AppException(ResultCode.USER_NOT_VERIFIED);
		}
		// 2. Generate token WITHOUT password
		String token;
		try {
			UserDetails userDetails = userDetailService.loadUserByUsername(user.getLoginId());
			token = jwtTokenHelper.generateToken(userDetails);
		} catch (Exception e) {
			throw new AppException(ResultCode.SERVER_ERROR);
		}
		Response response = new Response();
		response.setStatus(HttpStatus.OK);
		response.setResultCode(ResultCode.SUCCESS.getCode());
		response.setMessage(ResultCode.SUCCESS.getMessage());
		response.setData(Arrays.asList(token));
		return ResponseEntity.ok(response);
	}

	@PostMapping({ "/register" })
	public ResponseEntity<Response> registeredNewUser(@RequestBody UserDto userDto) {
		userDto.setDcrptpassword(userDto.getPassword());
		if (userDto.getName() == null) {
			userDto.setName("");
		}
		String loginID = userDto.getLoginId();
		String mobileNo = userDto.getMobile();
		Response response = new Response();
		response.setErrorMessage("");
		response.setStatus(HttpStatus.OK);
		boolean isUserExist = this.userService.checkMobileNumberExit(loginID);
		if (!isUserExist) {
			userDto = this.userService.registerNewUser(userDto);
			this.userService.generateOtp(mobileNo);
			UserResponse logedInUser = (UserResponse) this.modelMapper.map(userDto, UserResponse.class);
			response.setResultCode(ResultCode.OTP_SENT.getCode());
			response.setMessage(ResultCode.OTP_SENT.getMessage());
			response.setData(Arrays.asList(logedInUser));

		} else {
			response.setResultCode(ResultCode.USER_ALREADY_REGISTER.getCode());
			response.setMessage(ResultCode.USER_ALREADY_REGISTER.getMessage());
			response.setData(Collections.emptyList());
		}
		return ResponseEntity.ok(response);
	}

	public void authenticate(String username, String password) throws Exception {

		if (username == null || password == null) {
			throw new UsernamePasswordException("Username or Password Missing");
		}

		UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, password);

		try {
			authenticationManger.authenticate(authToken);
		} catch (DisabledException e) {
			throw new UsernamePasswordException("User is Disabled");
		} catch (BadCredentialsException e) {
			throw new UsernamePasswordException("Bad Credentials");
		}
	}
}
