package com.astroganit.api.entities;

import java.util.Date;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import jakarta.persistence.Entity;

@Entity
@Table(name = "otp")
public class OTP {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String mobile;
	private String otp;
	@Column(name = "created_date")
	private Date createdDate;
	@Column(name = "updated_date")
	private Date updatedDate;
	private int count;

	public int getId() {
		return this.id;
	}

	public String getMobile() {
		return this.mobile;
	}

	public String getOtp() {
		return this.otp;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public Date getUpdatedDate() {
		return this.updatedDate;
	}

	public int getCount() {
		return this.count;
	}

	public void setId(final int id) {
		this.id = id;
	}

	public void setMobile(final String mobile) {
		this.mobile = mobile;
	}

	public void setOtp(final String otp) {
		this.otp = otp;
	}

	public void setCreatedDate(final Date createdDate) {
		this.createdDate = createdDate;
	}

	public void setUpdatedDate(final Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public void setCount(final int count) {
		this.count = count;
	}
}
