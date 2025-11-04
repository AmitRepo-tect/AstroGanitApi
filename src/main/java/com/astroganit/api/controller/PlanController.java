package com.astroganit.api.controller;

import org.springframework.web.bind.annotation.*;

import com.astroganit.api.entities.Plan;
import com.astroganit.api.serviceImpl.PlanService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/plans")
public class PlanController {

    private final PlanService planService;

    public PlanController(PlanService planService) {
        this.planService = planService;
    }

    @GetMapping
    public List<Plan> getAllPlans() {
        return planService.getAllPlans();
    }

    @GetMapping("/{id}")
    public Optional<Plan> getPlanById(@PathVariable Long id) {
        return planService.getPlanById(id);
    }
}
