package com.astroganit.lib.horo.model;
import java.io.Serializable;

public class PlaceDetail implements Serializable {
    private long id;

    private String place;

    private String latDeg;

    private String latMin;

    private String latNS;

    private String longDeg;

    private String longMin;

    private String longEW;

    private String state;

    private String country;

    private String timezone;

    private String timezoneStr;
    public PlaceDetail() {
    	
    }
    // Constructor
    public PlaceDetail(long id, String place, String latDeg, String latMin, String latNS, String longDeg, String longMin, String longEW, String state, String country, String timezone, String timezoneStr) {
        this.id = id;
        this.place = place;
        this.latDeg = latDeg;
        this.latMin = latMin;
        this.latNS = latNS;
        this.longDeg = longDeg;
        this.longMin = longMin;
        this.longEW = longEW;
        this.state = state;
        this.country = country;
        this.timezone = timezone;
        this.timezoneStr = timezoneStr;
    }

    // Getters and Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public String getTimezoneStr() {
        return timezoneStr;
    }

    public void setTimezoneStr(String timezoneStr) {
        this.timezoneStr = timezoneStr;
    }

    // toString method for displaying object information
    @Override
    public String toString() {
        return "PlaceDetail{" +
                "id=" + id +
                ", place='" + place + '\'' +
                ", latDeg='" + latDeg + '\'' +
                ", latMin='" + latMin + '\'' +
                ", latNS='" + latNS + '\'' +
                ", longDeg='" + longDeg + '\'' +
                ", longMin='" + longMin + '\'' +
                ", longEW='" + longEW + '\'' +
                ", state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", timezone='" + timezone + '\'' +
                ", timezoneStr='" + timezoneStr + '\'' +
                '}';
    }
}
