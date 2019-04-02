package com.example.springbootdemo.service;


import com.example.springbootdemo.dao.CarCardDetailMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private Logger log = LoggerFactory.getLogger(CarCardDetailService.class);

    @Autowired
    private CarCardDetailMapper carCardDetailMapper;

    public List<String> findCardDetail(Map<String,Object> map){
        List<String> list = carCardDetailMapper.findCardDetail(map);
        return list;
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
