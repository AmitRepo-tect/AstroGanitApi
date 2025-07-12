package com.astroganit.lib.panchang.model;

import java.io.Serializable;
import java.util.ArrayList;

public class SampurnPanchangModel implements Serializable {
    private String sunRiseTime;
    private String sunSetTime;
    private ArrayList<String> moonRiseAndSetTime;
    private ArrayList<String> tithi;
    private int tithiInt;
    private ArrayList<String> nakshtra;
    private ArrayList<String> yog;
    private ArrayList<String> karan;
    private String paksha;
    private String vara;
    private String ritu;
    private ArrayList<String> moonSign;
    private String amantMonth;
    private String purnimantMonth;
    private String shakaSamvat;
    private String kaliSamvat;
    private String vikramSamvat;
    private String dayDuration;
    private ArrayList<String> abhijitMuhurat;
    private ArrayList<ArrayList<String>> ashubhMuhuratList;
    private String dishaShool;
    private String tarabal;
    private String chandrabal;

    // Constructor
    public SampurnPanchangModel(String sunRiseTime, String sunSetTime, ArrayList<String> moonRiseAndSetTime,
                                ArrayList<String> tithi, int tithiInt, ArrayList<String> nakshtra,
                                ArrayList<String> yog, ArrayList<String> karan, String paksha, String vara,
                                String ritu, ArrayList<String> moonSign, String amantMonth, String purnimantMonth,
                                String shakaSamvat, String kaliSamvat, String vikramSamvat, String dayDuration,
                                ArrayList<String> abhijitMuhurat, ArrayList<ArrayList<String>> ashubhMuhuratList,
                                String dishaShool, String tarabal, String chandrabal) {
        this.sunRiseTime = sunRiseTime;
        this.sunSetTime = sunSetTime;
        this.moonRiseAndSetTime = moonRiseAndSetTime;
        this.tithi = tithi;
        this.tithiInt = tithiInt;
        this.nakshtra = nakshtra;
        this.yog = yog;
        this.karan = karan;
        this.paksha = paksha;
        this.vara = vara;
        this.ritu = ritu;
        this.moonSign = moonSign;
        this.amantMonth = amantMonth;
        this.purnimantMonth = purnimantMonth;
        this.shakaSamvat = shakaSamvat;
        this.kaliSamvat = kaliSamvat;
        this.vikramSamvat = vikramSamvat;
        this.dayDuration = dayDuration;
        this.abhijitMuhurat = abhijitMuhurat;
        this.ashubhMuhuratList = ashubhMuhuratList;
        this.dishaShool = dishaShool;
        this.tarabal = tarabal;
        this.chandrabal = chandrabal;
    }

    // Getters and Setters
    public String getSunRiseTime() {
        return sunRiseTime;
    }

    public void setSunRiseTime(String sunRiseTime) {
        this.sunRiseTime = sunRiseTime;
    }

    public String getSunSetTime() {
        return sunSetTime;
    }

    public void setSunSetTime(String sunSetTime) {
        this.sunSetTime = sunSetTime;
    }

    public ArrayList<String> getMoonRiseAndSetTime() {
        return moonRiseAndSetTime;
    }

    public void setMoonRiseAndSetTime(ArrayList<String> moonRiseAndSetTime) {
        this.moonRiseAndSetTime = moonRiseAndSetTime;
    }

    public ArrayList<String> getTithi() {
        return tithi;
    }

    public void setTithi(ArrayList<String> tithi) {
        this.tithi = tithi;
    }

    public int getTithiInt() {
        return tithiInt;
    }

    public void setTithiInt(int tithiInt) {
        this.tithiInt = tithiInt;
    }

    public ArrayList<String> getNakshtra() {
        return nakshtra;
    }

    public void setNakshtra(ArrayList<String> nakshtra) {
        this.nakshtra = nakshtra;
    }

    public ArrayList<String> getYog() {
        return yog;
    }

    public void setYog(ArrayList<String> yog) {
        this.yog = yog;
    }

    public ArrayList<String> getKaran() {
        return karan;
    }

    public void setKaran(ArrayList<String> karan) {
        this.karan = karan;
    }

    public String getPaksha() {
        return paksha;
    }

    public void setPaksha(String paksha) {
        this.paksha = paksha;
    }

    public String getVara() {
        return vara;
    }

    public void setVara(String vara) {
        this.vara = vara;
    }

    public String getRitu() {
        return ritu;
    }

    public void setRitu(String ritu) {
        this.ritu = ritu;
    }

    public ArrayList<String> getMoonSign() {
        return moonSign;
    }

    public void setMoonSign(ArrayList<String> moonSign) {
        this.moonSign = moonSign;
    }

    public String getAmantMonth() {
        return amantMonth;
    }

    public void setAmantMonth(String amantMonth) {
        this.amantMonth = amantMonth;
    }

    public String getPurnimantMonth() {
        return purnimantMonth;
    }

    public void setPurnimantMonth(String purnimantMonth) {
        this.purnimantMonth = purnimantMonth;
    }

    public String getShakaSamvat() {
        return shakaSamvat;
    }

    public void setShakaSamvat(String shakaSamvat) {
        this.shakaSamvat = shakaSamvat;
    }

    public String getKaliSamvat() {
        return kaliSamvat;
    }

    public void setKaliSamvat(String kaliSamvat) {
        this.kaliSamvat = kaliSamvat;
    }

    public String getVikramSamvat() {
        return vikramSamvat;
    }

    public void setVikramSamvat(String vikramSamvat) {
        this.vikramSamvat = vikramSamvat;
    }

    public String getDayDuration() {
        return dayDuration;
    }

    public void setDayDuration(String dayDuration) {
        this.dayDuration = dayDuration;
    }

    public ArrayList<String> getAbhijitMuhurat() {
        return abhijitMuhurat;
    }

    public void setAbhijitMuhurat(ArrayList<String> abhijitMuhurat) {
        this.abhijitMuhurat = abhijitMuhurat;
    }

    public ArrayList<ArrayList<String>> getAshubhMuhuratList() {
        return ashubhMuhuratList;
    }

    public void setAshubhMuhuratList(ArrayList<ArrayList<String>> ashubhMuhuratList) {
        this.ashubhMuhuratList = ashubhMuhuratList;
    }

    public String getDishaShool() {
        return dishaShool;
    }

    public void setDishaShool(String dishaShool) {
        this.dishaShool = dishaShool;
    }

    public String getTarabal() {
        return tarabal;
    }

    public void setTarabal(String tarabal) {
        this.tarabal = tarabal;
    }

    public String getChandrabal() {
        return chandrabal;
    }

    public void setChandrabal(String chandrabal) {
        this.chandrabal = chandrabal;
    }

    // Override toString, equals, and hashCode if needed.
}
