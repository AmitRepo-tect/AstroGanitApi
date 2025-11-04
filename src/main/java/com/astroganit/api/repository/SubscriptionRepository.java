package com.astroganit.api.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.astroganit.api.entities.UserSubscription;

import java.util.Optional;
import java.util.List;

public interface SubscriptionRepository extends JpaRepository<UserSubscription, Integer> {

    Optional<UserSubscription> findByUserIdAndStatus(int userId, String status);

    List<UserSubscription> findByUserId(int userId);
}
