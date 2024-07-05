package com.astroganit.api.payload;

public class CityDetailsDto {
   private int id;
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

   public int getId() {
      return this.id;
   }

   public String getPlace() {
      return this.place;
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

   public void setLatDeg(final String latDeg) {
      this.latDeg = latDeg;
   }

   public void setLatMin(final String latMin) {
      this.latMin = latMin;
   }

   public void setLatNS(final String latNS) {
      this.latNS = latNS;
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
}
