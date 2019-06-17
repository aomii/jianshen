package com.rabbit.fitness.pojo;

import java.util.Date;

public class Knowledge {
    private Integer knlgId;

    private String knlgTitle;

    private String knlgContent;

    private Date knlgPosttime;

    private Integer knlgViewcount;

    public Integer getKnlgId() {
        return knlgId;
    }

    public void setKnlgId(Integer knlgId) {
        this.knlgId = knlgId;
    }

    public String getKnlgTitle() {
        return knlgTitle;
    }

    public void setKnlgTitle(String knlgTitle) {
        this.knlgTitle = knlgTitle;
    }

    public String getKnlgContent() {
        return knlgContent;
    }

    public void setKnlgContent(String knlgContent) {
        this.knlgContent = knlgContent;
    }

    public Date getKnlgPosttime() {
        return knlgPosttime;
    }

    public void setKnlgPosttime(Date knlgPosttime) {
        this.knlgPosttime = knlgPosttime;
    }

    public Integer getKnlgViewcount() {
        return knlgViewcount;
    }

    public void setKnlgViewcount(Integer knlgViewcount) {
        this.knlgViewcount = knlgViewcount;
    }
}