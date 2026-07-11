package com.astroganit.api.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.astroganit.api.entities.Plan;
import com.astroganit.api.entities.PlanFeature;
import com.astroganit.api.response.PlanFeatureResponse;
import com.astroganit.api.response.PlanResponse;

@Component
public class PlanMapper {

	public PlanResponse toResponse(Plan plan) {

		PlanResponse response = new PlanResponse();

		response.setId(plan.getId());
		response.setPlanCode(plan.getPlanCode());
		response.setNameEn(plan.getNameEn());
		response.setNameHi(plan.getNameHi());
		response.setDescriptionEn(plan.getDescriptionEn());
		response.setDescriptionHi(plan.getDescriptionHi());
		response.setPrice(plan.getPrice());
		response.setCurrency(plan.getCurrency());
		response.setDurationDays(plan.getDurationDays());

		// If PlanResponse.planType is String
		response.setPlanType(plan.getPlanType().name());

		// If PlanResponse.planType is PlanType, use:
		// response.setPlanType(plan.getPlanType());

		response.setIconUrl(plan.getIconUrl());
		response.setIsActive(plan.getIsActive());
		response.setSortOrder(plan.getSortOrder());

		// New fields
		response.setTrialDays(plan.getTrialDays());
		response.setMaxUsers(plan.getMaxUsers());

		if (plan.getFeatures() != null) {
			List<PlanFeatureResponse> features = plan.getFeatures().stream().map(this::toFeatureResponse).toList();

			response.setFeatures(features);
		}

		return response;
	}

	private PlanFeatureResponse toFeatureResponse(PlanFeature feature) {

		PlanFeatureResponse response = new PlanFeatureResponse();
		response.setId(feature.getId());
		response.setFeatureKey(feature.getFeatureKey());
		response.setFeatureValue(feature.getFeatureValue());
		response.setFeatureLimit(feature.getFeatureLimit());
		response.setTitleEn(feature.getTitleEn());
		response.setTitleHi(feature.getTitleHi());
		response.setShortDescEn(feature.getShortDescEn());
		response.setShortDescHi(feature.getShortDescHi());
		response.setDisplayOrder(feature.getDisplayOrder());
		response.setIsActive(feature.getIsActive());
		return response;
	}
}