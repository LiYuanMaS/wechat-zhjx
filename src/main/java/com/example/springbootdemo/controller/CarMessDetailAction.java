package com.example.springbootdemo.controller;


import com.example.springbootdemo.service.CarMessDetailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;


import java.util.HashMap;
import java.util.Map;

/**
 * 车牌信息controller
 */
@RestController
@RequestMapping(value = {"/cardMess"})
public class CarMessDetailAction
{
    private final static String WECHATID = "wechatId";
    @Autowired
    private CarMessDetailService carMessDetailService;

    /**
     * 查询车牌信息
     * @return
     */
    @RequestMapping(value={"/findCar"})
    public Map<String,Object> getAllUsers(HttpServletRequest req){
        Map<String,Object> map = new HashMap<>();
       // String wechatid = req.getSession().getAttribute(WECHATID).toString();
        String wechatid = "test123456";
        if(StringUtils.isEmpty(wechatid)){
            map.put("rspCode","neterror");
            map.put("rspMes","非法请求！");
            return map;
        }

        return carMessDetailService.listAll(wechatid);
    }

//    /**
//     * 新增车牌信息
//     * 注：暂无用功能
//     * @return
//     */
//    @RequestMapping(value={"/insert"})
//    public Map<String,Object> insertMess(HttpSession session, @RequestBody Map<String, Object> makeOnCard){
//
//        return carMessDetailService.insertMess(makeOnCard);
//    }
}
