package com.astroganit.api.controller;

import com.astroganit.api.exception.UsernamePasswordException;
import com.astroganit.api.payload.JwtAuthRequest;
import com.astroganit.api.payload.Response;
import com.astroganit.api.payload.UserDto;
import com.astroganit.api.service.UserService;
import com.astroganit.security.JwtTokenHelper;
import java.util.Arrays;
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
@RequestMapping({"/api/ganit/v1/auth"})
public class AuthController {
   @Autowired
   private JwtTokenHelper jwtTokenHelper;
   @Autowired
   private UserDetailsService userDetailService;
   @Autowired
   private AuthenticationManager authenticationManger;
   @Autowired
   private UserService userService;

   @PostMapping({"/token"})
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
      UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);

      try {
         this.authenticationManger.authenticate(authenticationToken);
      } catch (DisabledException var5) {
         throw new UsernamePasswordException(username, password, "USER_DISABLED");
      } catch (BadCredentialsException var6) {
         System.out.println("invalid userid or  password !!");
         throw new UsernamePasswordException(username, password, "Bad Crendential");
      }
   }

   @PostMapping({"/register"})
   public ResponseEntity<Response> registeredNewUser(@RequestBody UserDto userDto) {
      userDto.setDcrptpassword(userDto.getPassword());
      if(userDto.getName()==null) {
    	  userDto.setName("Amit");  
      }
      String mobileNo = userDto.getMobile();
      Response response = new Response();
      response.setErrorMessage("");
      response.setStatus(HttpStatus.OK);
      boolean isUserExit = this.userService.checkMobileNumberExit(mobileNo);
      if (!isUserExit) {
         this.userService.registerNewUser(userDto);
         String otpResponse = "";
         otpResponse = this.userService.sendOTPForLoginSignup(mobileNo);
         if (otpResponse.equalsIgnoreCase("COUNTGT")) {
            response.setResultCode(10);
         } else if (otpResponse.equalsIgnoreCase("EXCEPTION")) {
            response.setResultCode(11);
         } else {
            response.setResultCode(1);
         }

         response.setMessage(otpResponse);
         response.setData(Arrays.asList());
      } else {
         response.setResultCode(2);
         response.setMessage("User Registered already");
         response.setData(Arrays.asList());
      }

      return new ResponseEntity(response, HttpStatus.CREATED);
   }
}
