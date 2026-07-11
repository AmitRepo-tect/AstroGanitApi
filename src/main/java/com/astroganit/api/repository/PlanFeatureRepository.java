package com.astroganit.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.astroganit.api.entities.PlanFeature;

@Repository
public interface PlanFeatureRepository extends JpaRepository<PlanFeature, Integer> {

	Optional<PlanFeature> findByPlan_IdAndFeatureKey(Integer planId, String featureKey);

	List<PlanFeature> findByPlan_Id(Integer planId);

	boolean existsByPlan_IdAndFeatureKey(Integer planId, String featureKey);

	@Query("SELECT pf.featureLimit FROM PlanFeature pf WHERE pf.plan.id = :planId AND pf.featureKey = :featureKey")
	Optional<Integer> findFeatureLimit(@Param("planId") Integer planId, @Param("featureKey") String featureKey);

	@Query("SELECT pf.featureValue FROM PlanFeature pf WHERE pf.plan.id = :planId AND pf.featureKey = :featureKey")
	Optional<String> findFeatureValue(@Param("planId") Integer planId, @Param("featureKey") String featureKey);
}