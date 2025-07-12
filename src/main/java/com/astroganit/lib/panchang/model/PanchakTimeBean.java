package com.astroganit.lib.panchang.model;

import java.io.Serializable;

public class PanchakTimeBean implements Serializable {

    private final String startDate;
    private final String endDate;
    private final String startDay;
    private final String endDay;
    private final String startTime;
    private final String endTime;
    private final String startMonth;
    private final String endMonth;
    private final String startYear;
    private final String endYear;

    public PanchakTimeBean(String startDate, String endDate, String startDay, String endDay,
                           String startTime, String endTime, String startMonth, String endMonth,
                           String startYear, String endYear) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.startDay = startDay;
        this.endDay = endDay;
        this.startTime = startTime;
        this.endTime = endTime;
        this.startMonth = startMonth;
        this.endMonth = endMonth;
        this.startYear = startYear;
        this.endYear = endYear;
    }

    public String getStartDate() { return startDate; }
    public String getEndDate() { return endDate; }
    public String getStartDay() { return startDay; }
    public String getEndDay() { return endDay; }
    public String getStartTime() { return startTime; }
    public String getEndTime() { return endTime; }
    public String getStartMonth() { return startMonth; }
    public String getEndMonth() { return endMonth; }
    public String getStartYear() { return startYear; }
    public String getEndYear() { return endYear; }
}
