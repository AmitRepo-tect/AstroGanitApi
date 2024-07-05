package com.astroganit.api.repository;

import com.astroganit.api.entities.OTP;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OTPRepo extends JpaRepository<OTP, Integer> {
   Optional<OTP> findByMobileAndOtp(String mobile, String otp);

   Optional<OTP> findByMobile(String mobile);
}
