package com.astroganit.api.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "status_category")
public class StatusCategory {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	@Column(name = "cate_id")
	int cateId;
	@Column(name = "cate_name")
	String cateName;
	@Column(name = "lang")
	String lang;
	@Column(name = "parent_cate_id")
	int parentCateId;
	@Column(name = "parent_cate_name")
	String parentCateName;

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

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public String getParentCateName() {
		return parentCateName;
	}

	public void setParentCateName(String parentCateName) {
		this.parentCateName = parentCateName;
	}

	public int getParentCateId() {
		return parentCateId;
	}

	public void setParentCateId(int parentCateId) {
		this.parentCateId = parentCateId;
	}
}
