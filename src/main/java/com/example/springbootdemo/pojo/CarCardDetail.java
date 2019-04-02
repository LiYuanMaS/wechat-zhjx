package com.example.springbootdemo.pojo;

import java.util.Date;

public class CarCardDetail {
    /**
     *省份
     */
    private String region;

    /**
     *
     * 市
     */
    private String province;

    /**
     *区/县
     */
    private String city;

    /**
     *创建时间
     */
    private Date createTime;

    /**
     *预约状态
     */
    private String yuyType;

    /**
     *车牌号
     */
    private String cardNo;

    /**
     *上牌时间
     */
    private Date onCardTime;

    /**
     *微信id
     */
    private String wechatId;

    /**
     *预约用户姓名
     */
    private String yuyUsername;

    public void setRegion(String region) {
        this.region = region;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setYuyType(String yuyType) {
        this.yuyType = yuyType;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public void setOnCardTime(Date onCardTime) {
        this.onCardTime = onCardTime;
    }

    public void setWechatId(String wechatId) {
        this.wechatId = wechatId;
    }

    public void setYuyUsername(String yuyUsername) {
        this.yuyUsername = yuyUsername;
    }

    public String getRegion() {
        return region;
    }

    public String getProvince() {
        return province;
    }

    public String getCity() {
        return city;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public String getYuyType() {
        return yuyType;
    }

    public String getCardNo() {
        return cardNo;
    }

    public Date getOnCardTime() {
        return onCardTime;
    }

    public String getWechatId() {
        return wechatId;
    }

    public String getYuyUsername() {
        return yuyUsername;
    }
}