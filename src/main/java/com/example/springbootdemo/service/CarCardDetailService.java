package com.example.springbootdemo.service;


import com.example.springbootdemo.dao.CarCardDetailMapper;
import com.example.springbootdemo.dao.MakeOnCardMapper;
import com.example.springbootdemo.pojo.CarCardDetail;
import com.example.springbootdemo.pojo.CarMessDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 自主选牌业务逻辑
 */
@Service
public class CarCardDetailService {
    @Autowired
    private CarCardDetailMapper carCardDetailMapper;

    public List<String> findCardDetail(String wechatId){
        List<String> list = carCardDetailMapper.findCardDetail(wechatId);
        return list;
    }

    public List<CarMessDetail> findMessDetail(String wechatId){
        List<CarMessDetail> list = carCardDetailMapper.findCardMess(wechatId);
        return list;
    }

    public Integer cardIsNo(String wechatId){
        int result = carCardDetailMapper.cardIsNo(wechatId);
        return result;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Map<String,Object> updateCardMess(Map<String,Object> map){
        Map<String,Object> resultMap = new HashMap<>();
        try {
            int insercard =  carCardDetailMapper.insertCardMess(map);
            int cardresult = carCardDetailMapper.updateCard(map);
          //  int messresult = carCardDetailMapper.updateCardMess(map);

            if(insercard>0 && cardresult>0 ){
                resultMap.put("rspCode","000");
                resultMap.put("rspMsg","预约成功");
            }else{
                throw new SQLException("预约异常");
            }
        }catch (Exception e){
            e.printStackTrace();
            resultMap.put("rspCode","999");
            resultMap.put("rspMsg","预约失败，请重新预约");
        }

        return resultMap;
    }


}
