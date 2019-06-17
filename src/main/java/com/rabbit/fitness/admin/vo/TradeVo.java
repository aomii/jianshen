package com.rabbit.fitness.admin.vo;

public class TradeVo {
    /*学员姓名*/
    private String stuname;
    /*场馆id*/
    private Integer gid;
    /*签约类型*/
    private String tradeType;

    public String getStuname() {
        return stuname;
    }

    public void setStuname(String stuname) {
        this.stuname = stuname;
    }

    public Integer getGid() {
        return gid;
    }

    public void setGid(Integer gid) {
        this.gid = gid;
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }
}
