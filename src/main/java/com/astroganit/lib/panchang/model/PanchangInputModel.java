package com.astroganit.lib.panchang.model;
public class PanchangInputModel {
    private PlaceInfo place;
    private DateTimeInfo dateTimeInfo;

    // Constructors, Getters and Setters
    public PanchangInputModel(PlaceInfo place, DateTimeInfo dateTimeInfo) {
        this.place = place;
        this.dateTimeInfo = dateTimeInfo;
    }

    public PlaceInfo getPlace() {
        return place;
    }

    public void setPlace(PlaceInfo place) {
        this.place = place;
    }

    public DateTimeInfo getDateTimeInfo() {
        return dateTimeInfo;
    }

    public void setDateTimeInfo(DateTimeInfo dateTimeInfo) {
        this.dateTimeInfo = dateTimeInfo;
    }
}
