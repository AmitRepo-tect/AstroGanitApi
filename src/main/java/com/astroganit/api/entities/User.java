package com.astroganit.api.entities;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import jakarta.persistence.Entity;
import jakarta.persistence.CascadeType;
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
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
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
	@ManyToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
	@JoinTable(name = "user_role", joinColumns = {
			@JoinColumn(name = "user", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "role", referencedColumnName = "id") })
	private Set<Role> roles = new HashSet();

	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<SimpleGrantedAuthority> authorties = (List) this.roles.stream().map((role) -> {
			return new SimpleGrantedAuthority(role.getName());
		}).collect(Collectors.toList());
		return null;
	}

	public String getUsername() {
		return this.mobile;
	}

	public boolean isAccountNonExpired() {
		return true;
	}

	public boolean isAccountNonLocked() {
		return true;
	}

	public boolean isCredentialsNonExpired() {
		return true;
	}

	public boolean isEnabled() {
		return true;
	}

	public int getId() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}

	public String getEmail() {
		return this.email;
	}

	public String getPassword() {
		return this.password;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public Date getUpdatedDate() {
		return this.updatedDate;
	}

	public boolean isUserActive() {
		return this.userActive;
	}

	public String getAbout() {
		return this.about;
	}

	public String getDcrptpassword() {
		return this.dcrptpassword;
	}

	public String getGender() {
		return this.gender;
	}

	public String getPlace() {
		return this.place;
	}

	public String getCountry() {
		return this.country;
	}

	public String getState() {
		return this.state;
	}

	public String getMobile() {
		return this.mobile;
	}

	public String getMobilecc() {
		return this.mobilecc;
	}

	public String getDayBirth() {
		return this.dayBirth;
	}

	public String getMonthBirth() {
		return this.monthBirth;
	}

	public String getYearBirth() {
		return this.yearBirth;
	}

	public String getHourBirth() {
		return this.hourBirth;
	}

	public String getMinuteBirth() {
		return this.minuteBirth;
	}

	public String getSecondBirth() {
		return this.secondBirth;
	}

	public String getLatitude() {
		return this.latitude;
	}

	public String getLatDeg() {
		return this.latDeg;
	}

	public String getLatMin() {
		return this.latMin;
	}

	public String getLatNS() {
		return this.latNS;
	}

	public String getLongitude() {
		return this.longitude;
	}

	public String getLongDeg() {
		return this.longDeg;
	}

	public String getLongMin() {
		return this.longMin;
	}

	public String getLongEW() {
		return this.longEW;
	}

	public String getTimeZone() {
		return this.timeZone;
	}

	public boolean isUserVerified() {
		return this.userVerified;
	}

	public String getMaritalStatus() {
		return this.maritalStatus;
	}

	public String getDeviceId() {
		return this.deviceId;
	}

	public String getAppVersion() {
		return this.appVersion;
	}

	public String getAndroidVersion() {
		return this.androidVersion;
	}

	public Set<Role> getRoles() {
		return this.roles;
	}

	public void setId(final int id) {
		this.id = id;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public void setEmail(final String email) {
		this.email = email;
	}

	public void setPassword(final String password) {
		this.password = password;
	}

	public void setCreatedDate(final Date createdDate) {
		this.createdDate = createdDate;
	}

	public void setUpdatedDate(final Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public void setUserActive(final boolean userActive) {
		this.userActive = userActive;
	}

	public void setAbout(final String about) {
		this.about = about;
	}

	public void setDcrptpassword(final String dcrptpassword) {
		this.dcrptpassword = dcrptpassword;
	}

	public void setGender(final String gender) {
		this.gender = gender;
	}

	public void setPlace(final String place) {
		this.place = place;
	}

	public void setCountry(final String country) {
		this.country = country;
	}

	public void setState(final String state) {
		this.state = state;
	}

	public void setMobile(final String mobile) {
		this.mobile = mobile;
	}

	public void setMobilecc(final String mobilecc) {
		this.mobilecc = mobilecc;
	}

	public void setDayBirth(final String dayBirth) {
		this.dayBirth = dayBirth;
	}

	public void setMonthBirth(final String monthBirth) {
		this.monthBirth = monthBirth;
	}

	public void setYearBirth(final String yearBirth) {
		this.yearBirth = yearBirth;
	}

	public void setHourBirth(final String hourBirth) {
		this.hourBirth = hourBirth;
	}

	public void setMinuteBirth(final String minuteBirth) {
		this.minuteBirth = minuteBirth;
	}

	public void setSecondBirth(final String secondBirth) {
		this.secondBirth = secondBirth;
	}

	public void setLatitude(final String latitude) {
		this.latitude = latitude;
	}

	public void setLatDeg(final String latDeg) {
		this.latDeg = latDeg;
	}

	public void setLatMin(final String latMin) {
		this.latMin = latMin;
	}

	public void setLatNS(final String latNS) {
		this.latNS = latNS;
	}

	public void setLongitude(final String longitude) {
		this.longitude = longitude;
	}

	public void setLongDeg(final String longDeg) {
		this.longDeg = longDeg;
	}

	public void setLongMin(final String longMin) {
		this.longMin = longMin;
	}

	public void setLongEW(final String longEW) {
		this.longEW = longEW;
	}

	public void setTimeZone(final String timeZone) {
		this.timeZone = timeZone;
	}

	public void setUserVerified(final boolean userVerified) {
		this.userVerified = userVerified;
	}

	public void setMaritalStatus(final String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public void setDeviceId(final String deviceId) {
		this.deviceId = deviceId;
	}

	public void setAppVersion(final String appVersion) {
		this.appVersion = appVersion;
	}

	public void setAndroidVersion(final String androidVersion) {
		this.androidVersion = androidVersion;
	}

	public void setRoles(final Set<Role> roles) {
		this.roles = roles;
	}
}
