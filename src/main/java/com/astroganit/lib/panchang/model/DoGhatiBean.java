package com.astroganit.lib.panchang.model;

import java.io.Serializable;

public class DoGhatiBean implements Serializable {

    private String planetName;
    private String enterTime;
    private String exitTime;
    private String planetMeaning;
    private String planetCurrentHoraMeaning;
    private String doGhatiSecondMeaning;
    private String doGhatiSecondMeaningWikipedia;
    private String doGhatiMuhurat;
    private String duration;

    // Constructor
    public DoGhatiBean(String planetName, String enterTime, String exitTime, String planetMeaning, 
                       String planetCurrentHoraMeaning, String doGhatiSecondMeaning, 
                       String doGhatiSecondMeaningWikipedia, String doGhatiMuhurat, String duration) {
        this.planetName = planetName;
        this.enterTime = enterTime;
        this.exitTime = exitTime;
        this.planetMeaning = planetMeaning;
        this.planetCurrentHoraMeaning = planetCurrentHoraMeaning;
        this.doGhatiSecondMeaning = doGhatiSecondMeaning;
        this.doGhatiSecondMeaningWikipedia = doGhatiSecondMeaningWikipedia;
        this.doGhatiMuhurat = doGhatiMuhurat;
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

    public String getDoGhatiSecondMeaning() {
        return doGhatiSecondMeaning;
    }

    public void setDoGhatiSecondMeaning(String doGhatiSecondMeaning) {
        this.doGhatiSecondMeaning = doGhatiSecondMeaning;
    }

    public String getDoGhatiSecondMeaningWikipedia() {
        return doGhatiSecondMeaningWikipedia;
    }

    public void setDoGhatiSecondMeaningWikipedia(String doGhatiSecondMeaningWikipedia) {
        this.doGhatiSecondMeaningWikipedia = doGhatiSecondMeaningWikipedia;
    }

    public String getDoGhatiMuhurat() {
        return doGhatiMuhurat;
    }

    public void setDoGhatiMuhurat(String doGhatiMuhurat) {
        this.doGhatiMuhurat = doGhatiMuhurat;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "DoGhatiBean{" +
                "planetName='" + planetName + '\'' +
                ", enterTime='" + enterTime + '\'' +
                ", exitTime='" + exitTime + '\'' +
                ", planetMeaning='" + planetMeaning + '\'' +
                ", planetCurrentHoraMeaning='" + planetCurrentHoraMeaning + '\'' +
                ", doGhatiSecondMeaning='" + doGhatiSecondMeaning + '\'' +
                ", doGhatiSecondMeaningWikipedia='" + doGhatiSecondMeaningWikipedia + '\'' +
                ", doGhatiMuhurat='" + doGhatiMuhurat + '\'' +
                ", duration='" + duration + '\'' +
                '}';
    }
}
