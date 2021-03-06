package com.rabbit.fitness.pojo;

public class Coach {
    private Integer cid;

    private String cname;

    private String cgender;

    private String cpic;

    private Integer signid;

    private Double cmoney;

    private Integer gid;

    private String type;

    private String contact;

    private String isauth;

    private String freetime;

    private String freeday;

    private Integer uid;

    private String friendids;

    private String secret;

    private String logaddress;

    private Integer cage;

    private String liupai;

    private String jieke;

    private Double cprice;

    private String cnickname;

    /*后台*/
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getCgender() {
        return cgender;
    }

    public void setCgender(String cgender) {
        this.cgender = cgender;
    }

    public String getCpic() {
        return cpic;
    }

    public void setCpic(String cpic) {
        this.cpic = cpic;
    }

    public Integer getSignid() {
        return signid;
    }

    public void setSignid(Integer signid) {
        this.signid = signid;
    }

    public Double getCmoney() {
        return cmoney;
    }

    public void setCmoney(Double cmoney) {
        this.cmoney = cmoney;
    }

    public Integer getGid() {
        return gid;
    }

    public void setGid(Integer gid) {
        this.gid = gid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getIsauth() {
        return isauth;
    }

    public void setIsauth(String isauth) {
        this.isauth = isauth;
    }

    public String getFreetime() {
        return freetime;
    }

    public void setFreetime(String freetime) {
        this.freetime = freetime;
    }

    public String getFreeday() {
        return freeday;
    }

    public void setFreeday(String freeday) {
        this.freeday = freeday;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getFriendids() {
        return friendids;
    }

    public void setFriendids(String friendids) {
        this.friendids = friendids;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getLogaddress() {
        return logaddress;
    }

    public void setLogaddress(String logaddress) {
        this.logaddress = logaddress;
    }

    public Integer getCage() {
        return cage;
    }

    public void setCage(Integer cage) {
        this.cage = cage;
    }

    public String getLiupai() {
        return liupai;
    }

    public void setLiupai(String liupai) {
        this.liupai = liupai;
    }

    public String getJieke() {
        return jieke;
    }

    public void setJieke(String jieke) {
        this.jieke = jieke;
    }

    public Double getCprice() {
        return cprice;
    }

    public void setCprice(Double cprice) {
        this.cprice = cprice;
    }

    public String getCnickname() {
        return cnickname;
    }

    public void setCnickname(String cnickname) {
        this.cnickname = cnickname;
    }

    public Coach(String cname, String cgender, String cpic, double cmoney, String type, String contact, String isauth, String freetime, String freeday, Integer uid, String secret, Integer cage, String liupai, String jieke, double cprice, String cnickname) {
        this.cname = cname;
        this.cgender = cgender;
        this.cpic = cpic;
        this.cmoney = cmoney;
        this.type = type;
        this.contact = contact;
        this.isauth = isauth;
        this.freetime = freetime;
        this.freeday = freeday;
        this.uid = uid;
        this.secret = secret;
        this.cage = cage;
        this.liupai = liupai;
        this.jieke = jieke;
        this.cprice = cprice;
        this.cnickname = cnickname;
    }

    public Coach(Integer cid, String cname, String cgender, String cpic, Integer signid, Double cmoney, Integer gid, String type, String contact, String isauth, String freetime, String freeday, Integer uid, String friendids, String secret, String logaddress, Integer cage, String liupai, String jieke, Double cprice, String cnickname) {
        this.cid = cid;
        this.cname = cname;
        this.cgender = cgender;
        this.cpic = cpic;
        this.signid = signid;
        this.cmoney = cmoney;
        this.gid = gid;
        this.type = type;
        this.contact = contact;
        this.isauth = isauth;
        this.freetime = freetime;
        this.freeday = freeday;
        this.uid = uid;
        this.friendids = friendids;
        this.secret = secret;
        this.logaddress = logaddress;
        this.cage = cage;
        this.liupai = liupai;
        this.jieke = jieke;
        this.cprice = cprice;
        this.cnickname = cnickname;
    }

    public Coach() {
    }


    @Override
    public String toString() {
        return "Coach{" +
                "cid=" + cid +
                ", cname='" + cname + '\'' +
                ", cgender='" + cgender + '\'' +
                ", cpic='" + cpic + '\'' +
                ", signid=" + signid +
                ", cmoney=" + cmoney +
                ", gid=" + gid +
                ", type='" + type + '\'' +
                ", contact='" + contact + '\'' +
                ", isauth='" + isauth + '\'' +
                ", freetime='" + freetime + '\'' +
                ", freeday='" + freeday + '\'' +
                ", uid=" + uid +
                ", friendids='" + friendids + '\'' +
                ", secret='" + secret + '\'' +
                ", logaddress='" + logaddress + '\'' +
                ", cage=" + cage +
                ", liupai='" + liupai + '\'' +
                ", jieke='" + jieke + '\'' +
                ", cprice=" + cprice +
                ", cnickname='" + cnickname + '\'' +
                '}';
    }
}