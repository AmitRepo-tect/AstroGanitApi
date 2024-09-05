package com.astroganit.api.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "status_img")
public class StatusImg {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	@Column(name = "cate_id")
	int cateId;
	@Column(name = "cate_name")
	String cateName;
	@Column(name = "special_day")
	String specialDay;
	@Column(name = "img_url")
	String imgUrl;
	@Column(name = "lang")
	String lang;
	@Column(name = "sub_cate_id")
	int subCateId;
	@Column(name = "sub_cate_name")
	String subCateName;
	@Column(name = "create_date")
	String createDate;

	public StatusImg(int id, int cateId, String cateName, String specialDay, String imgUrl, String lang, int subCateId,
			String subCateName, String createDate) {
		this.id = id;
		this.cateId = cateId;
		this.cateName = cateName;
		this.specialDay = specialDay;
		this.imgUrl = imgUrl;
		this.lang = lang;
		this.subCateId = subCateId;
		this.subCateName = subCateName;
		this.createDate = createDate;
	}

	public StatusImg() {

	}

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

	public int getSubCateId() {
		return subCateId;
	}

	public void setSubCateId(int subCateId) {
		this.subCateId = subCateId;
	}

	public String getSubCateName() {
		return subCateName;
	}

	public void setSubCateName(String subCateName) {
		this.subCateName = subCateName;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
}
