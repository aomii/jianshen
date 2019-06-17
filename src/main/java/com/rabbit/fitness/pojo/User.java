package com.rabbit.fitness.pojo;

public class User {
    private  String address;
    private  String lastaddress;
    private Integer userId;

    private String userName;

    private String password;

    private String salt;

    private Byte userType;

    private Role role;

    private Integer roleId;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    private String userDescription;

    public User(String userName, String upwd2) {
        this.userName = userName;
        this.password = upwd2;
    }


    public User() {

    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Byte getUserType() {
        return userType;
    }

    public void setUserType(Byte userType) {
        this.userType = userType;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getUserDescription() {
        return userDescription;
    }

    public void setUserDescription(String userDescription) {
        this.userDescription = userDescription;
    }

    public User(String userName, String password, Integer roleId) {
        this.roleId = roleId;
        this.userName = userName;
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLastaddress() {
        return lastaddress;
    }

    public void setLastaddress(String lastaddress) {
        this.lastaddress = lastaddress;
    }

    @Override
    public String toString() {
        return "User{" +
                "address='" + address + '\'' +
                ", lastaddress='" + lastaddress + '\'' +
                ", userId=" + userId +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", salt='" + salt + '\'' +
                ", userType=" + userType +
                ", role=" + role +
                ", roleId=" + roleId +
                ", userDescription='" + userDescription + '\'' +
                '}';
    }
}