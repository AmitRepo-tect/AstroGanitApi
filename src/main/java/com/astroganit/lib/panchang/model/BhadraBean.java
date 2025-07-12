package com.astroganit.lib.panchang.model;
import java.io.Serializable;

public class BhadraBean implements Serializable {
    private String startDay;
    private String startMonth;
    private String startDate;
    private String startTime;
    private String endDay;
    private String endMonth;
    private String endDate;
    private String endTime;

    public BhadraBean(String startDay, String startMonth, String startDate, String startTime,
                      String endDay, String endMonth, String endDate, String endTime) {
        this.startDay = startDay;
        this.startMonth = startMonth;
        this.startDate = startDate;
        this.startTime = startTime;
        this.endDay = endDay;
        this.endMonth = endMonth;
        this.endDate = endDate;
        this.endTime = endTime;
    }

    // Getters
    public String getStartDay() { return startDay; }
    public String getStartMonth() { return startMonth; }
    public String getStartDate() { return startDate; }
    public String getStartTime() { return startTime; }
    public String getEndDay() { return endDay; }
    public String getEndMonth() { return endMonth; }
    public String getEndDate() { return endDate; }
    public String getEndTime() { return endTime; }

    // Optionally, you can add setters if needed
    // toString(), equals(), and hashCode() can also be added if required
}
