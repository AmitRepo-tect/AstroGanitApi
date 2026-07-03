package com.astroganit.api.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "plan_features")
public class PlanFeature {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String featureKey;
	private int featureLimit;
	private String titleEn;
	private String titleHi;
	private String shortDescEn;
	private String shortDescHi;
	private Integer displayOrder;
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	private LocalDateTime createdAt;

	@ManyToOne
	@JoinColumn(name = "plan_id")
	@JsonBackReference
	private Plan plan;

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

	public int getFeatureLimit() {
		return featureLimit;
	}

	public void setFeatureLimit(int featureLimit) {
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

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public Plan getPlan() {
		return plan;
	}

	public void setPlan(Plan plan) {
		this.plan = plan;
	}

	@PrePersist
	public void prePersist() {
		createdAt = LocalDateTime.now();
	}

}