package com.example.springbootdemo.service;


import com.example.springbootdemo.dao.CarMessDetailMapper;
import com.example.springbootdemo.pojo.CarMessDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 个人车牌信息业务逻辑
 */
@Service
public class CarMessDetailService {
    private Logger log = LoggerFactory.getLogger(CarMessDetailService.class);

    @Autowired
    private CarMessDetailMapper carMessDetailMapper;

    public Map<String,Object> listAll(String wechatid){
        Map<String,Object> resultMap = new HashMap<>();
        List<CarMessDetail> list = carMessDetailMapper.findAll(wechatid);
        if(StringUtils.isEmpty(list) || list.size()<=0){
            resultMap.put("rspCode","999");
            resultMap.put("rspMsg","您还未选过牌，快去选择自己心仪的车牌吧！");
            resultMap.put("rspData",list);
        }else{
            resultMap.put("rspCode","000");
            resultMap.put("rspMsg","查询成功");
            resultMap.put("rspData",list);
        }

        return resultMap;
    }




}
