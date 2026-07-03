package com.astroganit.api.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "birth_detail_info", uniqueConstraints = {
		@UniqueConstraint(name = "uk_user_client_uuid", columnNames = { "user_id", "client_uuid" }) })
public class BirthDetailInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "user_id", nullable = false)
	private Long userId;
	@Column(name = "client_uuid", nullable = false)
	private String clientUuid;
	@Column(name = "name")
	private String name;
	@Column(name = "sex")
	private String sex;
	@Column(name = "day")
	private String day;
	@Column(name = "month")
	private String month;
	@Column(name = "year")
	private String year;
	@Column(name = "hrs")
	private String hrs;
	@Column(name = "min")
	private String min;
	@Column(name = "sec")
	private String sec;
	@Column(name = "place")
	private String place;
	@Column(name = "long_deg")
	private String longDeg;
	@Column(name = "long_min")
	private String longMin;
	@Column(name = "long_ew")
	private String longEW;
	@Column(name = "lat_deg")
	private String latDeg;
	@Column(name = "lat_min")
	private String latMin;
	@Column(name = "lat_ns")
	private String latNS;
	@Column(name = "time_zone")
	private String timeZone;
	@Column(name = "timezone_str")
	private String timezoneStr;
	@Column(name = "country")
	private String country;
	@Column(name = "place_id")
	private Long placeId;
	@Column(name = "state")
	private String state;
	@Column(name = "dst")
	private String dst;
	@Column(name = "ayanamsa")
	private String ayanamsa;
	@Column(name = "kphn")
	private String kphn;
	@Column(name = "create_time")
	private Long createTime;
	@Column(name = "update_time")
	private Long updateTime;
	@Column(name = "view_time")
	private Long viewTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getClientUuid() {
		return clientUuid;
	}

	public void setClientUuid(String clientUuid) {
		this.clientUuid = clientUuid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getHrs() {
		return hrs;
	}

	public void setHrs(String hrs) {
		this.hrs = hrs;
	}

	public String getMin() {
		return min;
	}

	public void setMin(String min) {
		this.min = min;
	}

	public String getSec() {
		return sec;
	}

	public void setSec(String sec) {
		this.sec = sec;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
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

	public String getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}

	public String getTimezoneStr() {
		return timezoneStr;
	}

	public void setTimezoneStr(String timezoneStr) {
		this.timezoneStr = timezoneStr;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Long getPlaceId() {
		return placeId;
	}

	public void setPlaceId(Long placeId) {
		this.placeId = placeId;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getDst() {
		return dst;
	}

	public void setDst(String dst) {
		this.dst = dst;
	}

	public String getAyanamsa() {
		return ayanamsa;
	}

	public void setAyanamsa(String ayanamsa) {
		this.ayanamsa = ayanamsa;
	}

	public String getKphn() {
		return kphn;
	}

	public void setKphn(String kphn) {
		this.kphn = kphn;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public Long getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Long updateTime) {
		this.updateTime = updateTime;
	}

	public Long getViewTime() {
		return viewTime;
	}

	public void setViewTime(Long viewTime) {
		this.viewTime = viewTime;
	}

	@PrePersist
	public void prePersist() {
		long now = System.currentTimeMillis();
		this.createTime = now;
		this.updateTime = now;
		this.viewTime = now;
	}

	@PreUpdate
	public void preUpdate() {
		long now = System.currentTimeMillis();
		this.updateTime = now;
		this.viewTime = now;
	}
}