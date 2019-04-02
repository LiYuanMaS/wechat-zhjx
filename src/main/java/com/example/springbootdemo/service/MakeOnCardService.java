package com.example.springbootdemo.service;


import com.example.springbootdemo.dao.MakeOnCardMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 在线预约业务逻辑
 */
@Service
public class MakeOnCardService{
    private Logger log = LoggerFactory.getLogger(MakeOnCardService.class);

    @Autowired
    private MakeOnCardMapper makeOnCardMapper;

    public String listAll(Map<String,Object> make){
        String list = makeOnCardMapper.findAll(make);

        return list;
    }

    public Map<String,Object> findAllUser(Map<String,Object> make) {
        Map<String,Object> map = new HashMap<>();
        try {
            makeOnCardMapper.insert(make);
        }catch (Exception e) {
            map.put("rspCode","999");
            map.put("rspMsg","预约失败，请重新进入预约！");
            return map;
        }
        map.put("rspCode","000");
        map.put("rspMsg","预约成功，客服会在24小时内电话联系您，请保持电话畅通 谢谢！");
        return map;

    }


}
