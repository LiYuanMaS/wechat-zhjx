package com.example.springbootdemo.dao;


import com.example.springbootdemo.pojo.UserDetail;

public interface CommonUserMapper {
    /**
     * 获取用户信息
     * @param openid
     * @return
     */
    UserDetail findUser(String openid);


}
