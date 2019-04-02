package com.example.springbootdemo.service;

import com.example.springbootdemo.dao.UserDetailMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 用户信息注册查询业务逻辑
 */
@Service
public class UserDetailService {
    private Logger log = LoggerFactory.getLogger(UserDetailService.class);

    @Autowired
    private UserDetailMapper userDetailMapper;


    public Map<String,Object> insertUser(Map<String,Object> make) {
        Map<String,Object> map = new HashMap<>();
        if(make.get("phone").equals("")){
            map.put("rspCode","999");
            map.put("rspMsg","请填写正确手机号！");
            return map;
        }

        try {
            userDetailMapper.insertUser(make);
        }catch (Exception e) {
            e.printStackTrace();
            map.put("rspCode","999");
            map.put("rspMsg","注册失败，请重新进入预约！");
            return map;
        }
        map.put("rspCode","000");
        map.put("rspMsg","注册成功");
        return map;

    }


}
