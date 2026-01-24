package com.astroganit.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.astroganit.api.entities.UserSubscription;

import java.util.Optional;
import java.util.List;

public interface SubscriptionRepository extends JpaRepository<UserSubscription, Long> {

	UserSubscription findByUserIdAndStatus(long userId, String status);

	Optional<UserSubscription> findByUserId(long userId);

	Optional<UserSubscription> findByPaymentId(String paymentId);
}
