package com.rabbit.fitness.pojo;

public class Addfriend {
    private int senduid;
    private int agreeuid;
    private String status;
    private String sendname;

    public String getSendname() {
        return sendname;
    }

    public void setSendname(String sendname) {
        this.sendname = sendname;
    }

    public int getSenduid() {
        return senduid;
    }

    public void setSenduid(int senduid) {
        this.senduid = senduid;
    }

    public int getAgreeuid() {
        return agreeuid;
    }

    public void setAgreeuid(int agreeuid) {
        this.agreeuid = agreeuid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
