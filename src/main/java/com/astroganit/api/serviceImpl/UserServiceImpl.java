package com.astroganit.api.serviceImpl;

import com.astroganit.api.constant.AppConstant;
import com.astroganit.api.entities.OTP;
import com.astroganit.api.entities.OtpNew;
import com.astroganit.api.entities.Role;
import com.astroganit.api.entities.User;
import com.astroganit.api.exception.ResourceNotFoundException;
import com.astroganit.api.payload.OTPDto;
import com.astroganit.api.payload.Response;
import com.astroganit.api.payload.UserDto;
import com.astroganit.api.payload.UserResponse;
import com.astroganit.api.repository.OTPRepo;
import com.astroganit.api.repository.OtpRepository;
import com.astroganit.api.repository.RoleRepo;
import com.astroganit.api.repository.UserRepo;
import com.astroganit.api.service.UserService;
import com.astroganit.api.util.HUtil;
import com.astroganit.api.util.SendSMS;
import com.astroganit.lib.panchang.util.AppEnums;
import com.astroganit.lib.panchang.util.AppResultConstant;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.Optional;
import java.util.Random;

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
	private OtpRepository otpRepository;
	@Autowired
	private SendSMS sendSMS;
	@Autowired
	private PasswordEncoder passwordEncoder;

	public UserDto registerNewUser(UserDto userDto) {
		User user = (User) this.modelMapper.map(userDto, User.class);
		user.setCreatedDate(new Date());
		user.setUpdatedDate(new Date());
		user.setPassword(this.passwordEncoder.encode(user.getPassword()));
		user.setDcrptpassword(user.getDcrptpassword());
		user.setUserActive(true);
		Role role = (Role) this.roleRepo.findById(AppConstant.NORMAL_USER).get();
		user.getRoles().add(role);
		User newUser = (User) this.userRepo.save(user);
		return (UserDto) this.modelMapper.map(newUser, UserDto.class);
	}

	/*
	 * public Response updateUserProfile(UserDto userDto, String mobile) { Response
	 * response = new Response(); response.setErrorMessage("");
	 * response.setStatus(HttpStatus.OK); if (HUtil.isNullEmpty(mobile)) { mobile =
	 * "0000000000"; }
	 * 
	 * Optional<User> findByMobile = this.userRepo.findByMobile(mobile); if
	 * (findByMobile.isPresent()) { User user = (User) findByMobile.get(); if
	 * (!HUtil.isNullEmpty(userDto.getName())) { user.setName(userDto.getName()); }
	 * 
	 * if (!HUtil.isNullEmpty(userDto.getAbout())) {
	 * user.setAbout(userDto.getAbout()); }
	 * 
	 * if (!HUtil.isNullEmpty(userDto.getEmail())) {
	 * user.setEmail(userDto.getEmail()); }
	 * 
	 * if (!HUtil.isNullEmpty(userDto.getGender())) {
	 * user.setGender(userDto.getGender()); }
	 * 
	 * if (!HUtil.isNullEmpty(userDto.getMaritalStatus())) {
	 * user.setMaritalStatus(userDto.getMaritalStatus()); }
	 * 
	 * if (!HUtil.isNullEmpty(userDto.getPlace())) {
	 * user.setPlace(userDto.getPlace()); }
	 * 
	 * if (!HUtil.isNullEmpty(userDto.getState())) {
	 * user.setState(userDto.getState()); }
	 * 
	 * if (!HUtil.isNullEmpty(userDto.getCountry())) {
	 * user.setCountry(userDto.getCountry()); }
	 * 
	 * if (!HUtil.isNullEmpty(userDto.getDayBirth())) {
	 * user.setDayBirth(userDto.getDayBirth()); }
	 * 
	 * if (!HUtil.isNullEmpty(userDto.getMonthBirth())) {
	 * user.setMonthBirth(userDto.getMonthBirth()); }
	 * 
	 * if (!HUtil.isNullEmpty(userDto.getYearBirth())) {
	 * user.setYearBirth(userDto.getYearBirth()); }
	 * 
	 * if (!HUtil.isNullEmpty(userDto.getHourBirth())) {
	 * user.setHourBirth(userDto.getHourBirth()); }
	 * 
	 * if (!HUtil.isNullEmpty(userDto.getMinuteBirth())) {
	 * user.setMinuteBirth(userDto.getMinuteBirth()); }
	 * 
	 * if (!HUtil.isNullEmpty(userDto.getSecondBirth())) {
	 * user.setSecondBirth(userDto.getSecondBirth()); }
	 * 
	 * if (!HUtil.isNullEmpty(userDto.getLatitude())) {
	 * user.setLatitude(userDto.getLatitude()); }
	 * 
	 * if (!HUtil.isNullEmpty(userDto.getLatDeg())) {
	 * user.setLatDeg(userDto.getLatDeg()); }
	 * 
	 * if (!HUtil.isNullEmpty(userDto.getLatMin())) {
	 * user.setLatMin(userDto.getLatMin()); }
	 * 
	 * if (!HUtil.isNullEmpty(userDto.getLatNS())) {
	 * user.setLatNS(userDto.getLatNS()); }
	 * 
	 * if (!HUtil.isNullEmpty(userDto.getLongitude())) {
	 * user.setLongitude(userDto.getLongitude()); }
	 * 
	 * if (!HUtil.isNullEmpty(userDto.getLongDeg())) {
	 * user.setLongDeg(userDto.getLongDeg()); }
	 * 
	 * if (!HUtil.isNullEmpty(userDto.getLongMin())) {
	 * user.setLongMin(userDto.getLongMin()); }
	 * 
	 * if (!HUtil.isNullEmpty(userDto.getLongEW())) {
	 * user.setLongEW(userDto.getLongEW()); }
	 * 
	 * if (!HUtil.isNullEmpty(userDto.getTimeZone())) {
	 * user.setTimeZone(userDto.getTimeZone()); }
	 * 
	 * if (!HUtil.isNullEmpty(userDto.getDeviceId())) {
	 * user.setDeviceId(userDto.getDeviceId()); }
	 * 
	 * if (!HUtil.isNullEmpty(userDto.getAppVersion())) {
	 * user.setAppVersion(userDto.getAppVersion()); }
	 * 
	 * if (!HUtil.isNullEmpty(userDto.getAndroidVersion())) {
	 * user.setAndroidVersion(userDto.getAndroidVersion()); }
	 * 
	 * this.userRepo.save(user); response.setResultCode(1);
	 * response.setMessage("Successfully."); response.setData(Arrays.asList()); }
	 * else { response.setResultCode(5); response.setMessage("User not found");
	 * response.setData(Arrays.asList()); }
	 * 
	 * return response; }
	 */

	public void deleteUser(Long userId) {
		User user = (User) this.userRepo.findById(userId).orElseThrow(() -> {
			return new ResourceNotFoundException("user", "id", (long) userId);
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

	public Boolean checkLoginIdExit(String loginID) {
		boolean isExit = false;
		if (HUtil.isValidString(loginID)) {
			Optional<User> findByLoginID = this.userRepo.findByLoginId(loginID);
			if (findByLoginID != null && !findByLoginID.isEmpty() && findByLoginID.isPresent()) {
				isExit = true;
			}
		}

		return isExit;
	}

	public Response loginUser(UserDto userDto) {

		Response response = new Response();
		response.setErrorMessage("");
		response.setStatus(HttpStatus.OK);
		String loginId = userDto.getLoginId();
		String password = userDto.getPassword();
		System.out.println(loginId + "--" + password);

		if (!HUtil.isValidString(loginId) || !HUtil.isValidString(password)) {
			response.setResultCode(AppResultConstant.INVALID_PARAMETER);
			response.setMessage("Invalid input parameters");
			return response;
		}

		// STEP 1: Find user
		Optional<User> userOpt = userRepo.findByLoginId(loginId);

		if (userOpt.isEmpty()) {
			response.setResultCode(AppResultConstant.USER_NOT_FOUND); // user not found
			response.setMessage("User not found");
			return response;
		}

		User user = userOpt.get();
		response.setData(Arrays.asList(modelMapper.map(user, UserResponse.class)));
		// STEP 2: Check password
		if (!user.getDcrptpassword().equals(password)) {
			response.setResultCode(AppResultConstant.INCORRECT_PASSWORD);
			response.setMessage("Incorrect password");
			return response;
		}

		// STEP 3: Check user active
		if (!user.isUserActive()) {
			response.setResultCode(AppResultConstant.USER_NOT_ACTIVE);
			response.setMessage("User is not active");
			return response;
		}

		// STEP 4: If not verified, send OTP
		if (!user.isUserVerified()) {
			int otpStatus = generateOtp(loginId);

			switch (otpStatus) {
			case AppResultConstant.LIMIT_REACHED:
				response.setMessage("OTP request limit reached. Try again after 10 minutes.");
				break;
			case AppResultConstant.SMS_ERROR:
				response.setMessage("SMS sending failed");
				break;
			case AppResultConstant.SERVER_ERROR:
				response.setMessage("Server Error");
			default:
				response.setMessage("OTP sent successfully");
			}
			response.setResultCode(otpStatus);
			return response;
		}

		// SUCCESS LOGIN
		response.setResultCode(AppResultConstant.SUCCESSFUL);
		response.setMessage("Successfully");

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
				User user = (User) userByMobile.get();
				isUserActive = user.isUserActive();
				if (isUserActive) {
					user.setDcrptpassword(pass);
					user.setPassword(this.passwordEncoder.encode(pass));
					user.setUpdatedDate(new Date());
					this.userRepo.save(user);
					response.setResultCode(AppResultConstant.SUCCESSFUL);
					response.setMessage("Successfully.");
					response.setData(Arrays.asList());
				} else {
					response.setResultCode(AppResultConstant.USER_NOT_ACTIVE);
					response.setMessage("user is not active.");
					response.setData(Arrays.asList());
				}
			} else {
				response.setResultCode(AppResultConstant.USER_NOT_FOUND);
				response.setMessage("User not found");
				response.setData(Arrays.asList());
			}
		} else {
			response.setResultCode(AppResultConstant.INVALID_PARAMETER);
			response.setMessage("In valid input parameter");
			response.setData(Arrays.asList());
		}

		return response;
	}

	/*
	 * public Response sendOTP(String mobile) { String random =
	 * HUtil.getRandomNumberString(); // String random = "1234"; OTP otp = new
	 * OTP(); otp.setMobile(mobile); otp.setOtp(random); otp.setCreatedDate(new
	 * Date()); otp.setUpdatedDate(new Date()); otp.setCount(1); int count = 1;
	 * String smsSend = ""; Response response = new Response();
	 * response.setErrorMessage(""); response.setStatus(HttpStatus.OK); boolean
	 * isUserActive = false; Optional<User> userByMobile =
	 * this.userRepo.findByMobile(mobile); if (userByMobile.isPresent()) { User user
	 * = (User) userByMobile.get(); isUserActive = user.isUserActive(); if
	 * (isUserActive) { Optional<OTP> findByMobile =
	 * this.otpRepo.findByMobile(mobile); if (findByMobile.isPresent()) { OTP o =
	 * (OTP) findByMobile.get(); count = o.getCount(); Date d2 = o.getUpdatedDate();
	 * Date d1 = o.getCreatedDate(); long diff = (new Date()).getTime() -
	 * d1.getTime(); long diffMinutes = diff / 60000L; if (diffMinutes > 15L) {
	 * count = 0; }
	 * 
	 * ++count; if (count < 4) { o.setCreatedDate(d2); }
	 * 
	 * o.setOtp(random); o.setUpdatedDate(new Date()); o.setCount(count);
	 * this.otpRepo.save(o); } else { this.otpRepo.save(otp); }
	 * 
	 * try { if (count > 3) { response.setResultCode(10);
	 * response.setMessage("COUNTGT"); response.setData(Arrays.asList()); } else {
	 * smsSend = this.sendSMS.sendOtp(mobile, random); response.setResultCode(1);
	 * response.setMessage(smsSend); response.setData(Arrays.asList()); } } catch
	 * (Exception var18) { response.setResultCode(11);
	 * response.setMessage(var18.getMessage()); response.setData(Arrays.asList()); }
	 * } else { response.setResultCode(3);
	 * response.setMessage("User is not active"); response.setData(Arrays.asList());
	 * } } else { response.setResultCode(5); response.setMessage("User not found");
	 * response.setData(Arrays.asList()); }
	 * 
	 * return response; }
	 */

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
				User user = (User) userByMobile.get();
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
			OTP o = (OTP) findByMobile.get();
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
				smsSend = this.sendSMS.sendOtp(mobile, random);
				// smsSend = "Success";
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
			User user = (User) findByMobile.get();
			boolean isUserActive = user.isUserActive();
			if (isUserActive) {
				user.setUserActive(false);
				user.setUpdatedDate(new Date());
				this.userRepo.save(user);
				response.setResultCode(AppResultConstant.SUCCESSFUL);
				response.setMessage("successfully.");
				response.setData(Arrays.asList());
			} else {
				response.setResultCode(AppResultConstant.USER_ALREADY_DEACTIVATE);
				response.setMessage("User already Deactivate");
				response.setData(Arrays.asList());
			}
		} else {
			response.setResultCode(AppResultConstant.USER_NOT_FOUND);
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
			User user = (User) findByMobile.get();
			boolean isUserActive = user.isUserActive();
			if (!isUserActive) {
				user.setUserActive(true);
				user.setUpdatedDate(new Date());
				this.userRepo.save(user);
				response.setResultCode(AppResultConstant.SUCCESSFUL);
				response.setMessage("successfully.");
				response.setData(Arrays.asList());
			} else {
				response.setResultCode(AppResultConstant.USER_ALREADY_ACTIVE);
				response.setMessage("User already Aactive");
				response.setData(Arrays.asList());
			}
		} else {
			response.setResultCode(AppResultConstant.USER_NOT_FOUND);
			response.setMessage("User Not Found.");
			response.setData(Arrays.asList());
		}

		return response;
	}

	public int generateOtp(String mobile) {

		String otpValue = HUtil.getRandomNumberString();
		LocalDateTime now = LocalDateTime.now();

		OtpNew otp = otpRepository.findByMobile(mobile).orElseGet(() -> {
			OtpNew o = new OtpNew();
			o.setMobile(mobile);
			return o;
		});

		// Check time window: 10 min
		if (otp.getLastSentAt().isBefore(now.minusMinutes(10))) {
			otp.setSendCount(0); // reset old count
		}

		// Limit: Maximum 3 OTP
		if (otp.getSendCount() >= 3) {
			return AppResultConstant.LIMIT_REACHED;
		}

		try {
			String otpRes = sendSMS.sendOtp(mobile, otpValue);
			if (otpRes.equals("Success")) {
				// Update OTP entry
				otp.setOtpCode(otpValue);
				otp.setCreatedAt(now);
				otp.setExpiresAt(now.plusMinutes(5));
				otp.setAttempts(0);
				otp.setUsed(false);

				otp.setSendCount(otp.getSendCount() + 1);
				otp.setLastSentAt(now);

				otpRepository.save(otp);

				return AppResultConstant.OTP_SENT;
			} else {
				return AppResultConstant.SMS_ERROR;
			}

		} catch (Exception e) {
			return AppResultConstant.SERVER_ERROR;
		}
	}

	public Response sendOTP(String mobile) {

		Response response = new Response();
		response.setErrorMessage("");
		response.setStatus(HttpStatus.OK);

		// Time window start
		LocalDateTime windowStart = LocalDateTime.now().minusMinutes(10);

		// Fetch existing OTP record (if any)
		OtpNew otp = otpRepository.findByMobile(mobile).orElse(null);

		if (otp != null) {

			// If last OTP was created within 10 min → apply limit
			if (otp.getCreatedAt().isAfter(windowStart)) {

				if (otp.getSendCount() >= 3) {
					response.setMessage("OTP request limit reached. Try again after 10 minutes.");
					response.setResultCode(AppResultConstant.LIMIT_REACHED);
					return response;
				}

			} else {
				// Last OTP was older than 10 minutes → reset counter
				otp.setSendCount(0);
			}

		} else {
			otp = new OtpNew();
			otp.setMobile(mobile);
		}

		// Generate OTP
		String otpValue = HUtil.getRandomNumberString();

		try {
			String otpRes = sendSMS.sendOtp(mobile, otpValue);
			if (otpRes.equals("Success")) {
				// Update OTP entry
				otp.setOtpCode(otpValue);
				otp.setCreatedAt(LocalDateTime.now());
				otp.setExpiresAt(LocalDateTime.now().plusMinutes(5));
				otp.setAttempts(0);
				otp.setUsed(false);
				// Increment OTP count
				otp.setSendCount(otp.getSendCount() + 1);
				otpRepository.save(otp);
				response.setMessage("OTP sent successfully");
				response.setResultCode(AppResultConstant.OTP_SENT);
				return response;
			} else {
				response.setMessage("SMS sending failed");
				response.setResultCode(AppResultConstant.SMS_ERROR);
				return response;
			}

		} catch (Exception e) {
			response.setMessage("SMS sending failed");
			response.setResultCode(AppResultConstant.SERVER_ERROR);
			return response;
		}
	}

	public Response verifyOtp(String mobile, String otpCode) {
		Response response = new Response();
		response.setErrorMessage("");
		response.setStatus(HttpStatus.OK);
		Optional<OtpNew> otpOpt = otpRepository.findByMobile(mobile);

		if (!otpOpt.isPresent()) {
			response.setMessage("No OTP found");
			response.setResultCode(AppResultConstant.OTP_NOT_FOUND);
			return response;
		}
		OtpNew latestOtp = otpOpt.get();

		// Already used
		if (latestOtp.isUsed()) {
			response.setMessage("OTP already used");
			response.setResultCode(AppResultConstant.OTP_ALREADY_USED);
			return response;
		}

		// Expired OTP
		if (LocalDateTime.now().isAfter(latestOtp.getExpiresAt())) {
			response.setMessage("OTP expired");
			response.setResultCode(AppResultConstant.OTP_EXPIRED);
			return response;
		}

		// Check max attempts (before increment)
		/*
		 * if (latestOtp.getAttempts() >= 3) {
		 * response.setMessage("Maximum attempts reached");
		 * response.setResultCode(AppResultConstant.MAX_ATTEMPTS_REACHED); return
		 * response; }
		 */

		// Increment attempts
		latestOtp.setAttempts(latestOtp.getAttempts() + 1);

		// Invalid OTP
		if (!latestOtp.getOtpCode().equals(otpCode)) {
			otpRepository.save(latestOtp);
			response.setMessage("Invalid OTP");
			response.setResultCode(AppResultConstant.OTP_INVALID);
			return response;
		}

		// Valid OTP
		latestOtp.setUsed(true);
		otpRepository.save(latestOtp);
		Optional<User> loginUser = this.userRepo.findByLoginId(mobile);
		if (loginUser.isPresent()) {
			User user = loginUser.get();
			user.setUserVerified(true);
			userRepo.save(user);
		}

		response.setMessage("OTP verified successfully");
		response.setResultCode(AppResultConstant.OTP_VERIFIED_SUCCESSFULLY);
		return response;
	}

	public Response updateUserProfile(UserDto userDto) {
		Response response = new Response();
		response.setErrorMessage("");
		response.setStatus(HttpStatus.OK);
		/*
		 * if (HUtil.isNullEmpty(mobile)) { mobile = "0000000000"; }
		 */

		Optional<User> userDetail = this.userRepo.findByLoginIdAndDcrptpassword(userDto.getLoginId(),
				userDto.getPassword());
		if (userDetail.isPresent()) {
			User user = userDetail.get();
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
			user.setUpdatedDate(new Date());
			this.userRepo.save(user);
			response.setResultCode(AppResultConstant.SUCCESSFUL);
			response.setMessage("Successfully.");
			response.setData(Arrays.asList());
		} else {
			response.setResultCode(AppResultConstant.USER_NOT_FOUND);
			response.setMessage("User not found");
			response.setData(Arrays.asList());
		}

		return response;
	}
}
