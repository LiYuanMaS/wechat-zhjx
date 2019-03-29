package com.example.springbootdemo.utils;

import com.example.springbootdemo.dao.CommonUserMapper;
import com.example.springbootdemo.pojo.UserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 用户信息公共类
 */
@Service
public class CommonUser {
    @Autowired
    private CommonUserMapper findUserMess;


    public UserDetail findUser(String openId) {
        return findUserMess.findUser(openId);
    }
}