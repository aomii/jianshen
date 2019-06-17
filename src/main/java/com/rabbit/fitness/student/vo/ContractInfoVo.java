package com.rabbit.fitness.student.vo;

import java.util.Date;

public class ContractInfoVo {
    private String gymName;
    private String productName;
    private Date begin;
    private Date end;

    public String getGymName() {
        return gymName;
    }

    public void setGymName(String gymName) {
        this.gymName = gymName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Date getBegin() {
        return begin;
    }

    public void setBegin(Date begin) {
        this.begin = begin;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    @Override
    public String toString() {
        return "ContractInfoVo{" +
                "gymName='" + gymName + '\'' +
                ", productName='" + productName + '\'' +
                ", begin=" + begin +
                ", end=" + end +
                '}';
    }
}
