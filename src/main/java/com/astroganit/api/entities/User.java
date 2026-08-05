package com.astroganit.api.entities;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "users")
public class User implements UserDetails {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(name = "login_id", length = 15)
	private String loginId;
	@Column(name = "user_name", length = 100)
	private String name;
	private String email;
	private String password;
	@Column(name = "created_date")
	private Date createdDate;
	@Column(name = "updated_date")
	private Date updatedDate;
	@Column(name = "user_active")
	private boolean userActive;
	@Column(name = "deleted", nullable = false)
	private boolean deleted = false;
	@Column(name = "deleted_date")
	private Date deletedDate;
	@Column(name = "delete_after")
	private Date deleteAfter;
	private String about;
	private String dcrptpassword;
	private String gender;
	private String place;
	private String country;
	private String state;
	private String mobile;
	private String mobilecc;
	@Column(name = "day_birth")
	private String dayBirth;
	@Column(name = "month_birth")
	private String monthBirth;
	@Column(name = "year_birth")
	private String yearBirth;
	@Column(name = "hour_birth")
	private String hourBirth;
	@Column(name = "minute_birth")
	private String minuteBirth;
	@Column(name = "second_birth")
	private String secondBirth;
	private String latitude;
	@Column(name = "lat_deg")
	private String latDeg;
	@Column(name = "lat_min")
	private String latMin;
	@Column(name = "lat_ns")
	private String latNS;
	private String longitude;
	@Column(name = "long_deg")
	private String longDeg;
	@Column(name = "long_min")
	private String longMin;
	@Column(name = "long_ew")
	private String longEW;
	@Column(name = "time_zone")
	private String timeZone;
	@Column(name = "user_verified")
	private boolean userVerified;
	@Column(name = "marital_status")
	private String maritalStatus;
	@Column(name = "device_id")
	private String deviceId;
	@Column(name = "app_version")
	private String appVersion;
	@Column(name = "android_version")
	private String androidVersion;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user"), inverseJoinColumns = @JoinColumn(name = "role"))
	private Set<Role> roles = new HashSet<>();

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}

	@Override
	public String getUsername() {
		return loginId;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public String getPassword() {
		return password;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public boolean isUserActive() {
		return userActive;
	}

	public void setUserActive(boolean userActive) {
		this.userActive = userActive;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public Date getDeletedDate() {
		return deletedDate;
	}

	public void setDeletedDate(Date deletedDate) {
		this.deletedDate = deletedDate;
	}

	public Date getDeleteAfter() {
		return deleteAfter;
	}

	public void setDeleteAfter(Date deleteAfter) {
		this.deleteAfter = deleteAfter;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public String getDcrptpassword() {
		return dcrptpassword;
	}

	public void setDcrptpassword(String dcrptpassword) {
		this.dcrptpassword = dcrptpassword;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getMobilecc() {
		return mobilecc;
	}

	public void setMobilecc(String mobilecc) {
		this.mobilecc = mobilecc;
	}

	public String getDayBirth() {
		return dayBirth;
	}

	public void setDayBirth(String dayBirth) {
		this.dayBirth = dayBirth;
	}

	public String getMonthBirth() {
		return monthBirth;
	}

	public void setMonthBirth(String monthBirth) {
		this.monthBirth = monthBirth;
	}

	public String getYearBirth() {
		return yearBirth;
	}

	public void setYearBirth(String yearBirth) {
		this.yearBirth = yearBirth;
	}

	public String getHourBirth() {
		return hourBirth;
	}

	public void setHourBirth(String hourBirth) {
		this.hourBirth = hourBirth;
	}

	public String getMinuteBirth() {
		return minuteBirth;
	}

	public void setMinuteBirth(String minuteBirth) {
		this.minuteBirth = minuteBirth;
	}

	public String getSecondBirth() {
		return secondBirth;
	}

	public void setSecondBirth(String secondBirth) {
		this.secondBirth = secondBirth;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLatDeg() {
		return latDeg;
	}

	public void setLatDeg(String latDeg) {
		this.latDeg = latDeg;
	}

	public String getLatMin() {
		return latMin;
	}

	public void setLatMin(String latMin) {
		this.latMin = latMin;
	}

	public String getLatNS() {
		return latNS;
	}

	public void setLatNS(String latNS) {
		this.latNS = latNS;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLongDeg() {
		return longDeg;
	}

	public void setLongDeg(String longDeg) {
		this.longDeg = longDeg;
	}

	public String getLongMin() {
		return longMin;
	}

	public void setLongMin(String longMin) {
		this.longMin = longMin;
	}

	public String getLongEW() {
		return longEW;
	}

	public void setLongEW(String longEW) {
		this.longEW = longEW;
	}

	public String getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}

	public boolean isUserVerified() {
		return userVerified;
	}

	public void setUserVerified(boolean userVerified) {
		this.userVerified = userVerified;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getAppVersion() {
		return appVersion;
	}

	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}

	public String getAndroidVersion() {
		return androidVersion;
	}

	public void setAndroidVersion(String androidVersion) {
		this.androidVersion = androidVersion;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
