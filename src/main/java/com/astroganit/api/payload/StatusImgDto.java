package com.astroganit.api.payload;

public class StatusImgDto {
	int id;
	int cateId;
	String cateName;
	String specialDay;
	String imgUrl;
	String lang;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCateId() {
		return cateId;
	}

	public void setCateId(int cateId) {
		this.cateId = cateId;
	}

	public String getCateName() {
		return cateName;
	}

	public void setCateName(String cateName) {
		this.cateName = cateName;
	}

	public String getSpecialDay() {
		return specialDay;
	}

	public void setSpecialDay(String specialDay) {
		this.specialDay = specialDay;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

}
