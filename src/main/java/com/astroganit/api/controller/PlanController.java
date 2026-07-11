package com.astroganit.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.astroganit.api.entities.BirthDetailInfo;
import com.astroganit.api.entities.Plan;
import com.astroganit.api.payload.ResponseNew;
import com.astroganit.api.response.PlanResponse;
import com.astroganit.api.serviceImpl.PlanService;
import com.astroganit.api.util.ResultCode;

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

	/*
	 * @GetMapping("/active") public List<Plan> getAllActivePlans() {
	 * 
	 * return planService.getAllActivePlans(); }
	 */
	@GetMapping("/active")
	public ResponseEntity<ResponseNew<List<PlanResponse>>> getAllActivePlans() {

		List<PlanResponse> plans = planService.getAllActivePlans();

		ResponseNew<List<PlanResponse>> response = new ResponseNew<>();
			
		response.setStatus(HttpStatus.OK);
		response.setStatusCode(HttpStatus.OK.value());
		response.setResultCode(ResultCode.SUCCESS.getCode());
		response.setMessage("Plans fetched successfully.");
		response.setData(plans);
		System.out.println(response);
		return ResponseEntity.ok(response);
	}

	@GetMapping("/{id}")
	public Optional<Plan> getPlanById(@PathVariable Integer id) {
		return planService.getPlanById(id);
	}
}
