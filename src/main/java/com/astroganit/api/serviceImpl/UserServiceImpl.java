package com.astroganit.api.serviceImpl;

import com.astroganit.api.constant.AppConstant;
import com.astroganit.api.entities.OTP;
import com.astroganit.api.entities.Role;
import com.astroganit.api.entities.User;
import com.astroganit.api.exception.ResourceNotFoundException;
import com.astroganit.api.payload.OTPDto;
import com.astroganit.api.payload.Response;
import com.astroganit.api.payload.UserDto;
import com.astroganit.api.payload.UserResponse;
import com.astroganit.api.repository.OTPRepo;
import com.astroganit.api.repository.RoleRepo;
import com.astroganit.api.repository.UserRepo;
import com.astroganit.api.service.UserService;
import com.astroganit.api.util.HUtil;
import com.astroganit.api.util.SendSMS;
import java.util.Arrays;
import java.util.Date;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
   @Autowired
   private UserRepo userRepo;
   @Autowired
   private ModelMapper modelMapper;
   @Autowired
   private RoleRepo roleRepo;
   @Autowired
   private OTPRepo otpRepo;
   @Autowired
   private SendSMS sendSMS;
   @Autowired
   private PasswordEncoder passwordEncoder;

   public UserDto registerNewUser(UserDto userDto) {
      User user = (User)this.modelMapper.map(userDto, User.class);
      user.setCreatedDate(new Date());
      user.setPassword(this.passwordEncoder.encode(user.getPassword()));
      user.setDcrptpassword(user.getDcrptpassword());
      user.setUserActive(true);
      Role role = (Role)this.roleRepo.findById(AppConstant.NORMAL_USER).get();
      user.getRoles().add(role);
      User newUser = (User)this.userRepo.save(user);
      return (UserDto)this.modelMapper.map(newUser, UserDto.class);
   }

   public Response updateUserProfile(UserDto userDto, String mobile) {
      Response response = new Response();
      response.setErrorMessage("");
      response.setStatus(HttpStatus.OK);
      if (HUtil.isNullEmpty(mobile)) {
         mobile = "0000000000";
      }

      Optional<User> findByMobile = this.userRepo.findByMobile(mobile);
      if (findByMobile.isPresent()) {
         User user = (User)findByMobile.get();
         if (!HUtil.isNullEmpty(userDto.getName())) {
            user.setName(userDto.getName());
         }

         if (!HUtil.isNullEmpty(userDto.getAbout())) {
            user.setAbout(userDto.getAbout());
         }

         if (!HUtil.isNullEmpty(userDto.getEmail())) {
            user.setEmail(userDto.getEmail());
         }

         if (!HUtil.isNullEmpty(userDto.getGender())) {
            user.setGender(userDto.getGender());
         }

         if (!HUtil.isNullEmpty(userDto.getMaritalStatus())) {
            user.setMaritalStatus(userDto.getMaritalStatus());
         }

         if (!HUtil.isNullEmpty(userDto.getPlace())) {
            user.setPlace(userDto.getPlace());
         }

         if (!HUtil.isNullEmpty(userDto.getState())) {
            user.setState(userDto.getState());
         }

         if (!HUtil.isNullEmpty(userDto.getCountry())) {
            user.setCountry(userDto.getCountry());
         }

         if (!HUtil.isNullEmpty(userDto.getDayBirth())) {
            user.setDayBirth(userDto.getDayBirth());
         }

         if (!HUtil.isNullEmpty(userDto.getMonthBirth())) {
            user.setMonthBirth(userDto.getMonthBirth());
         }

         if (!HUtil.isNullEmpty(userDto.getYearBirth())) {
            user.setYearBirth(userDto.getYearBirth());
         }

         if (!HUtil.isNullEmpty(userDto.getHourBirth())) {
            user.setHourBirth(userDto.getHourBirth());
         }

         if (!HUtil.isNullEmpty(userDto.getMinuteBirth())) {
            user.setMinuteBirth(userDto.getMinuteBirth());
         }

         if (!HUtil.isNullEmpty(userDto.getSecondBirth())) {
            user.setSecondBirth(userDto.getSecondBirth());
         }

         if (!HUtil.isNullEmpty(userDto.getLatitude())) {
            user.setLatitude(userDto.getLatitude());
         }

         if (!HUtil.isNullEmpty(userDto.getLatDeg())) {
            user.setLatDeg(userDto.getLatDeg());
         }

         if (!HUtil.isNullEmpty(userDto.getLatMin())) {
            user.setLatMin(userDto.getLatMin());
         }

         if (!HUtil.isNullEmpty(userDto.getLatNS())) {
            user.setLatNS(userDto.getLatNS());
         }

         if (!HUtil.isNullEmpty(userDto.getLongitude())) {
            user.setLongitude(userDto.getLongitude());
         }

         if (!HUtil.isNullEmpty(userDto.getLongDeg())) {
            user.setLongDeg(userDto.getLongDeg());
         }

         if (!HUtil.isNullEmpty(userDto.getLongMin())) {
            user.setLongMin(userDto.getLongMin());
         }

         if (!HUtil.isNullEmpty(userDto.getLongEW())) {
            user.setLongEW(userDto.getLongEW());
         }

         if (!HUtil.isNullEmpty(userDto.getTimeZone())) {
            user.setTimeZone(userDto.getTimeZone());
         }

         if (!HUtil.isNullEmpty(userDto.getDeviceId())) {
            user.setDeviceId(userDto.getDeviceId());
         }

         if (!HUtil.isNullEmpty(userDto.getAppVersion())) {
            user.setAppVersion(userDto.getAppVersion());
         }

         if (!HUtil.isNullEmpty(userDto.getAndroidVersion())) {
            user.setAndroidVersion(userDto.getAndroidVersion());
         }

         this.userRepo.save(user);
         response.setResultCode(1);
         response.setMessage("Successfully.");
         response.setData(Arrays.asList());
      } else {
         response.setResultCode(5);
         response.setMessage("User not found");
         response.setData(Arrays.asList());
      }

      return response;
   }

   public void deleteUser(Integer userId) {
      User user = (User)this.userRepo.findById(userId).orElseThrow(() -> {
         return new ResourceNotFoundException("user", "id", (long)userId);
      });
      user.getRoles().clear();
      this.userRepo.delete(user);
   }

   public Boolean checkMobileNumberExit(String mobile) {
      boolean isExit = false;
      if (HUtil.isValidString(mobile)) {
         Optional<User> findByMobile = this.userRepo.findByMobile(mobile);
         if (findByMobile != null && !findByMobile.isEmpty() && findByMobile.isPresent()) {
            isExit = true;
         }
      }

      return isExit;
   }

   public Response loginUser(UserDto userDto) {
      String mobile = userDto.getMobile();
      String pass = userDto.getPassword();
      boolean isUserVerified = false;
      boolean isUserActive = false;
      Response response = new Response();
      response.setErrorMessage("");
      response.setStatus(HttpStatus.OK);
      if (HUtil.isValidString(mobile) && HUtil.isValidString(pass)) {
         Optional<User> userDetail = this.userRepo.findByMobileAndDcrptpassword(mobile, pass);
         if (userDetail.isPresent()) {
            UserDto loginUser = (UserDto)this.modelMapper.map(userDetail.get(), UserDto.class);
            UserResponse logedInUser = (UserResponse)this.modelMapper.map(loginUser, UserResponse.class);
            isUserVerified = loginUser.isUserVerified();
            isUserActive = loginUser.isUserActive();
            if (isUserActive) {
               if (isUserVerified) {
                  response.setResultCode(1);
                  response.setMessage("Successfully");
                  response.setData(Arrays.asList(logedInUser));
               } else {
                  String otpResponse = "";
                  response.setData(Arrays.asList(logedInUser));
                  otpResponse = this.sendOTPForLoginSignup(mobile);
                  if (otpResponse.equalsIgnoreCase("COUNTGT")) {
                     response.setResultCode(10);
                  } else if (otpResponse.equalsIgnoreCase("EXCEPTION")) {
                     response.setResultCode(11);
                  } else {
                     response.setResultCode(12);
                  }

                  response.setMessage(otpResponse);
               }
            } else {
               response.setResultCode(3);
               response.setMessage("User is not active");
               response.setData(Arrays.asList());
            }
         } else {
            Boolean checkMobileNumberExit = this.checkMobileNumberExit(mobile);
            if (checkMobileNumberExit) {
               response.setResultCode(4);
               response.setMessage("User password is Wrong");
               response.setData(Arrays.asList());
            } else {
               response.setResultCode(5);
               response.setMessage("User not found");
               response.setData(Arrays.asList());
            }
         }
      } else {
         response.setResultCode(6);
         response.setMessage("In valid input parameter");
         response.setData(Arrays.asList());
      }

      return response;
   }

   public Response updatePassword(UserDto userDto) {
      String mobile = userDto.getMobile();
      String pass = userDto.getPassword();
      boolean isUserActive = false;
      Response response = new Response();
      response.setErrorMessage("");
      response.setStatus(HttpStatus.OK);
      if (HUtil.isValidString(mobile)) {
         Optional<User> userByMobile = this.userRepo.findByMobile(mobile);
         if (userByMobile.isPresent()) {
            User user = (User)userByMobile.get();
            isUserActive = user.isUserActive();
            if (isUserActive) {
               user.setDcrptpassword(pass);
               user.setPassword(this.passwordEncoder.encode(pass));
               user.setUpdatedDate(new Date());
               this.userRepo.save(user);
               response.setResultCode(1);
               response.setMessage("Successfully.");
               response.setData(Arrays.asList());
            } else {
               response.setResultCode(3);
               response.setMessage("user is not active.");
               response.setData(Arrays.asList());
            }
         } else {
            response.setResultCode(5);
            response.setMessage("User not found");
            response.setData(Arrays.asList());
         }
      } else {
         response.setResultCode(6);
         response.setMessage("In valid input parameter");
         response.setData(Arrays.asList());
      }

      return response;
   }

   public Response sendOTP(String mobile) {
      String random = HUtil.getRandomNumberString();
      OTP otp = new OTP();
      otp.setMobile(mobile);
      otp.setOtp(random);
      otp.setCreatedDate(new Date());
      otp.setUpdatedDate(new Date());
      otp.setCount(1);
      int count = 1;
      String smsSend = "";
      Response response = new Response();
      response.setErrorMessage("");
      response.setStatus(HttpStatus.OK);
      boolean isUserActive = false;
      Optional<User> userByMobile = this.userRepo.findByMobile(mobile);
      if (userByMobile.isPresent()) {
         User user = (User)userByMobile.get();
         isUserActive = user.isUserActive();
         if (isUserActive) {
            Optional<OTP> findByMobile = this.otpRepo.findByMobile(mobile);
            if (findByMobile.isPresent()) {
               OTP o = (OTP)findByMobile.get();
               count = o.getCount();
               Date d2 = o.getUpdatedDate();
               Date d1 = o.getCreatedDate();
               long diff = (new Date()).getTime() - d1.getTime();
               long diffMinutes = diff / 60000L;
               if (diffMinutes > 15L) {
                  count = 0;
               }

               ++count;
               if (count < 4) {
                  o.setCreatedDate(d2);
               }

               o.setOtp(random);
               o.setUpdatedDate(new Date());
               o.setCount(count);
               this.otpRepo.save(o);
            } else {
               this.otpRepo.save(otp);
            }

            try {
               if (count > 3) {
                  response.setResultCode(10);
                  response.setMessage("COUNTGT");
                  response.setData(Arrays.asList());
               } else {
                  smsSend = this.sendSMS.sendSms(mobile, random);
                  response.setResultCode(1);
                  response.setMessage(smsSend);
                  response.setData(Arrays.asList());
               }
            } catch (Exception var18) {
               response.setResultCode(11);
               response.setMessage(var18.getMessage());
               response.setData(Arrays.asList());
            }
         } else {
            response.setResultCode(3);
            response.setMessage("User is not active");
            response.setData(Arrays.asList());
         }
      } else {
         response.setResultCode(5);
         response.setMessage("User not found");
         response.setData(Arrays.asList());
      }

      return response;
   }

   public Response validateOTP(OTPDto otpDto) {
      String mobile = otpDto.getMobile();
      String otp = otpDto.getOtp();
      Optional<OTP> findByMobileAndOtp = this.otpRepo.findByMobileAndOtp(mobile, otp);
      Response response = new Response();
      response.setErrorMessage("");
      response.setStatus(HttpStatus.OK);
      if (findByMobileAndOtp.isPresent()) {
         Optional<User> userByMobile = this.userRepo.findByMobile(mobile);
         if (userByMobile.isPresent()) {
            User user = (User)userByMobile.get();
            if (!user.isUserVerified()) {
               user.setUserVerified(true);
               user.setUpdatedDate(new Date());
               this.userRepo.save(user);
               response.setResultCode(1);
               response.setMessage("Successfully");
            } else {
               response.setResultCode(13);
               response.setMessage("valid otp but user already verified.");
            }
         }

         response.setData(Arrays.asList());
      } else {
         response.setResultCode(14);
         response.setMessage("invalid otp");
         response.setData(Arrays.asList());
      }

      return response;
   }

   public String sendOTPForLoginSignup(String mobile) {
      String random = HUtil.getRandomNumberString();
      OTP otp = new OTP();
      otp.setMobile(mobile);
      otp.setOtp(random);
      otp.setCreatedDate(new Date());
      otp.setUpdatedDate(new Date());
      otp.setCount(1);
      int count = 1;
      String smsSend = "";
      Optional<OTP> findByMobile = this.otpRepo.findByMobile(mobile);
      if (findByMobile.isPresent()) {
         OTP o = (OTP)findByMobile.get();
         count = o.getCount();
         Date d2 = o.getUpdatedDate();
         Date d1 = o.getCreatedDate();
         long diff = (new Date()).getTime() - d1.getTime();
         long diffMinutes = diff / 60000L;
         if (diffMinutes > 15L) {
            count = 0;
         }

         ++count;
         if (count < 4) {
            o.setCreatedDate(d2);
         }

         o.setOtp(random);
         o.setUpdatedDate(new Date());
         o.setCount(count);
         this.otpRepo.save(o);
      } else {
         this.otpRepo.save(otp);
      }

      try {
         if (count > 3) {
            smsSend = "COUNTGT";
         } else {
            smsSend = this.sendSMS.sendSms(mobile, random);
         }
      } catch (Exception var14) {
         smsSend = "EXCEPTION";
      }

      return smsSend;
   }

   public Response deactivateUser(String mobile) {
      Response response = new Response();
      response.setErrorMessage("");
      response.setStatus(HttpStatus.OK);
      Optional<User> findByMobile = this.userRepo.findByMobile(mobile);
      if (findByMobile.isPresent()) {
         User user = (User)findByMobile.get();
         boolean isUserActive = user.isUserActive();
         if (isUserActive) {
            user.setUserActive(false);
            user.setUpdatedDate(new Date());
            this.userRepo.save(user);
            response.setResultCode(1);
            response.setMessage("successfully.");
            response.setData(Arrays.asList());
         } else {
            response.setResultCode(7);
            response.setMessage("User already Deactivate");
            response.setData(Arrays.asList());
         }
      } else {
         response.setResultCode(5);
         response.setMessage("User Not Found.");
         response.setData(Arrays.asList());
      }

      return response;
   }

   public Response activateUser(String mobile) {
      Response response = new Response();
      response.setErrorMessage("");
      response.setStatus(HttpStatus.OK);
      Optional<User> findByMobile = this.userRepo.findByMobile(mobile);
      if (findByMobile.isPresent()) {
         User user = (User)findByMobile.get();
         boolean isUserActive = user.isUserActive();
         if (!isUserActive) {
            user.setUserActive(true);
            user.setUpdatedDate(new Date());
            this.userRepo.save(user);
            response.setResultCode(1);
            response.setMessage("successfully.");
            response.setData(Arrays.asList());
         } else {
            response.setResultCode(8);
            response.setMessage("User already Aactive");
            response.setData(Arrays.asList());
         }
      } else {
         response.setResultCode(5);
         response.setMessage("User Not Found.");
         response.setData(Arrays.asList());
      }

      return response;
   }
}
