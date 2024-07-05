package com.astroganit.api.controller;

import com.astroganit.api.payload.ApiResponse;
import com.astroganit.api.payload.OTPDto;
import com.astroganit.api.payload.Response;
import com.astroganit.api.payload.UserDto;
import com.astroganit.api.service.UserService;
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
@RequestMapping({"/api/user"})
public class UserController {
   @Autowired
   private UserService userService;

   @PostMapping({"/update/profile/{mobile}"})
   public ResponseEntity<Response> updateUserProfile(@RequestBody UserDto useDto, @PathVariable("mobile") String mobile) {
      Response updateUserDto = this.userService.updateUserProfile(useDto, mobile);
      return ResponseEntity.ok(updateUserDto);
   }

   @PostMapping({"/delete/{userId}"})
   public ResponseEntity<?> deleteUser(@PathVariable Integer userId) {
      this.userService.deleteUser(userId);
      return new ResponseEntity(new ApiResponse("user deleted successfully"), HttpStatus.OK);
   }

   @PostMapping({"/login"})
   public ResponseEntity<Response> login(@RequestBody UserDto userDto) {
      Response loginResponse = this.userService.loginUser(userDto);
      return ResponseEntity.ok(loginResponse);
   }

   @PostMapping({"/update/password"})
   public ResponseEntity<Response> updatePassword(@RequestBody UserDto useDto) {
      Response updatePassword = this.userService.updatePassword(useDto);
      return ResponseEntity.ok(updatePassword);
   }

   @GetMapping({"/sendotp/{mobile}"})
   public ResponseEntity<Response> sendOTP(@PathVariable String mobile) {
      Response response = this.userService.sendOTP(mobile);
      return ResponseEntity.ok(response);
   }

   @PostMapping({"/validateotp"})
   public ResponseEntity<Response> validateOTP(@RequestBody OTPDto otpDto) {
      Response validateOTP = this.userService.validateOTP(otpDto);
      return ResponseEntity.ok(validateOTP);
   }

   @PostMapping({"/activateuser/{mobile}"})
   public ResponseEntity<Response> activateUser(@PathVariable String mobile) {
      Response activateUser = this.userService.activateUser(mobile);
      return ResponseEntity.ok(activateUser);
   }

   @PostMapping({"/deactivateuser/{mobile}"})
   public ResponseEntity<Response> dectivateUser(@PathVariable String mobile) {
      Response dectivateUser = this.userService.deactivateUser(mobile);
      return ResponseEntity.ok(dectivateUser);
   }
}
