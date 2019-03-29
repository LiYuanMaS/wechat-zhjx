package com.example.springbootdemo.controller;


import com.example.springbootdemo.service.MakeOnCardService;

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
@RequestMapping(value = {"/real"})
public class MakeOnCardAction
{
    @Autowired
    private MakeOnCardService makeOnCardService;

    /**
     * 在线预约逻辑
     * @return
     */
    @RequestMapping(value={"/oncard"})
    public Map<String,Object> getAllUsers(@RequestBody Map<String,Object> makeOnCard){
        Map<String,Object> map = new HashMap<>();
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
