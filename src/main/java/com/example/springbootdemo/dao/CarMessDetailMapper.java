package com.example.springbootdemo.dao;

import com.example.springbootdemo.pojo.CarMessDetail;

import java.util.List;

public interface CarMessDetailMapper {


    /**
     * 查询个人车牌信息
     */
    List<CarMessDetail> findAll(String wechatid);



}