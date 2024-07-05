package com.astroganit.api.payload;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;


public class UserDto {
   private int id;
   @NotEmpty
   @NotNull
   @Size(
      min = 3,
      message = "user name must be min of 3 char !!"
   )
   private String name;
   @Email
   @NotEmpty
   private String email;
   @NotNull
   @NotEmpty
   private String password;
   @NotNull
   private String about;
   @JsonProperty
   @NotNull
   @NotEmpty
   private boolean userActive;
   @JsonProperty
   private Date createdDate;
   @JsonProperty
   private Date updatedDate;
   private Set<RoleDto> roles = new HashSet();
   private String dcrptpassword;
   private String gender;
   private String place;
   private String country;
   private String state;
   private String mobile;
   private String mobilecc;
   @JsonProperty
   private String dayBirth;
   @JsonProperty
   private String monthBirth;
   @JsonProperty
   private String yearBirth;
   @JsonProperty
   private String hourBirth;
   @JsonProperty
   private String minuteBirth;
   @JsonProperty
   private String secondBirth;
   private String latitude;
   @JsonProperty
   private String latDeg;
   @JsonProperty
   private String latMin;
   @JsonProperty
   private String latNS;
   @JsonProperty
   private String longitude;
   @JsonProperty
   private String longDeg;
   @JsonProperty
   private String longMin;
   @JsonProperty
   private String longEW;
   @JsonProperty
   private String timeZone;
   @JsonProperty
   private boolean userVerified;
   @JsonProperty
   private String maritalStatus;
   @JsonProperty
   private String deviceId;
   @JsonProperty
   private String appVersion;
   @JsonProperty
   private String androidVersion;

   public int getId() {
      return this.id;
   }

   @NotNull
   public String getName() {
      return this.name;
   }

   public String getEmail() {
      return this.email;
   }

   @NotNull
   public String getPassword() {
      return this.password;
   }

   @NotNull
   public String getAbout() {
      return this.about;
   }

   @NotNull
   public boolean isUserActive() {
      return this.userActive;
   }

   public Date getCreatedDate() {
      return this.createdDate;
   }

   public Date getUpdatedDate() {
      return this.updatedDate;
   }

   public Set<RoleDto> getRoles() {
      return this.roles;
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

   public void setId(final int id) {
      this.id = id;
   }

   public void setName(@NotNull final String name) {
      this.name = name;
   }

   public void setEmail(final String email) {
      this.email = email;
   }

   public void setPassword(@NotNull final String password) {
      this.password = password;
   }

   public void setAbout(@NotNull final String about) {
      this.about = about;
   }

   @JsonProperty
   public void setUserActive(@NotNull final boolean userActive) {
      this.userActive = userActive;
   }

   @JsonProperty
   public void setCreatedDate(final Date createdDate) {
      this.createdDate = createdDate;
   }

   @JsonProperty
   public void setUpdatedDate(final Date updatedDate) {
      this.updatedDate = updatedDate;
   }

   public void setRoles(final Set<RoleDto> roles) {
      this.roles = roles;
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

   @JsonProperty
   public void setDayBirth(final String dayBirth) {
      this.dayBirth = dayBirth;
   }

   @JsonProperty
   public void setMonthBirth(final String monthBirth) {
      this.monthBirth = monthBirth;
   }

   @JsonProperty
   public void setYearBirth(final String yearBirth) {
      this.yearBirth = yearBirth;
   }

   @JsonProperty
   public void setHourBirth(final String hourBirth) {
      this.hourBirth = hourBirth;
   }

   @JsonProperty
   public void setMinuteBirth(final String minuteBirth) {
      this.minuteBirth = minuteBirth;
   }

   @JsonProperty
   public void setSecondBirth(final String secondBirth) {
      this.secondBirth = secondBirth;
   }

   public void setLatitude(final String latitude) {
      this.latitude = latitude;
   }

   @JsonProperty
   public void setLatDeg(final String latDeg) {
      this.latDeg = latDeg;
   }

   @JsonProperty
   public void setLatMin(final String latMin) {
      this.latMin = latMin;
   }

   @JsonProperty
   public void setLatNS(final String latNS) {
      this.latNS = latNS;
   }

   @JsonProperty
   public void setLongitude(final String longitude) {
      this.longitude = longitude;
   }

   @JsonProperty
   public void setLongDeg(final String longDeg) {
      this.longDeg = longDeg;
   }

   @JsonProperty
   public void setLongMin(final String longMin) {
      this.longMin = longMin;
   }

   @JsonProperty
   public void setLongEW(final String longEW) {
      this.longEW = longEW;
   }

   @JsonProperty
   public void setTimeZone(final String timeZone) {
      this.timeZone = timeZone;
   }

   @JsonProperty
   public void setUserVerified(final boolean userVerified) {
      this.userVerified = userVerified;
   }

   @JsonProperty
   public void setMaritalStatus(final String maritalStatus) {
      this.maritalStatus = maritalStatus;
   }

   @JsonProperty
   public void setDeviceId(final String deviceId) {
      this.deviceId = deviceId;
   }

   @JsonProperty
   public void setAppVersion(final String appVersion) {
      this.appVersion = appVersion;
   }

   @JsonProperty
   public void setAndroidVersion(final String androidVersion) {
      this.androidVersion = androidVersion;
   }
}
