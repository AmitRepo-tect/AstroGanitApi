package com.astroganit.api.controller;

import com.astroganit.api.exception.UsernamePasswordException;
import com.astroganit.api.payload.JwtAuthRequest;
import com.astroganit.api.payload.Response;
import com.astroganit.api.payload.UserDto;
import com.astroganit.api.payload.UserResponse;
import com.astroganit.api.repository.UserRepo;
import com.astroganit.api.service.UserService;
import com.astroganit.lib.panchang.util.AppResultConstant;
import com.astroganit.security.JwtTokenHelper;
import java.util.Arrays;

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
		Response response1 = new Response();
		response1.setErrorMessage("");
		response1.setStatus(HttpStatus.OK);
		response1.setResultCode(1);
		response1.setData(Arrays.asList(generateToken));
		return new ResponseEntity(response1, HttpStatus.OK);
	}

	public void authenticate(String username, String password) throws Exception {

		if (username == null || password == null) {
			throw new UsernamePasswordException(username, password, "Username or Password Missing");
		}

		UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, password);

		try {
			authenticationManger.authenticate(authToken);
		} catch (DisabledException e) {
			throw new UsernamePasswordException(username, password, "USER_DISABLED");
		} catch (BadCredentialsException e) {
			throw new UsernamePasswordException(username, password, "Bad Credentials");
		}
	}

	@PostMapping({ "/register" })
	public Response registeredNewUser(@RequestBody UserDto userDto) {
		userDto.setDcrptpassword(userDto.getPassword());
		if (userDto.getName() == null) {
			userDto.setName("");
		}
		String loginID = userDto.getLoginId();
		String mobileNo = userDto.getMobile();
		Response response = new Response();
		response.setErrorMessage("");
		response.setStatus(HttpStatus.OK);
		boolean isUserExit = this.userService.checkMobileNumberExit(loginID);
		if (!isUserExit) {
			userDto = this.userService.registerNewUser(userDto);
			String otpResponse = "";
			int resultCode = this.userService.generateOtp(mobileNo);
			if (resultCode == AppResultConstant.LIMIT_REACHED) {
				otpResponse = "OTP request limit reached. Try again after 10 minutes.";
			} else if (resultCode == AppResultConstant.SERVER_ERROR) {
				otpResponse = "Server Error";
			} else if (resultCode == AppResultConstant.SMS_ERROR) {
				otpResponse = "SMS sending failed";
			} else {
				otpResponse = "OTP sent successfully";
			}

			UserResponse logedInUser = (UserResponse) this.modelMapper.map(userDto, UserResponse.class);
			response.setResultCode(resultCode);
			response.setMessage(otpResponse);
			response.setData(Arrays.asList(logedInUser));

		} else {
			response.setResultCode(AppResultConstant.USER_ALREADY_REGISTER);
			response.setMessage("User Registered already");
			response.setData(Arrays.asList());
		}

		return response;
	}
}
