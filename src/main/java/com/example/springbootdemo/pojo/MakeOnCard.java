package com.example.springbootdemo.pojo;

public class MakeOnCard {
    /**
     *用户姓名
     */
    private String userName;

    /**
     *手机号
     */
    private String phone;

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUserName() {
        return userName;
    }

    public String getPhone() {
        return phone;
    }
}