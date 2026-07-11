package com.astroganit.api.response;

public class PlanFeatureResponse {

	private Integer id;
	private String featureKey;
	private String featureValue;
	private Integer featureLimit;
	private String titleEn;
	private String titleHi;
	private String shortDescEn;
	private String shortDescHi;
	private Integer displayOrder;
	private Boolean isActive;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFeatureKey() {
		return featureKey;
	}

	public void setFeatureKey(String featureKey) {
		this.featureKey = featureKey;
	}

	public String getFeatureValue() {
		return featureValue;
	}

	public void setFeatureValue(String featureValue) {
		this.featureValue = featureValue;
	}

	public Integer getFeatureLimit() {
		return featureLimit;
	}

	public void setFeatureLimit(Integer featureLimit) {
		this.featureLimit = featureLimit;
	}

	public String getTitleEn() {
		return titleEn;
	}

	public void setTitleEn(String titleEn) {
		this.titleEn = titleEn;
	}

	public String getTitleHi() {
		return titleHi;
	}

	public void setTitleHi(String titleHi) {
		this.titleHi = titleHi;
	}

	public String getShortDescEn() {
		return shortDescEn;
	}

	public void setShortDescEn(String shortDescEn) {
		this.shortDescEn = shortDescEn;
	}

	public String getShortDescHi() {
		return shortDescHi;
	}

	public void setShortDescHi(String shortDescHi) {
		this.shortDescHi = shortDescHi;
	}

	public Integer getDisplayOrder() {
		return displayOrder;
	}

	public void setDisplayOrder(Integer displayOrder) {
		this.displayOrder = displayOrder;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	// Getters & Setters

}