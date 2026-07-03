package com.astroganit.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.astroganit.api.entities.UserFeatureUsage;

@Repository
public interface UserFeatureUsageRepository extends JpaRepository<UserFeatureUsage, Long> {

	@Modifying
	@Transactional
	@Query(value = """
			    INSERT INTO user_feature_usage
			    (user_id, subscription_id, feature_key, used_count, created_at, updated_at)
			    VALUES (:userId, :subscriptionId, :featureKey, 1, NOW(), NOW())
			    ON DUPLICATE KEY UPDATE
			    used_count = IF(used_count < :limit, used_count + 1, used_count),
			    updated_at = NOW()
			""", nativeQuery = true)
	int upsertUsage(Long userId, Long subscriptionId, String featureKey, int limit);

	@Query(value = """
			    SELECT used_count FROM user_feature_usage
			    WHERE user_id = :userId AND feature_key = :featureKey
			""", nativeQuery = true)
	Integer getUsedCount(Long userId, String featureKey);
}