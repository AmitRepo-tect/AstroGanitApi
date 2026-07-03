package com.astroganit.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.astroganit.api.entities.Plan;

public interface PlanRepository extends JpaRepository<Plan, Integer> {
	Optional<Plan> findByPlanId(Long id);

	List<Plan> findByIsActiveTrue();

	@EntityGraph(attributePaths = "features")
	List<Plan> findByIsActiveTrueOrderBySortOrderAsc();

	@Query("SELECT p FROM Plan p LEFT JOIN FETCH p.features WHERE p.isActive = true")
	List<Plan> findActivePlansWithFeatures();
	
}
