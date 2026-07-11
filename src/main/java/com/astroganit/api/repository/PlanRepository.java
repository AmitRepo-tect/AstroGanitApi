package com.astroganit.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.astroganit.api.entities.Plan;

@Repository
public interface PlanRepository extends JpaRepository<Plan, Integer> {

    // JpaRepository already provides findById(Integer id),
    // so this method is optional. You can remove it completely.
    Optional<Plan> findById(Integer id);

    Optional<Plan> findByPlanCode(String planCode);

    List<Plan> findByIsActiveTrue();

    @EntityGraph(attributePaths = "features")
    List<Plan> findByIsActiveTrueOrderBySortOrderAsc();

    @Query("SELECT p FROM Plan p LEFT JOIN FETCH p.features WHERE p.isActive = true ORDER BY p.sortOrder ASC")
    List<Plan> findActivePlansWithFeatures();
}