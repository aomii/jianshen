package com.rabbit.fitness.pojo;

import java.util.Date;

public class WebCourse {
    private Integer webcId;

    private String webcTitle;

    private String webcContent;

    private Date webcTime;

    private Integer webcViewcount;

    private String webcDesc;

    public Integer getWebcId() {
        return webcId;
    }

    public void setWebcId(Integer webcId) {
        this.webcId = webcId;
    }

    public String getWebcTitle() {
        return webcTitle;
    }

    public void setWebcTitle(String webcTitle) {
        this.webcTitle = webcTitle;
    }

    public String getWebcContent() {
        return webcContent;
    }

    public void setWebcContent(String webcContent) {
        this.webcContent = webcContent;
    }

    public Date getWebcTime() {
        return webcTime;
    }

    public void setWebcTime(Date webcTime) {
        this.webcTime = webcTime;
    }

    public Integer getWebcViewcount() {
        return webcViewcount;
    }

    public void setWebcViewcount(Integer webcViewcount) {
        this.webcViewcount = webcViewcount;
    }

    public String getWebcDesc() {
        return webcDesc;
    }

    public void setWebcDesc(String webcDesc) {
        this.webcDesc = webcDesc;
    }

}