package com.astroganit.api.serviceImpl;

import java.time.Duration;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.astroganit.api.entities.OtpNew;
import com.astroganit.api.exception.AppException;
import com.astroganit.api.repository.OtpRepository;
import com.astroganit.api.util.HUtil;
import com.astroganit.api.util.ResultCode;
import com.astroganit.api.util.SendSMS;

import jakarta.transaction.Transactional;

@Service
public class OtpService {

	private final OtpRepository otpRepository;
	private final PasswordEncoder passwordEncoder;
	private final SendSMS sendSMS;

	@Autowired
	public OtpService(OtpRepository otpRepository, PasswordEncoder passwordEncoder, SendSMS sendSMS) {
		this.otpRepository = otpRepository;
		this.passwordEncoder = passwordEncoder;
		this.sendSMS = sendSMS;
	}

	/**
	 * Generates OTP for a mobile number with locking, limits, cooldown, and
	 * hashing.
	 */
	@Transactional
	public void generateOtp(String mobile) {

		final int OTP_LIMIT = 3;
		final int OTP_WINDOW_MIN = 10; // minutes
		final int OTP_EXPIRY_MIN = 5; // minutes
		final int OTP_COOLDOWN_SEC = 30; // seconds

		LocalDateTime now = LocalDateTime.now();
		String otpValue = HUtil.getRandomNumberString();

		// 🔒 Lock row to prevent race conditions
		OtpNew otp = otpRepository.findByMobileForUpdate(mobile).orElseGet(() -> {
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
		try {
			//sendSMS.sendOtp(mobile, otpValue);
		} catch (Exception e) {
			throw new AppException(ResultCode.SMS_ERROR.getCode(), "Failed to send OTP. Please try again.");
		}

		// 🔐 Hash and store OTP
		otp.setOtpCode(passwordEncoder.encode(otpValue));
		otp.setCreatedAt(now);
		otp.setExpiresAt(now.plusMinutes(OTP_EXPIRY_MIN));
		otp.setAttempts(0);
		otp.setUsed(false);
		otp.setSendCount(otp.getSendCount() + 1);
		otp.setLastSentAt(now);

		otpRepository.save(otp);

		// Return plain OTP for response if needed (for testing only)
		
	}
}