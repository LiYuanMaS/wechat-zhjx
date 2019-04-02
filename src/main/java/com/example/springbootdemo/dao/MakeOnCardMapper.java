package com.example.springbootdemo.dao;

import java.util.Map;

public interface MakeOnCardMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table make_on_card
     *
     * 在线预约入库
     */
    int insert(Map<String,Object> record);

    /**
     * 查询预约信息
     */
    String findAll(Map<String,Object> record);



}