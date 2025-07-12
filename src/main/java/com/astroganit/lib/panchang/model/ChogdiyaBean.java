package com.astroganit.lib.panchang.model;

import java.io.Serializable;

public class ChogdiyaBean implements Serializable {
    private String planetName;
    private String enterTime;
    private String exitTime;
    private String planetMeaning;
    private String duration;

    // Constructor
    public ChogdiyaBean(String planetName, String enterTime, String exitTime, String planetMeaning, String duration) {
        this.planetName = planetName;
        this.enterTime = enterTime;
        this.exitTime = exitTime;
        this.planetMeaning = planetMeaning;
        this.duration = duration;
    }

    // Getters and Setters
    public String getPlanetName() {
        return planetName;
    }

    public void setPlanetName(String planetName) {
        this.planetName = planetName;
    }

    public String getEnterTime() {
        return enterTime;
    }

    public void setEnterTime(String enterTime) {
        this.enterTime = enterTime;
    }

    public String getExitTime() {
        return exitTime;
    }

    public void setExitTime(String exitTime) {
        this.exitTime = exitTime;
    }

    public String getPlanetMeaning() {
        return planetMeaning;
    }

    public void setPlanetMeaning(String planetMeaning) {
        this.planetMeaning = planetMeaning;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "ChogdiyaBean{" +
                "planetName='" + planetName + '\'' +
                ", enterTime='" + enterTime + '\'' +
                ", exitTime='" + exitTime + '\'' +
                ", planetMeaning='" + planetMeaning + '\'' +
                ", duration='" + duration + '\'' +
                '}';
    }
}
