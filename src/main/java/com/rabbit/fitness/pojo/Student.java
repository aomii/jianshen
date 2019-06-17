package com.rabbit.fitness.pojo;



public class Student {
    private Integer stuid;

    private String stuname;

    private double stumoney;

    private String stuage;

    private String stugender;

    private String friendids;

    private String stupic;

    private String oids;

    private Integer uid;

    private String lastlogin;

    private String stuphone;

    private String stubirth;

    private Integer gid;


    //======后台需要字段=====
    private User user;
    //======================


    private Integer cid;

    public Student() {
    }

    public Student(String stuname, String stupic, Integer userId) {
        this.stuname = stuname;
        this.stupic = stupic;
        this.uid = userId;
    }
    public Student(Integer stuid, String stuname, double stumoney, String stuage, String stugender, String friendids, String stupic, String oids, Integer uid, String lastlogin, String stuphone, String stubirth, Integer gid, Integer cid) {
        this.stuid = stuid;
        this.stuname = stuname;
        this.stumoney = stumoney;
        this.stuage = stuage;
        this.stugender = stugender;
        this.friendids = friendids;
        this.stupic = stupic;
        this.oids = oids;
        this.uid = uid;
        this.lastlogin = lastlogin;
        this.stuphone = stuphone;
        this.stubirth = stubirth;
        this.gid = gid;
        this.cid = cid;
    }

    public Integer getStuid() {
        return stuid;
    }

    public void setStuid(Integer stuid) {
        this.stuid = stuid;
    }

    public String getStuname() {
        return stuname;
    }

    public void setStuname(String stuname) {
        this.stuname = stuname;
    }

    public double getStumoney() {
        return stumoney;
    }

    public void setStumoney(double stumoney) {
        this.stumoney = stumoney;
    }

    public String getStuage() {
        return stuage;
    }

    public void setStuage(String stuage) {
        this.stuage = stuage;
    }

    public String getStugender() {
        return stugender;
    }

    public void setStugender(String stugender) {
        this.stugender = stugender;
    }

    public String getFriendids() {
        return friendids;
    }

    public void setFriendids(String friendids) {
        this.friendids = friendids;
    }

    public String getStupic() {
        return stupic;
    }

    public void setStupic(String stupic) {
        this.stupic = stupic;
    }

    public String getOids() {
        return oids;
    }

    public void setOids(String oids) {
        this.oids = oids;
    }



    //====================================后台添加
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    //===========================================



    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getLastlogin() {
        return lastlogin;
    }

    public void setLastlogin(String lastlogin) {
        this.lastlogin = lastlogin;
    }

    public String getStuphone() {
        return stuphone;
    }

    public void setStuphone(String stuphone) {
        this.stuphone = stuphone;
    }

    public String getStubirth() {
        return stubirth;
    }

    public void setStubirth(String stubirth) {
        this.stubirth = stubirth;
    }

    public Integer getGid() {
        return gid;
    }

    public void setGid(Integer gid) {
        this.gid = gid;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    @Override
    public String toString() {
        return "Student{" +
                "stuid=" + stuid +
                ", stuname='" + stuname + '\'' +
                ", stumoney=" + stumoney +
                ", stuage='" + stuage + '\'' +
                ", stugender='" + stugender + '\'' +
                ", friendids='" + friendids + '\'' +
                ", stupic='" + stupic + '\'' +
                ", oids='" + oids + '\'' +
                ", uid=" + uid +
                ", lastlogin='" + lastlogin + '\'' +
                ", stuphone='" + stuphone + '\'' +
                ", stubirth='" + stubirth + '\'' +
                ", gid=" + gid +
                ", user=" + user +
                ", cid=" + cid +
                '}';
    }
}