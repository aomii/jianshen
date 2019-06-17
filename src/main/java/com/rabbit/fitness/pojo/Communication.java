package com.rabbit.fitness.pojo;

public class Communication {

    private String sendName;
    private String receiveName;
    private String message;
    private String time;
    private String isRead;

    public String getIsRead() {
        return isRead;
    }

    public void setIsRead(String isRead) {
        this.isRead = isRead;
    }

    public String getSendName() {
        return sendName;
    }

    public void setSendName(String sendName) {
        this.sendName = sendName;
    }

    public String getReceiveName() {
        return receiveName;
    }

    public void setReceiveName(String receiveName) {
        this.receiveName = receiveName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Communication() {
    }

    public Communication(String sendName, String receiveName, String message, String time, String read) {
        this.sendName = sendName;
        this.receiveName = receiveName;
        this.message = message;
        this.time = time;
    }
}
