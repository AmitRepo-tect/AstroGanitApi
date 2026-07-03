package com.astroganit.api.serviceImpl;

import com.astroganit.api.constant.AppConstant;
import com.astroganit.api.entities.OTP;
import com.astroganit.api.entities.OtpNew;
import com.astroganit.api.entities.Role;
import com.astroganit.api.entities.User;
import com.astroganit.api.exception.AppException;
import com.astroganit.api.exception.ResourceNotFoundException;
import com.astroganit.api.payload.OTPDto;
import com.astroganit.api.payload.Response;
import com.astroganit.api.payload.ResponseNew;
import com.astroganit.api.payload.UserDto;
import com.astroganit.api.payload.UserResponse;
import com.astroganit.api.repository.OTPRepo;
import com.astroganit.api.repository.OtpRepository;
import com.astroganit.api.repository.RoleRepo;
import com.astroganit.api.repository.UserRepo;
import com.astroganit.api.service.UserService;
import com.astroganit.api.util.HUtil;
import com.astroganit.api.util.ResultCode;
import com.astroganit.api.util.SendSMS;
import com.astroganit.lib.panchang.util.AppResultConstant;
import com.astroganit.security.JwtTokenHelper;

import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
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
	@Autowired
	private JwtTokenHelper jwtTokenHelper;
	@Autowired
	private UserDetailsService userDetailService;
	@Autowired
	private OtpService otpService;
	/*
	 * @Autowired private StringRedisTemplate redisTemplate;
	 */

	final int OTP_LIMIT = 3;
	final int OTP_WINDOW_MIN = 10; // minutes
	final int OTP_EXPIRY_MIN = 5; // minutes
	final int OTP_COOLDOWN_SEC = 30; // seconds

	@Override
	@Transactional
	public ResponseNew<List<UserResponse>> requestLoginOtp(UserDto userDto) {

		ResponseNew<List<UserResponse>> response = new ResponseNew<>();
		String loginId = userDto.getLoginId();

		// 1️⃣ Validate input
		if (!HUtil.isValidString(loginId)) {
			throw new AppException(ResultCode.INVALID_PARAMETER);
		}

		// 2️⃣ Find or create user
		User user = userRepo.findByLoginId(loginId).orElseGet(() -> {
			User newUser = new User();
			newUser.setName("");
			newUser.setLoginId(loginId);
			newUser.setMobile(loginId);
			newUser.setUserActive(true);
			newUser.setUserVerified(false);
			newUser.setCreatedDate(new Date());
			String defaultPass = "astro_2026";
			newUser.setPassword(passwordEncoder.encode(defaultPass)); // only hash
			newUser.setMobilecc(userDto.getMobilecc());
			newUser.setAndroidVersion(userDto.getAndroidVersion());
			newUser.setAppVersion(userDto.getAppVersion());
			newUser.setDeviceId(userDto.getDeviceId());
			return userRepo.save(newUser);
		});

		// 3️⃣ Check active
		if (!user.isUserActive()) {
			throw new AppException(ResultCode.USER_NOT_ACTIVE);
		}

		// 4️⃣ Generate OTP
		generateOtp(loginId);
		// 5️⃣ Prepare response
		response.setStatus(HttpStatus.OK);
		response.setStatusCode(HttpStatus.OK.value());
		response.setResultCode(ResultCode.SUCCESS.getCode());
		response.setMessage(ResultCode.SUCCESS.getMessage());
		response.setData(Arrays.asList(modelMapper.map(user, UserResponse.class)));

		return response;
	}

	public void generateOtp(String mobile) {

		LocalDateTime now = LocalDateTime.now();
		String otpValue = HUtil.getRandomNumberString();
		System.out.println(otpValue);
		// 🔒 Lock row to prevent race conditions
		OtpNew otp = otpRepository.findByMobile(mobile).orElseGet(() -> {
			OtpNew o = new OtpNew();
			o.setMobile(mobile);
			o.setSendCount(0);
			o.setAttempts(0);
			o.setUsed(false);
			return o;
		});
		// 🔹 Reset send count after window
		if (otp.getLastSentAt() == null || otp.getLastSentAt().isBefore(now.minusMinutes(OTP_WINDOW_MIN))) {
			otp.setSendCount(0);
		}
		// 🔒 Check max OTPs sent
		if (otp.getSendCount() >= OTP_LIMIT) {
			throw new AppException(ResultCode.LIMIT_REACHED.getCode(),
					"Maximum OTP requests reached. Please try again later.");
		}
		// ⏱️ Cooldown check
		if (otp.getLastSentAt() != null && otp.getLastSentAt().isAfter(now.minusSeconds(OTP_COOLDOWN_SEC))) {
			long secondsLeft = OTP_COOLDOWN_SEC - Duration.between(otp.getLastSentAt(), now).getSeconds();
			throw new AppException(ResultCode.TOO_SOON.getCode(),
					"OTP already sent. Please wait " + secondsLeft + " seconds before requesting a new one.");
		}

		// 📩 Send OTP via SMS
		sendSMS.sendOtp(mobile, otpValue);
		// 🔐 Hash and store OTP
		otp.setOtpCode(passwordEncoder.encode(otpValue));
		otp.setCreatedAt(now);
		otp.setExpiresAt(now.plusMinutes(OTP_EXPIRY_MIN));
		otp.setAttempts(0);
		otp.setUsed(false);
		otp.setSendCount(otp.getSendCount() + 1);
		otp.setLastSentAt(now);
		otpRepository.save(otp);
	}

	@Transactional
	public ResponseNew<Void> resendOTP(String mobile) {

		LocalDateTime now = LocalDateTime.now();
		String otpValue = HUtil.getRandomNumberString();
		// 🔒 Lock row to prevent race conditions
		OtpNew otp = otpRepository.findByMobile(mobile).orElseGet(() -> {
			OtpNew o = new OtpNew();
			o.setMobile(mobile);
			o.setSendCount(0);
			o.setAttempts(0);
			o.setUsed(false);
			return o;
		});
		// 🔹 Reset send count after window
		if (otp.getLastSentAt() == null || otp.getLastSentAt().isBefore(now.minusMinutes(OTP_WINDOW_MIN))) {
			otp.setSendCount(0);
		}
		// 🔒 Check max OTPs sent
		if (otp.getSendCount() >= OTP_LIMIT) {
			throw new AppException(ResultCode.LIMIT_REACHED.getCode(),
					"Maximum OTP requests reached. Please try again later.");
		}
		// ⏱️ Cooldown check
		if (otp.getLastSentAt() != null && otp.getLastSentAt().isAfter(now.minusSeconds(OTP_COOLDOWN_SEC))) {
			long secondsLeft = OTP_COOLDOWN_SEC - Duration.between(otp.getLastSentAt(), now).getSeconds();
			throw new AppException(ResultCode.TOO_SOON.getCode(),
					"OTP already sent. Please wait " + secondsLeft + " seconds before requesting a new one.");
		}

		// 📩 Send OTP via SMS
		sendSMS.sendOtp(mobile, otpValue);
		// 🔐 Hash and store OTP
		otp.setOtpCode(passwordEncoder.encode(otpValue));
		otp.setCreatedAt(now);
		otp.setExpiresAt(now.plusMinutes(OTP_EXPIRY_MIN));
		otp.setAttempts(0);
		otp.setUsed(false);
		otp.setSendCount(otp.getSendCount() + 1);
		otp.setLastSentAt(now);
		otpRepository.save(otp);
		ResponseNew<Void> response = new ResponseNew<Void>();
		response.setStatus(HttpStatus.OK);
		response.setStatusCode(HttpStatus.OK.value());
		response.setResultCode(ResultCode.SUCCESS.getCode());
		response.setMessage(ResultCode.SUCCESS.getMessage());
		return response;

	}

	@Transactional
	public ResponseNew<List<String>> verifyOtp(String mobile, String otpCode) {

		ResponseNew<List<String>> response = new ResponseNew<List<String>>();
		response.setStatus(HttpStatus.OK);

		OtpNew latestOtp = otpRepository.findByMobile(mobile)
				.orElseThrow(() -> new AppException(ResultCode.OTP_NOT_FOUND));

		// Already used
		if (latestOtp.isUsed()) {
			throw new AppException(ResultCode.OTP_ALREADY_USED);
		}

		// Expired
		if (LocalDateTime.now().isAfter(latestOtp.getExpiresAt())) {
			throw new AppException(ResultCode.OTP_EXPIRED);
		}

		// Max attempts
		if (latestOtp.getAttempts() >= 3) {
			throw new AppException(ResultCode.MAX_ATTEMPTS_REACHED);
		}

		// Increment attempts
		latestOtp.setAttempts(latestOtp.getAttempts() + 1);

		// ❗ Correct OTP check (IMPORTANT FIX)
		if (!passwordEncoder.matches(otpCode, latestOtp.getOtpCode())) {
			otpRepository.save(latestOtp);
			throw new AppException(ResultCode.OTP_INVALID);
		}

		// Mark used
		latestOtp.setUsed(true);
		otpRepository.save(latestOtp);

		// Verify user
		User user = userRepo.findByLoginId(mobile).orElseThrow(() -> new AppException(ResultCode.USER_NOT_FOUND));

		user.setUserVerified(true);
		userRepo.save(user);

		// Generate token
		String token = generateToken(mobile);

		response.setMessage("OTP verified successfully");
		response.setResultCode(ResultCode.SUCCESS.getCode());

		if (token != null) {
			response.setData(Arrays.asList(token));
		}

		return response;
	}

	String generateToken(String mobile) {
		try {
			UserDetails userDetails = userDetailService.loadUserByUsername(mobile);
			return jwtTokenHelper.generateToken(userDetails);
		} catch (Exception e) {
			throw new AppException(ResultCode.OTP_VERIFICATION_FAILED);
		}
	}

	@Transactional
	public ResponseNew<List<UserResponse>> updateUserProfileV1(UserDto userDto) {
		ResponseNew<List<UserResponse>> response = new ResponseNew<List<UserResponse>>();
		response.setStatus(HttpStatus.OK);
		// ✅ Get logged-in user (JWT आधारित)
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		if (auth == null || !auth.isAuthenticated() || "anonymousUser".equals(auth.getName())) {
			throw new AppException(ResultCode.UNAUTHORIZED);
		}
		String loginId = auth.getName();
		User user = userRepo.findByLoginId(loginId).orElseThrow(() -> new AppException(ResultCode.USER_NOT_FOUND));

		updateIfNotNull(userDto.getName(), user::setName);
		updateIfNotNull(userDto.getAbout(), user::setAbout);
		updateIfNotNull(userDto.getEmail(), user::setEmail);
		updateIfNotNull(userDto.getGender(), user::setGender);
		updateIfNotNull(userDto.getMaritalStatus(), user::setMaritalStatus);
		updateIfNotNull(userDto.getPlace(), user::setPlace);
		updateIfNotNull(userDto.getState(), user::setState);
		updateIfNotNull(userDto.getCountry(), user::setCountry);
		updateIfNotNull(userDto.getDayBirth(), user::setDayBirth);
		updateIfNotNull(userDto.getMonthBirth(), user::setMonthBirth);
		updateIfNotNull(userDto.getYearBirth(), user::setYearBirth);
		updateIfNotNull(userDto.getHourBirth(), user::setHourBirth);
		updateIfNotNull(userDto.getMinuteBirth(), user::setMinuteBirth);
		updateIfNotNull(userDto.getSecondBirth(), user::setSecondBirth);
		updateIfNotNull(userDto.getLatitude(), user::setLatitude);
		updateIfNotNull(userDto.getLatDeg(), user::setLatDeg);
		updateIfNotNull(userDto.getLatMin(), user::setLatMin);
		updateIfNotNull(userDto.getLatNS(), user::setLatNS);
		updateIfNotNull(userDto.getLongitude(), user::setLongitude);
		updateIfNotNull(userDto.getLongDeg(), user::setLongDeg);
		updateIfNotNull(userDto.getLongMin(), user::setLongMin);
		updateIfNotNull(userDto.getLongEW(), user::setLongEW);
		updateIfNotNull(userDto.getTimeZone(), user::setTimeZone);
		updateIfNotNull(userDto.getDeviceId(), user::setDeviceId);
		updateIfNotNull(userDto.getAppVersion(), user::setAppVersion);
		updateIfNotNull(userDto.getAndroidVersion(), user::setAndroidVersion);
		user.setUpdatedDate(new Date());
		// userRepo.save(user);

		response.setResultCode(ResultCode.SUCCESS.getCode());
		response.setMessage(ResultCode.PROFILE_UPDATE_SUCCESSFUL.getMessage());
		response.setData(Arrays.asList(modelMapper.map(user, UserResponse.class)));

		return response;
	}

	private void updateIfNotNull(String value, Consumer<String> setter) {
		if (!HUtil.isNullEmpty(value)) {
			setter.accept(value);
		}
	}
	@Transactional
	public ResponseNew<List<UserResponse>> getUserProfile() {

	    ResponseNew<List<UserResponse>> response = new ResponseNew<>();
	    response.setStatus(HttpStatus.OK);

	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();

	    if (auth == null || !auth.isAuthenticated() || "anonymousUser".equals(auth.getName())) {
	        throw new AppException(ResultCode.UNAUTHORIZED);
	    }

	    String loginId = auth.getName();

	    User user = userRepo.findByLoginId(loginId)
	            .orElseThrow(() -> new AppException(ResultCode.USER_NOT_FOUND));

	    response.setResultCode(ResultCode.SUCCESS.getCode());
	    response.setMessage("Profile fetched successfully");
	    response.setData(Arrays.asList(modelMapper.map(user, UserResponse.class)));

	    return response;
	}

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

	public void deleteUser(Long id) {
		User user = (User) this.userRepo.findById(id).orElseThrow(() -> {
			return new ResourceNotFoundException("user not found");
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

	@Override
	public Response loginUser(UserDto userDto) {

		Response response = new Response();
		response.setStatus(HttpStatus.OK);

		String loginId = userDto.getLoginId();
		String password = userDto.getPassword();

		// ✅ 1. Validate input
		if (!HUtil.isValidString(loginId) || !HUtil.isValidString(password)) {
			throw new AppException(ResultCode.INVALID_PARAMETER);
		}

		// ✅ 2. Find user
		User user = userRepo.findByLoginId(loginId).orElseThrow(() -> new AppException(ResultCode.USER_NOT_FOUND));

		// ✅ 3. Check password (SECURE)
		if (!passwordEncoder.matches(password, user.getPassword())) {
			throw new AppException(ResultCode.INCORRECT_PASSWORD);
		}

		// ✅ 4. Check active
		if (!user.isUserActive()) {
			throw new AppException(ResultCode.USER_NOT_ACTIVE);
		}

		// ✅ 5. If not verified → send OTP
		if (!user.isUserVerified()) {

			resendOTP(loginId); // your method

			response.setResultCode(ResultCode.OTP_SENT.getCode());
			response.setMessage(ResultCode.OTP_SENT.getMessage());
			return response;
		}

		// ✅ 6. SUCCESS → generate token
		UserDetails userDetails = userDetailService.loadUserByUsername(loginId);
		String token = jwtTokenHelper.generateToken(userDetails);

		response.setResultCode(ResultCode.SUCCESS.getCode());
		response.setMessage(ResultCode.SUCCESS.getMessage());
		response.setData(Arrays.asList(token));

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
					response.setResultCode(AppResultConstant.SUCCESSFUL);
					response.setMessage("Successfully");
				} else {
					response.setResultCode(13);
					response.setMessage("valid otp but user already verified.");
				}
			}

			response.setData(Arrays.asList());
		} else {
			response.setResultCode(AppResultConstant.OTP_INVALID);
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
				sendSMS.sendOtp(mobile, random);
				// smsSend = "Success";
			}
		} catch (Exception var14) {
			smsSend = "EXCEPTION";
		}

		return smsSend;
	}

	@Override
	@Transactional
	public Response deactivateUser() {

		Response response = new Response();
		response.setStatus(HttpStatus.OK);

		// ✅ Get logged-in user (JWT)
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		if (auth == null || auth.getName() == null || "anonymousUser".equals(auth.getName())) {
			throw new AppException(ResultCode.UNAUTHORIZED);
		}

		String loginId = auth.getName();

		User user = userRepo.findByLoginId(loginId).orElseThrow(() -> new AppException(ResultCode.USER_NOT_FOUND));

		// ✅ Already deactivated
		if (!user.isUserActive()) {
			throw new AppException(ResultCode.USER_ALREADY_DEACTIVATE);
		}

		// ✅ Deactivate
		user.setUserActive(false);
		user.setUpdatedDate(new Date());
		userRepo.save(user);

		response.setResultCode(ResultCode.SUCCESS.getCode());
		response.setMessage("User deactivated successfully");
		response.setData(Collections.emptyList());

		return response;
	}
	/*
	 * public Response deactivateUser(String mobile) { Response response = new
	 * Response(); response.setErrorMessage(""); response.setStatus(HttpStatus.OK);
	 * Optional<User> findByMobile = this.userRepo.findByMobile(mobile); if
	 * (findByMobile.isPresent()) { User user = (User) findByMobile.get(); boolean
	 * isUserActive = user.isUserActive(); if (isUserActive) {
	 * user.setUserActive(false); user.setUpdatedDate(new Date());
	 * this.userRepo.save(user);
	 * response.setResultCode(AppResultConstant.SUCCESSFUL);
	 * response.setMessage("successfully."); response.setData(Arrays.asList()); }
	 * else { response.setResultCode(AppResultConstant.USER_ALREADY_DEACTIVATE);
	 * response.setMessage("User already Deactivate");
	 * response.setData(Arrays.asList()); } } else {
	 * response.setResultCode(AppResultConstant.USER_NOT_FOUND);
	 * response.setMessage("User Not Found."); response.setData(Arrays.asList()); }
	 * 
	 * return response; }
	 */

	/*
	 * public Response activateUser(String mobile) { Response response = new
	 * Response(); response.setErrorMessage(""); response.setStatus(HttpStatus.OK);
	 * Optional<User> findByMobile = this.userRepo.findByMobile(mobile); if
	 * (findByMobile.isPresent()) { User user = (User) findByMobile.get(); boolean
	 * isUserActive = user.isUserActive(); if (!isUserActive) {
	 * user.setUserActive(true); user.setUpdatedDate(new Date());
	 * this.userRepo.save(user);
	 * response.setResultCode(ResultCode.SUCCESSFUL.getCode());
	 * response.setMessage(ResultCode.SUCCESSFUL.getMessage());
	 * response.setData(Collections.emptyList()); } else {
	 * response.setResultCode(ResultCode.USER_ALREADY_ACTIVE.getCode());
	 * response.setMessage(ResultCode.USER_ALREADY_ACTIVE.getMessage());
	 * response.setData(Collections.emptyList()); } } else { throw new
	 * AppException(ResultCode.USER_NOT_FOUND); }
	 * 
	 * return response; }
	 */
	@Override
	@Transactional
	public Response activateUser() {

		Response response = new Response();
		response.setStatus(HttpStatus.OK);

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		if (auth == null || auth.getName() == null || "anonymousUser".equals(auth.getName())) {
			throw new AppException(ResultCode.UNAUTHORIZED);
		}

		String loginId = auth.getName();

		User user = userRepo.findByLoginId(loginId).orElseThrow(() -> new AppException(ResultCode.USER_NOT_FOUND));

		if (user.isUserActive()) {
			throw new AppException(ResultCode.USER_ALREADY_ACTIVE);
		}

		user.setUserActive(true);
		user.setUpdatedDate(new Date());
		userRepo.save(user);

		response.setResultCode(ResultCode.SUCCESS.getCode());
		response.setMessage("User activated successfully");
		response.setData(Arrays.asList(modelMapper.map(user, UserResponse.class)));

		return response;
	}

	/*
	 * @Transactional public void generateOtp(String mobile) {
	 * 
	 * LocalDateTime now = LocalDateTime.now(); int otpLimit = 3; int
	 * otpWindowMinutes = 10;
	 * 
	 * // 🚫 Redis atomic rate limiting String redisCountKey = "otp:count:" +
	 * mobile; Long count = redisTemplate.opsForValue().increment(redisCountKey); if
	 * (count == 1) { redisTemplate.expire(redisCountKey,
	 * Duration.ofMinutes(otpWindowMinutes)); } if (count > otpLimit) { throw new
	 * AppException(ResultCode.LIMIT_REACHED); }
	 * 
	 * // 🔹 Generate OTP String otpValue = HUtil.getRandomNumberString();
	 * 
	 * // 🔹 Send OTP (replace with real SMS service) // String smsRes =
	 * sendSMS.sendOtp(mobile, otpValue); String smsRes = "Success"; if
	 * (!"Success".equalsIgnoreCase(smsRes)) { throw new
	 * AppException(ResultCode.SMS_ERROR); }
	 * 
	 * // 🔐 Hash OTP asynchronously to reduce latency CompletableFuture.runAsync(()
	 * -> { String hashedOtp = passwordEncoder.encode(otpValue);
	 * 
	 * // Store/update in DB (optimistic, no lock)
	 * otpRepository.findByMobile(mobile).ifPresentOrElse(otp -> {
	 * otp.setOtpCode(hashedOtp); otp.setCreatedAt(now);
	 * otp.setExpiresAt(now.plusMinutes(5)); otp.setAttempts(0); otp.setUsed(false);
	 * otp.setSendCount(otp.getSendCount() + 1); otpRepository.save(otp); }, () -> {
	 * OtpNew newOtp = new OtpNew(); newOtp.setMobile(mobile);
	 * newOtp.setOtpCode(hashedOtp); newOtp.setCreatedAt(now);
	 * newOtp.setExpiresAt(now.plusMinutes(5)); newOtp.setAttempts(0);
	 * newOtp.setUsed(false); newOtp.setSendCount(1); otpRepository.save(newOtp);
	 * }); }); }
	 */

	/*
	 * @Transactional public Response updateUserProfile(UserDto userDto) { Response
	 * response = new Response(); response.setStatus(HttpStatus.OK); // ✅ Get
	 * logged-in user (JWT आधारित) Authentication auth =
	 * SecurityContextHolder.getContext().getAuthentication();
	 * 
	 * if (auth == null || !auth.isAuthenticated() ||
	 * "anonymousUser".equals(auth.getName())) { throw new
	 * AppException(ResultCode.UNAUTHORIZED); } String loginId = auth.getName();
	 * User user = userRepo.findByLoginId(loginId).orElseThrow(() -> new
	 * AppException(ResultCode.USER_NOT_FOUND));
	 * 
	 * updateIfNotNull(userDto.getName(), user::setName);
	 * updateIfNotNull(userDto.getAbout(), user::setAbout);
	 * updateIfNotNull(userDto.getEmail(), user::setEmail);
	 * updateIfNotNull(userDto.getGender(), user::setGender);
	 * updateIfNotNull(userDto.getMaritalStatus(), user::setMaritalStatus);
	 * updateIfNotNull(userDto.getPlace(), user::setPlace);
	 * updateIfNotNull(userDto.getState(), user::setState);
	 * updateIfNotNull(userDto.getCountry(), user::setCountry);
	 * updateIfNotNull(userDto.getDayBirth(), user::setDayBirth);
	 * updateIfNotNull(userDto.getMonthBirth(), user::setMonthBirth);
	 * updateIfNotNull(userDto.getYearBirth(), user::setYearBirth);
	 * updateIfNotNull(userDto.getHourBirth(), user::setHourBirth);
	 * updateIfNotNull(userDto.getMinuteBirth(), user::setMinuteBirth);
	 * updateIfNotNull(userDto.getSecondBirth(), user::setSecondBirth);
	 * updateIfNotNull(userDto.getLatitude(), user::setLatitude);
	 * updateIfNotNull(userDto.getLatDeg(), user::setLatDeg);
	 * updateIfNotNull(userDto.getLatMin(), user::setLatMin);
	 * updateIfNotNull(userDto.getLatNS(), user::setLatNS);
	 * updateIfNotNull(userDto.getLongitude(), user::setLongitude);
	 * updateIfNotNull(userDto.getLongDeg(), user::setLongDeg);
	 * updateIfNotNull(userDto.getLongMin(), user::setLongMin);
	 * updateIfNotNull(userDto.getLongEW(), user::setLongEW);
	 * updateIfNotNull(userDto.getTimeZone(), user::setTimeZone);
	 * updateIfNotNull(userDto.getDeviceId(), user::setDeviceId);
	 * updateIfNotNull(userDto.getAppVersion(), user::setAppVersion);
	 * updateIfNotNull(userDto.getAndroidVersion(), user::setAndroidVersion);
	 * user.setUpdatedDate(new Date()); // userRepo.save(user);
	 * 
	 * response.setResultCode(ResultCode.PROFILE_UPDATE_SUCCESSFUL.getCode());
	 * response.setMessage(ResultCode.PROFILE_UPDATE_SUCCESSFUL.getMessage());
	 * response.setData(Arrays.asList(modelMapper.map(user, UserResponse.class)));
	 * 
	 * return response; }
	 */

}
