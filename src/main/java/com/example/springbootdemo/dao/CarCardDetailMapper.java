package com.example.springbootdemo.dao;

import java.util.List;
import java.util.Map;

public interface CarCardDetailMapper {
    /**
     * 获取未预约车牌列表
     */
    List<String> findCardDetail(Map<String,Object> map);

    /**
     * 添加车牌信息
     */
    int insertCardMess(Map<String,Object> map);

    /**
     * 修改车牌状态
     */
    int updateCard(Map<String,Object> map);

}