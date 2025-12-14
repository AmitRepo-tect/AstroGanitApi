package com.astroganit.api.repository;



import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.astroganit.api.entities.Plan;

public interface PlanRepository extends JpaRepository<Plan, Integer> {
	Optional<Plan> findByPlanId(Long id);
}
