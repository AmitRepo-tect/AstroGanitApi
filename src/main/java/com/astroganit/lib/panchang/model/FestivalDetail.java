package com.astroganit.lib.panchang.model;

import java.util.Objects;

import com.astroganit.lib.panchang.util.EnumContainer;

public class FestivalDetail {
	private String festName;
	private double festDate;
	private EnumContainer.FestType festType;
	private String imgUrl;

	public FestivalDetail(String festName, double festDate, EnumContainer.FestType festType, String imgUrl) {
		this.festName = festName;
		this.festDate = festDate;
		this.festType = festType != null ? festType : EnumContainer.FestType.OTHER;
		this.imgUrl = imgUrl != null ? imgUrl : "";
	}

	// Optional: Overloaded constructor with default values
	public FestivalDetail(String festName, double festDate) {
		this(festName, festDate, EnumContainer.FestType.OTHER, "");
	}

	// Getters and setters
	public String getFestName() {
		return festName;
	}

	public void setFestName(String festName) {
		this.festName = festName;
	}

	public double getFestDate() {
		return festDate;
	}

	public void setFestDate(double festDate) {
		this.festDate = festDate;
	}

	public EnumContainer.FestType getFestType() {
		return festType;
	}

	public void setFestType(EnumContainer.FestType festType) {
		this.festType = festType != null ? festType : EnumContainer.FestType.OTHER;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl != null ? imgUrl : "";
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof FestivalDetail))
			return false;
		FestivalDetail that = (FestivalDetail) o;
		return Objects.equals(festName, that.festName);
	}

	@Override
	public int hashCode() {
		return Objects.hash(festName);
	}
}
