package com.astroganit.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.astroganit.api.entities.UserSubscription;

import jakarta.transaction.Transactional;

import java.util.Optional;
import java.util.List;

public interface SubscriptionRepository extends JpaRepository<UserSubscription, Long> {

	UserSubscription findByUserIdAndStatus(long userId, String status);

	Optional<UserSubscription> findByUserId(long userId);

	Optional<UserSubscription> findByPaymentId(long paymentId);

	@Modifying
	@Transactional
	@Query("""
			    UPDATE UserSubscription u
			    SET u.status = 'EXPIRED'
			    WHERE u.userId = :userId
			    AND u.endDate < CURRENT_TIMESTAMP
			""")
	int expireSubscription(Long userId);

	@Query("""
			    SELECT u FROM UserSubscription u
			    WHERE u.userId = :userId
			    AND u.status = 'ACTIVE'
			    AND u.startDate <= CURRENT_TIMESTAMP
			    AND (u.endDate IS NULL OR u.endDate >= CURRENT_TIMESTAMP)
			""")
	Optional<UserSubscription> findActiveSubscription(Long userId);

}
