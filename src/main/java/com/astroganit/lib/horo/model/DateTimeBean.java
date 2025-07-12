package com.astroganit.lib.horo.model;

import java.io.Serializable;

public class DateTimeBean implements Serializable {
    private String day;
    private String month;
    private String year;
    private String hrs;
    private String min;
    private String sec;
    public DateTimeBean() {
    	
    }
    // Constructor
    public DateTimeBean(String day, String month, String year, String hrs, String min, String sec) {
        this.day = day;
        this.month = month;
        this.year = year;
        this.hrs = hrs;
        this.min = min;
        this.sec = sec;
    }

    // Getters and Setters
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

    // toString method for displaying object information
    @Override
    public String toString() {
        return "DateTimeBean{" +
                "day='" + day + '\'' +
                ", month='" + month + '\'' +
                ", year='" + year + '\'' +
                ", hrs='" + hrs + '\'' +
                ", min='" + min + '\'' +
                ", sec='" + sec + '\'' +
                '}';
    }
}
