package com.example.springbootdemo.controller;


import com.example.springbootdemo.service.MakeOnCardService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

import java.util.Map;

/**
 * 在线预约controller
 */
@RestController
@RequestMapping("/api/real")
public class MakeOnCardAction
{
    private Logger log = LoggerFactory.getLogger(MakeOnCardAction.class);
    @Autowired
    private MakeOnCardService makeOnCardService;

    /**
     * 在线预约逻辑
     * @return
     */
    @RequestMapping("/oncard")
    public Map<String,Object> getAllUsers(@RequestBody Map<String,Object> makeOnCard){
        Map<String,Object> map = new HashMap<String,Object>();
        String list = makeOnCardService.listAll(makeOnCard);
        if(!StringUtils.isEmpty(list)){
            map.put("rspCode","999");
            map.put("rspMsg","您的手机号已预约，请勿重复预约。");
            return map;
        }

        if(StringUtils.isEmpty(makeOnCard.get("phone"))){
            map.put("rspCode","999");
            map.put("rspMsg","请填写正确的手机号！");
            return map;
        }
        Map<String,Object> rspCode = makeOnCardService.findAllUser(makeOnCard);

        return rspCode;
    }


}
