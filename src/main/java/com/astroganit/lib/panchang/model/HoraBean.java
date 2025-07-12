package com.astroganit.lib.panchang.model;

import java.io.Serializable;

public class HoraBean implements Serializable {
    
    private String planetName;
    private String enterTime;
    private String exitTime;
    private String planetMeaning;
    private String planetCurrentHoraMeaning;
    private String duration;
    // private String doghatiSecondMeaning;
    // private String doghatiSecondMeaningwikipedia;
    // private String doghatimuhurat;

    // Constructor
    public HoraBean(String planetName, String enterTime, String exitTime, 
                    String planetMeaning, String planetCurrentHoraMeaning, 
                    String duration) {
        this.planetName = planetName;
        this.enterTime = enterTime;
        this.exitTime = exitTime;
        this.planetMeaning = planetMeaning;
        this.planetCurrentHoraMeaning = planetCurrentHoraMeaning;
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

    public String getPlanetCurrentHoraMeaning() {
        return planetCurrentHoraMeaning;
    }

    public void setPlanetCurrentHoraMeaning(String planetCurrentHoraMeaning) {
        this.planetCurrentHoraMeaning = planetCurrentHoraMeaning;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    // Optionally add the missing fields later if needed
    // public String getDoghatiSecondMeaning() {
    //     return doghatiSecondMeaning;
    // }
    // public void setDoghatiSecondMeaning(String doghatiSecondMeaning) {
    //     this.doghatiSecondMeaning = doghatiSecondMeaning;
    // }

    // public String getDoghatiSecondMeaningWikipedia() {
    //     return doghatiSecondMeaningwikipedia;
    // }

    // public void setDoghatiSecondMeaningWikipedia(String doghatiSecondMeaningwikipedia) {
    //     this.doghatiSecondMeaningwikipedia = doghatiSecondMeaningwikipedia;
    // }

    // public String getDoghatiMuhurat() {
    //     return doghatimuhurat;
    // }

    // public void setDoghatiMuhurat(String doghatimuhurat) {
    //     this.doghatimuhurat = doghatimuhurat;
    // }
}
