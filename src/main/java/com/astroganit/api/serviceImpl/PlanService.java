package com.astroganit.api.serviceImpl;


import org.springframework.stereotype.Service;

import com.astroganit.api.entities.Plan;
import com.astroganit.api.repository.PlanRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PlanService {

    private final PlanRepository planRepository;

    public PlanService(PlanRepository planRepository) {
        this.planRepository = planRepository;
    }

    public List<Plan> getAllPlans() {
        return planRepository.findAll();
    }
    public List<Plan> getAllActivePlans() {
        return planRepository.findActivePlansWithFeatures();
    }

    public Optional<Plan> getPlanById(Long id) {
        return planRepository.findByPlanId(id);
    }

    public Plan savePlan(Plan plan) {
        return planRepository.save(plan);
    }
}
