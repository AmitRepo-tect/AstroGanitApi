package com.astroganit.api.entities;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.*;

@Entity
@Table(name = "plan_features")
public class PlanFeature {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "plan_id", nullable = false)
	@JsonBackReference
	private Plan plan;

	@Column(name = "feature_key", nullable = false, length = 100)
	private String featureKey;

	@Column(name = "feature_value", length = 255)
	private String featureValue;

	@Column(name = "feature_limit")
	private Integer featureLimit;

	@Column(name = "title_en", nullable = false, length = 150)
	private String titleEn;

	@Column(name = "title_hi", nullable = false, length = 150)
	private String titleHi;

	@Column(name = "short_desc_en", length = 255)
	private String shortDescEn;

	@Column(name = "short_desc_hi", length = 255)
	private String shortDescHi;

	@Column(name = "display_order", nullable = false)
	private Integer displayOrder = 0;

	@Column(name = "is_active", nullable = false)
	private Boolean isActive = true;

	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	@Column(name = "created_at", nullable = false, updatable = false)
	private LocalDateTime createdAt;

	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	@Column(name = "updated_at", nullable = false)
	private LocalDateTime updatedAt;

	@PrePersist
	public void prePersist() {
		createdAt = LocalDateTime.now();
		updatedAt = LocalDateTime.now();
	}

	@PreUpdate
	public void preUpdate() {
		updatedAt = LocalDateTime.now();
	}

	// Getters & Setters

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Plan getPlan() {
		return plan;
	}

	public void setPlan(Plan plan) {
		this.plan = plan;
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

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}
}