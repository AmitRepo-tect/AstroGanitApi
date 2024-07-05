package com.astroganit.api.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class CityDetails {
   @Id
   @GeneratedValue(
      strategy = GenerationType.IDENTITY
   )
   private int id;
   private String place;
   private String latitude;
   @Column(
      name = "lat_deg"
   )
   private String latDeg;
   @Column(
      name = "lat_min"
   )
   private String latMin;
   @Column(
      name = "lat_ns"
   )
   private String latNS;
   private String longitude;
   @Column(
      name = "long_deg"
   )
   private String longDeg;
   @Column(
      name = "long_min"
   )
   private String longMin;
   @Column(
      name = "long_ew"
   )
   private String longEW;
   private String state;
   private String country;
   private String timezone;
   @Column(
      name = "timezone_string"
   )
   private String timezoneStr;

   public int getId() {
      return this.id;
   }

   public String getPlace() {
      return this.place;
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

   public String getState() {
      return this.state;
   }

   public String getCountry() {
      return this.country;
   }

   public String getTimezone() {
      return this.timezone;
   }

   public String getTimezoneStr() {
      return this.timezoneStr;
   }

   public void setId(final int id) {
      this.id = id;
   }

   public void setPlace(final String place) {
      this.place = place;
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

   public void setState(final String state) {
      this.state = state;
   }

   public void setCountry(final String country) {
      this.country = country;
   }

   public void setTimezone(final String timezone) {
      this.timezone = timezone;
   }

   public void setTimezoneStr(final String timezoneStr) {
      this.timezoneStr = timezoneStr;
   }

   public CityDetails() {
   }

   public CityDetails(final int id, final String place, final String latitude, final String latDeg, final String latMin, final String latNS, final String longitude, final String longDeg, final String longMin, final String longEW, final String state, final String country, final String timezone, final String timezoneStr) {
      this.id = id;
      this.place = place;
      this.latitude = latitude;
      this.latDeg = latDeg;
      this.latMin = latMin;
      this.latNS = latNS;
      this.longitude = longitude;
      this.longDeg = longDeg;
      this.longMin = longMin;
      this.longEW = longEW;
      this.state = state;
      this.country = country;
      this.timezone = timezone;
      this.timezoneStr = timezoneStr;
   }
}
