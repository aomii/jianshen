package com.rabbit.fitness.pojo;

import java.util.Date;
import java.util.List;

public class Trade {
    private Integer tradeId;

    private Student student;

    private String  tradeProducts;

    private List<Product> products;

    private Gym gym;

    private Coach coach;

    private Double tradeMoney;

    private String tradeType;

    private Date tradeStarttime;

    private Date tradeOvertime;

    private String tradeDesc;

    public Integer getTradeId() {
        return tradeId;
    }

    public void setTradeId(Integer tradeId) {
        this.tradeId = tradeId;
    }

    public Double getTradeMoney() {
        return tradeMoney;
    }

    public void setTradeMoney(Double tradeMoney) {
        this.tradeMoney = tradeMoney;
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    public Date getTradeStarttime() {
        return tradeStarttime;
    }

    public void setTradeStarttime(Date tradeStarttime) {
        this.tradeStarttime = tradeStarttime;
    }

    public Date getTradeOvertime() {
        return tradeOvertime;
    }

    public void setTradeOvertime(Date tradeOvertime) {
        this.tradeOvertime = tradeOvertime;
    }

    public String getTradeDesc() {
        return tradeDesc;
    }

    public void setTradeDesc(String tradeDesc) {
        this.tradeDesc = tradeDesc;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getTradeProducts() {
        return tradeProducts;
    }

    public void setTradeProducts(String tradeProducts) {
        this.tradeProducts = tradeProducts;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Gym getGym() {
        return gym;
    }

    public void setGym(Gym gym) {
        this.gym = gym;
    }

    public Coach getCoach() {
        return coach;
    }

    public void setCoach(Coach coach) {
        this.coach = coach;
    }

    @Override
    public String toString() {
        return "Trade{" +
                "tradeId=" + tradeId +
                ", student=" + student +
                ", products=" + products +
                ", gym=" + gym +
                ", coach=" + coach +
                ", tradeMoney=" + tradeMoney +
                ", tradeType='" + tradeType + '\'' +
                ", tradeStarttime=" + tradeStarttime +
                ", tradeOvertime=" + tradeOvertime +
                ", tradeDesc='" + tradeDesc + '\'' +
                '}';
    }
}