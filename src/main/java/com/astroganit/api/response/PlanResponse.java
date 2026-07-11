package com.astroganit.api.response;

import java.math.BigDecimal;
import java.util.List;

import com.astroganit.api.entities.PlanType;

public class PlanResponse {

	private Integer id;
	private String planCode;
	private String nameEn;
	private String nameHi;
	private String descriptionEn;
	private String descriptionHi;
	private BigDecimal price;
	private String currency;
	private Integer durationDays;
	private String planType;
	private String iconUrl;
	private Boolean isActive;
	private Integer sortOrder;
	private Integer trialDays;
	private Integer maxUsers;

	private List<PlanFeatureResponse> features;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPlanCode() {
		return planCode;
	}

	public void setPlanCode(String planCode) {
		this.planCode = planCode;
	}

	public String getNameEn() {
		return nameEn;
	}

	public void setNameEn(String nameEn) {
		this.nameEn = nameEn;
	}

	public String getNameHi() {
		return nameHi;
	}

	public void setNameHi(String nameHi) {
		this.nameHi = nameHi;
	}

	public String getDescriptionEn() {
		return descriptionEn;
	}

	public void setDescriptionEn(String descriptionEn) {
		this.descriptionEn = descriptionEn;
	}

	public String getDescriptionHi() {
		return descriptionHi;
	}

	public void setDescriptionHi(String descriptionHi) {
		this.descriptionHi = descriptionHi;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Integer getDurationDays() {
		return durationDays;
	}

	public void setDurationDays(Integer durationDays) {
		this.durationDays = durationDays;
	}

	public String getPlanType() {
		return planType;
	}

	public void setPlanType(String planType) {
		this.planType = planType;
	}

	public String getIconUrl() {
		return iconUrl;
	}

	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Integer getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(Integer sortOrder) {
		this.sortOrder = sortOrder;
	}

	public Integer getTrialDays() {
		return trialDays;
	}

	public void setTrialDays(Integer trialDays) {
		this.trialDays = trialDays;
	}

	public Integer getMaxUsers() {
		return maxUsers;
	}

	public void setMaxUsers(Integer maxUsers) {
		this.maxUsers = maxUsers;
	}

	public List<PlanFeatureResponse> getFeatures() {
		return features;
	}

	public void setFeatures(List<PlanFeatureResponse> features) {
		this.features = features;
	}
}