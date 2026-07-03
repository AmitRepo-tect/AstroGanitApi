package com.astroganit.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.astroganit.api.entities.OtpNew;

import jakarta.persistence.LockModeType;

import java.time.LocalDateTime;
import java.util.Optional;

public interface OtpRepository extends JpaRepository<OtpNew, Long> {

	Optional<OtpNew> findTopByMobileOrderByIdDesc(String mobile);

	Optional<OtpNew> findByMobile(String mobile);

	@Query("SELECT o FROM OtpNew o WHERE o.mobile = :mobile")
	Optional<OtpNew> findByMobileForUpdate(@Param("mobile") String mobile);

	@Query("SELECT COUNT(o) FROM OtpNew o WHERE o.mobile = :mobile AND o.createdAt >= :fromTime")
	int countOtpsSentRecently(@Param("mobile") String mobile, @Param("fromTime") LocalDateTime fromTime);

}
