package com.astroganit.api.serviceImpl;

import org.springframework.stereotype.Service;

import com.astroganit.api.entities.Plan;
import com.astroganit.api.mapper.PlanMapper;
import com.astroganit.api.repository.PlanRepository;
import com.astroganit.api.response.PlanResponse;

import java.util.List;
import java.util.Optional;

@Service
public class PlanService {

	private final PlanRepository planRepository;
	private final PlanMapper planMapper;

	public PlanService(PlanRepository planRepository, PlanMapper planMapper) {
		this.planRepository = planRepository;
		this.planMapper = planMapper;
	}

	public List<Plan> getAllPlans() {
		return planRepository.findAll();
	}

	public Optional<Plan> getPlanById(Integer id) {
		return planRepository.findById(id);
	}

	public Plan savePlan(Plan plan) {
		return planRepository.save(plan);
	}

	public List<PlanResponse> getAllActivePlans() {

		List<Plan> plans = planRepository.findByIsActiveTrueOrderBySortOrderAsc();

		return plans.stream().map(planMapper::toResponse).toList();
	}
}
