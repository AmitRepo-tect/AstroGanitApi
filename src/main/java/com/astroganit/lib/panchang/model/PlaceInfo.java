package com.astroganit.lib.panchang.model;

public class PlaceInfo {

	private String city = "New Delhi";
	private String state = "New Delhi";
	double latitude = 28.644800;
	double longitude = 77.216721;
	double timezone = 5.5;

	// Constructors, Getters and Setters
	public PlaceInfo(String city, String state, double latitude, double longitude, double timezone) {
		this.city = city;
		this.state = state;
		this.latitude = latitude;
		this.longitude = longitude;
		this.timezone = timezone;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getTimezone() {
		return timezone;
	}

	public void setTimezone(double timezone) {
		this.timezone = timezone;
	}

	
}
