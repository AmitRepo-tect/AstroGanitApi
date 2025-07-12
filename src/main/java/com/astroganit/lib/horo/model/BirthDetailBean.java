package com.astroganit.lib.horo.model;
import java.io.Serializable;

public class BirthDetailBean implements Serializable {
    private long id = -1L;
    private String name;
    private String sex;
    private DateTimeBean dateTimeBean;
    private PlaceDetail placeDetail;
    private String dst;
    private String ayanamsa;
    private String charting;
    private String kphn;
    private String button1;
    private String languageCode;
    private Long createTime;
    private Long updateTime;
    private Long viewTime;
    public BirthDetailBean() {
    	
    }
    // Constructor
    public BirthDetailBean(String name, String sex, DateTimeBean dateTimeBean, PlaceDetail placeDetail, String dst, String ayanamsa, String charting, String kphn, String button1, String languageCode, Long createTime, Long updateTime, Long viewTime) {
        this.name = name;
        this.sex = sex;
        this.dateTimeBean = dateTimeBean;
        this.placeDetail = placeDetail;
        this.dst = dst;
        this.ayanamsa = ayanamsa;
        this.charting = charting;
        this.kphn = kphn;
        this.button1 = button1;
        this.languageCode = languageCode;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.viewTime = viewTime;
    }

    // Getters and Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public DateTimeBean getDateTimeBean() {
        return dateTimeBean;
    }

    public void setDateTimeBean(DateTimeBean dateTimeBean) {
        this.dateTimeBean = dateTimeBean;
    }

    public PlaceDetail getPlaceDetail() {
        return placeDetail;
    }

    public void setPlaceDetail(PlaceDetail placeDetail) {
        this.placeDetail = placeDetail;
    }

    public String getDst() {
        return dst;
    }

    public void setDst(String dst) {
        this.dst = dst;
    }

    public String getAyanamsa() {
        return ayanamsa;
    }

    public void setAyanamsa(String ayanamsa) {
        this.ayanamsa = ayanamsa;
    }

    public String getCharting() {
        return charting;
    }

    public void setCharting(String charting) {
        this.charting = charting;
    }

    public String getKphn() {
        return kphn;
    }

    public void setKphn(String kphn) {
        this.kphn = kphn;
    }

    public String getButton1() {
        return button1;
    }

    public void setButton1(String button1) {
        this.button1 = button1;
    }

    public String getLanguageCode() {
        return languageCode;
    }

    public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    public Long getViewTime() {
        return viewTime;
    }

    public void setViewTime(Long viewTime) {
        this.viewTime = viewTime;
    }

    @Override
    public String toString() {
        return "BirthDetailBean{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", dateTimeBean=" + dateTimeBean +
                ", placeDetail=" + placeDetail +
                ", dst='" + dst + '\'' +
                ", ayanamsa='" + ayanamsa + '\'' +
                ", charting='" + charting + '\'' +
                ", kphn='" + kphn + '\'' +
                ", button1='" + button1 + '\'' +
                ", languageCode='" + languageCode + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", viewTime=" + viewTime +
                '}';
    }
}
