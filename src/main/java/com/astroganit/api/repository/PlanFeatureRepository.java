package com.astroganit.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.astroganit.api.entities.PlanFeature;

@Repository
public interface PlanFeatureRepository extends JpaRepository<PlanFeature, Long> {

	// 🔹 1. Get specific feature by plan
	Optional<PlanFeature> findByPlan_PlanIdAndFeatureKey(int planId, String featureKey);

	// 🔹 2. Get all features for a plan
	List<PlanFeature> findByPlan_PlanId(int planId);

	// 🔹 3. Check if feature exists for plan
	boolean existsByPlan_PlanIdAndFeatureKey(int planId, String featureKey);

	// 🔹 4. Get feature limit
	@Query("""
			 SELECT p.featureLimit
			 FROM PlanFeature p
			 WHERE p.plan.planId = :planId
			 AND p.featureKey = :featureKey
			""")
	Optional<Integer> findFeatureLimit(int planId, String featureKey);
}