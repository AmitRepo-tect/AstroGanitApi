package com.astroganit.api.entities;

import java.time.LocalDateTime;
import java.util.Date;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "otpnew")
public class OtpNew {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 15)
	private String mobile;

	@Column(nullable = false, length = 100)
	private String otpCode;

	@Column(nullable = false)
	private LocalDateTime expiresAt;

	@Column(nullable = false)
	private LocalDateTime createdAt = LocalDateTime.now();

	@Column(nullable = false)
	private boolean isUsed = false;

	@Column(nullable = false)
	private int attempts = 0;
	@Column(nullable = false)
	private int sendCount = 0;

	@Column
	private LocalDateTime lastSentAt;

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOtpCode() {
		return otpCode;
	}

	public void setOtpCode(String otpCode) {
		this.otpCode = otpCode;
	}

	public java.time.LocalDateTime getExpiresAt() {
		return expiresAt;
	}

	public void setExpiresAt(java.time.LocalDateTime expiresAt) {
		this.expiresAt = expiresAt;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public boolean isUsed() {
		return isUsed;
	}

	public void setUsed(boolean isUsed) {
		this.isUsed = isUsed;
	}

	public int getAttempts() {
		return attempts;
	}

	public void setAttempts(int attempts) {
		this.attempts = attempts;
	}

	public String getMobile() {
		return mobile;
	}

	public int getSendCount() {
		return sendCount;
	}

	public void setSendCount(int sendCount) {
		this.sendCount = sendCount;
	}

	public LocalDateTime getLastSentAt() {
		return lastSentAt;
	}

	public void setLastSentAt(LocalDateTime lastSentAt) {
		this.lastSentAt = lastSentAt;
	}

}
