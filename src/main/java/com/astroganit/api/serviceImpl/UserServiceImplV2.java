package com.astroganit.api.serviceImpl;

import com.astroganit.api.entities.OtpNew;
import com.astroganit.api.entities.User;
import com.astroganit.api.exception.AppException;
import com.astroganit.api.payload.DeleteAccountResponse;
import com.astroganit.api.payload.LoginOtpResponse;
import com.astroganit.api.payload.LoginRequestDto;
import com.astroganit.api.payload.LoginResponse;
import com.astroganit.api.payload.ResendOtpResponse;
import com.astroganit.api.payload.ResponseNew;
import com.astroganit.api.payload.UserDto;
import com.astroganit.api.payload.UserResponse;
import com.astroganit.api.repository.OtpRepository;
import com.astroganit.api.repository.UserRepo;
import com.astroganit.api.service.UserServiceV2;
import com.astroganit.api.util.HUtil;
import com.astroganit.api.util.ResultCode;
import com.astroganit.security.JwtTokenHelper;
import org.springframework.transaction.annotation.Transactional;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImplV2 implements UserServiceV2 {
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private OtpRepository otpRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private JwtTokenHelper jwtTokenHelper;
	@Autowired
	private UserDetailsService userDetailService;
	@Autowired
	private OtpService otpService;
	@Autowired
	private SubscriptionService subscriptionService;
	@Value("${playstore.review.mobile}")
	private String reviewMobile;
	@Value("${playstore.review.otp}")
	private String reviewOtp;
	/*
	 * @Autowired private StringRedisTemplate redisTemplate;
	 */

	final int OTP_LIMIT = 10;
	final int OTP_WINDOW_MIN = 10; // minutes
	final int OTP_EXPIRY_MIN = 5; // minutes
	final int OTP_COOLDOWN_SEC = 30; // seconds
	private static final String DEFAULT_PASSWORD = "astro_2026";

	// ----Version V2 Api-----
	private void forTest(String loginId, ResultCode error) {
		if (loginId.equals("9015469060")) {
			throw new AppException(error);
		}
	}

	/* Start login Api */
	@Override
	@Transactional
	public ResponseNew<LoginOtpResponse> requestLoginOtp2(LoginRequestDto loginRequestDto) {

		// forTest(loginRequestDto.getLoginId(),ResultCode.ACCOUNT_PENDING_DELETION);
		validateLoginRequest(loginRequestDto);
		User user = findExistingOrCreateUser(loginRequestDto);
		validateUser(user);
		sendOtp(user.getLoginId());
		return createSuccessResponse(buildLoginOtpResponse(user, loginRequestDto.getMobilecc()),
				ResultCode.SUCCESS.getMessage());
	}

	private User findExistingOrCreateUser(LoginRequestDto request) {

		Optional<User> existing = userRepo.findByLoginId(request.getLoginId());
		if (existing.isPresent()) {
			return existing.get();
		}
		try {
			return userRepo.saveAndFlush(createUser(request));
		} catch (DataIntegrityViolationException ex) {
			return userRepo.findByLoginId(request.getLoginId()).orElseThrow(() -> ex);
		}
	}

	private User createUser(LoginRequestDto request) {

		User user = new User();
		user.setName("");
		user.setLoginId(request.getLoginId());
		user.setMobile(request.getLoginId());
		user.setUserActive(true);
		user.setUserVerified(false);
		Date now = new Date();
		user.setCreatedDate(now);
		user.setUpdatedDate(now);
		user.setPassword(passwordEncoder.encode(DEFAULT_PASSWORD));
		user.setMobilecc(request.getMobilecc());
		user.setAndroidVersion(request.getAndroidVersion());
		user.setAppVersion(request.getAppVersion());
		user.setDeviceId(request.getDeviceId());

		return user;
	}

	private void validateLoginRequest(LoginRequestDto request) {
		if (!HUtil.isValidString(request.getLoginId())) {
			throw new AppException(ResultCode.INVALID_PARAMETER);
		}
	}

	private void validateUser(User user) {

		if (user.isDeleted()) {
			throw new AppException(ResultCode.ACCOUNT_PENDING_DELETION);
		}

	}

	private void sendOtp(String loginId) {
		if (!reviewMobile.equals(loginId)) {
			generateOtp(loginId);
		}
	}

	private LoginOtpResponse buildLoginOtpResponse(User user, String mobileCC) {
		LoginOtpResponse response = new LoginOtpResponse();
		response.setOtpSent(true);
		response.setLoginId(user.getLoginId());
		response.setMobileCC(mobileCC);
		return response;
	}

	/* End login Api */

	/* Start Verify OTP */
	@Override
	@Transactional
	public ResponseNew<LoginResponse> verifyOtpV2(String mobile, String otpCode) {
		// forTest(mobile, ResultCode.USER_NOT_FOUND);

		// Play Store review account
		if (reviewMobile.equals(mobile) && reviewOtp.equals(otpCode)) {
			return loginUserV2(mobile);
		}

		OtpNew otp = otpRepository.findByMobile(mobile).orElseThrow(() -> new AppException(ResultCode.OTP_NOT_FOUND));

		validateOtpState(otp);

		// Invalid OTP
		if (!passwordEncoder.matches(otpCode, otp.getOtpCode())) {
			otpService.incrementAttempts(otp.getId());
			throw new AppException(ResultCode.OTP_INVALID);
		}

		// Mark OTP as used (atomic)
		int updated = otpRepository.markOtpAsUsed(otp.getId());

		if (updated == 0) {
			throw new AppException(ResultCode.OTP_ALREADY_USED);
		}

		return loginUserV2(mobile);
	}

	private void validateOtpState(OtpNew otp) {

		if (otp.isUsed()) {
			throw new AppException(ResultCode.OTP_ALREADY_USED);
		}

		if (LocalDateTime.now().isAfter(otp.getExpiresAt())) {
			throw new AppException(ResultCode.OTP_EXPIRED);
		}

		if (otp.getAttempts() >= 3) {
			throw new AppException(ResultCode.MAX_ATTEMPTS_REACHED);
		}

	}

	private ResponseNew<LoginResponse> loginUserV2(String mobile) {
		User user = userRepo.findByLoginId(mobile).orElseThrow(() -> new AppException(ResultCode.USER_NOT_FOUND));
		System.out.println(user.getLoginId());

		if (user.isDeleted()) {
			// Restore the account
			user.setDeleted(false);
			user.setDeletedDate(null);
			user.setDeleteAfter(null);
			user.setUserActive(true);
			user.setUpdatedDate(new Date());
		}

		if (!user.isUserVerified()) {
			user.setUserVerified(true);
		}
		return createSuccessResponse(buildLoginResponse(user, generateToken(user.getLoginId())),
				"OTP verified successfully");
	}

	private LoginResponse buildLoginResponse(User user, String token) {
		LoginResponse response = new LoginResponse();
		response.setAccessToken(token);
		if (user.isUserActive()) {
			response.setUser(modelMapper.map(user, UserResponse.class));
			response.setSubscription(subscriptionService.getActiveSubscription(user));
		}
		return response;
	}

	/* End Verify OTP */

	/* start Delete user */
	@Override
	@Transactional
	public ResponseNew<DeleteAccountResponse> deleteUserV2() {

		ResponseNew<DeleteAccountResponse> response = new ResponseNew<DeleteAccountResponse>();
		response.setStatus(HttpStatus.OK);

		User user = getAuthenticatedUser();

		// Already deleted
		if (user.isDeleted()) {
			throw new AppException(ResultCode.ACCOUNT_PENDING_DELETION);
		}

		Date now = new Date();

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(now);
		calendar.add(Calendar.DAY_OF_MONTH, 30); // Grace period

		user.setDeleted(true);
		user.setDeletedDate(now);
		user.setDeleteAfter(calendar.getTime());
		user.setUserActive(false);
		user.setUpdatedDate(now);

		DeleteAccountResponse data = new DeleteAccountResponse();
		data.setDeleted(true);
		data.setRestoreUntil(calendar.getTime());

		response.setResultCode(ResultCode.SUCCESS.getCode());
		response.setMessage("Account deleted successfully. You can restore it within 30 days.");
		response.setData(data);

		return response;
	}

	/* End Delete user */

	private User getAuthenticatedUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth == null || !auth.isAuthenticated() || auth instanceof AnonymousAuthenticationToken) {
			throw new AppException(ResultCode.UNAUTHORIZED);
		}

		return userRepo.findByLoginId(auth.getName()).orElseThrow(() -> new AppException(ResultCode.USER_NOT_FOUND));
	}

	private <T> ResponseNew<T> createSuccessResponse(T data, String message) {
		ResponseNew<T> response = new ResponseNew<>();
		response.setResultCode(ResultCode.SUCCESS.getCode());
		response.setMessage(message);
		response.setStatus(HttpStatus.OK);
		response.setStatusCode(HttpStatus.OK.value());
		response.setData(data);
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

	@Override
	public ResponseNew<UserResponse> updateUserProfileV1(UserDto user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseNew<UserResponse> getUserProfile() {
		// TODO Auto-generated method stub
		return null;
	}

	/* Strat Resend Otp */
	@Override
	@Transactional
	public ResponseNew<ResendOtpResponse> resendOTP(String mobile, boolean restoreFlow) {

		OtpNew otp = sendOtp(mobile, () -> validateResendRequest(mobile, restoreFlow));

		return createSuccessResponse(buildResendOtpResponse(otp), ResultCode.OTP_SENT.getMessage());
	}

	private void validateResendRequest(String mobile, boolean restoreFlow) {
		User user = userRepo.findByLoginId(mobile).orElseThrow(() -> new AppException(ResultCode.USER_NOT_FOUND));
		if (restoreFlow && !user.isDeleted()) {
			throw new AppException(ResultCode.ACCOUNT_NOT_PENDING_DELETION);
		}

		if (!restoreFlow && user.isDeleted()) {
			throw new AppException(ResultCode.ACCOUNT_PENDING_DELETION);
		}
	}

	private ResendOtpResponse buildResendOtpResponse(OtpNew otp) {
		ResendOtpResponse data = new ResendOtpResponse();
		data.setOtpSent(true);
		data.setRemainingAttempts(OTP_LIMIT - otp.getSendCount());
		data.setRetryAfterSeconds(OTP_COOLDOWN_SEC);
		data.setMesssage("OTP resent successfully");

		return data;
	}

	private void validateCooldown(OtpNew otp, LocalDateTime now) {
		if (otp.getLastSentAt() == null) {
			return;
		}

		LocalDateTime nextAllowed = otp.getLastSentAt().plusSeconds(OTP_COOLDOWN_SEC);

		if (now.isBefore(nextAllowed)) {
			long retryAfter = Math.max(1, Duration.between(now, nextAllowed).getSeconds());
			throw new AppException(ResultCode.TOO_SOON);
		}

	}

	/* End Resend Otp */

	private OtpNew sendOtp(String mobile, Runnable extraValidation) {

		if (extraValidation != null) {
			extraValidation.run();
		}

		LocalDateTime now = LocalDateTime.now();

		OtpNew otp = getOrCreateOtp(mobile);

		resetSendCountIfRequired(otp, now);

		validateSendLimit(otp);

		validateCooldown(otp, now);

		String otpValue = HUtil.getRandomNumberString();

		// sendSMS.sendOtp(mobile, otpValue);

		updateOtp(otp, otpValue, now);

		return otpRepository.save(otp);
	}

	private void resetSendCountIfRequired(OtpNew otp, LocalDateTime now) {
		if (otp.getLastSentAt() == null || otp.getLastSentAt().isBefore(now.minusMinutes(OTP_WINDOW_MIN))) {
			otp.setSendCount(0);
		}

	}

	private void validateSendLimit(OtpNew otp) {
		if (otp.getSendCount() >= OTP_LIMIT) {
			throw new AppException(ResultCode.LIMIT_REACHED);
		}

	}

	private OtpNew getOrCreateOtp(String mobile) {

		return otpRepository.findByMobileForUpdate(mobile).orElseGet(() -> {
			try {
				return otpRepository.saveAndFlush(createOtp(mobile));
			} catch (DataIntegrityViolationException ex) {
				return otpRepository.findByMobileForUpdate(mobile).orElseThrow(() -> ex);
			}
		});

	}

	private OtpNew createOtp(String mobile) {
		OtpNew otp = new OtpNew();
		otp.setMobile(mobile);
		otp.setSendCount(0);
		otp.setAttempts(0);
		otp.setUsed(false);
		return otp;
	}

	private void updateOtp(OtpNew otp, String otpValue, LocalDateTime now) {
		otp.setOtpCode(passwordEncoder.encode(otpValue));
		otp.setCreatedAt(now);
		otp.setExpiresAt(now.plusMinutes(OTP_EXPIRY_MIN));
		otp.setLastSentAt(now);
		otp.setAttempts(0);
		otp.setUsed(false);
		otp.setSendCount(otp.getSendCount() + 1);
	}

	public void generateOtp(String mobile) {
		sendOtp(mobile, null);
	}

}
