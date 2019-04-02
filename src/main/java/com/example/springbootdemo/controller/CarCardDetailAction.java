package com.example.springbootdemo.controller;

import com.example.springbootdemo.pojo.UserDetail;
import com.example.springbootdemo.pojo.WeixinUser;
import com.example.springbootdemo.service.CarCardDetailService;
import com.example.springbootdemo.utils.CommonUser;
import com.example.springbootdemo.utils.CommonWechatUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 自助选号处理controller
 */
@Controller
@RequestMapping(value = {"/help"})
public class CarCardDetailAction
{
    private Logger log = LoggerFactory.getLogger(CarCardDetailAction.class);

    private final static String WECHATID = "wechatId";
    @Autowired
    private CarCardDetailService carCardDetailService;

    @Autowired
    private CommonUser commonUser;

    @Autowired
    private CommonWechatUser commonWechatUser;
    /**
     * 进入自助选号首页
     */
    @RequestMapping(value={"/choosecar"})
    public String choosecar(HttpServletRequest request){
        HttpSession session = request.getSession();
        WeixinUser weixinUser = commonWechatUser.getTheCode(request.getParameter("code"));
        if(StringUtils.isEmpty(weixinUser)){
            return "neterror";
        }
        session.setAttribute(WECHATID,weixinUser.getOpenId());
        return "redirect:";
    }

    /**
     * 获取车牌列表
     * @return
     */
    @RequestMapping(value={"/getCard"})
    @ResponseBody
    public Map<String,Object> getCardList(HttpServletRequest req,@RequestBody Map<String,Object> reqMap){
        Map<String,Object> map = new HashMap<>();
        String wechatId = req.getSession().getAttribute(WECHATID).toString();
        //String wechatId = "testadb";
        log.info("wechatId--------:{}",wechatId);
        if(StringUtils.isEmpty(wechatId)){
            map.put("rspCode","neterror");
            map.put("rspMsg","非法请求");
            return map;
        }

        List<String> list = carCardDetailService.findCardDetail(reqMap);
        if(StringUtils.isEmpty(list) || list.size()<0){
            map.put("rspCode","999");
            map.put("rspMsg","该地区车牌为空！");
            map.put("wechatId",wechatId);
        }else{
            map.put("rspCode","000");
            map.put("rspMsg","查询成功");
            map.put("wechatId",wechatId);
        }
        map.put("rspData",list);
        req.getSession().setAttribute(WECHATID,wechatId);
        return map;
    }

    /**
     * 判注册
     *
     * @return
     */
    @RequestMapping(value={"/getCardMess"})
    @ResponseBody
    public Map<String,Object> getCardMes(HttpServletRequest req,@RequestBody Map<String,Object> reqMap){
        Map<String,Object> map = new HashMap<>();
        String openId = req.getSession().getAttribute(WECHATID).toString();
        log.info("openId--------:{}",openId);
        if(StringUtils.isEmpty(openId)){
            map.put("rspCode","neterror");
            map.put("rspMsg","非法请求！");
            return map;
        }

        UserDetail user = commonUser.findUser(openId);
        if(StringUtils.isEmpty(user)){
            map.put("rspCode","noBind");
            map.put("rspMsg","未注册");
            return map;
        }
        map.put("rspCode","000");
        map.put("rspMsg","成功");

        return map;
    }

    /**
     * 新增预约车牌信息
     * @return
     */
    @RequestMapping(value={"/addCarInfo"})
    @ResponseBody
    public Map<String,Object> getAllUsers(HttpServletRequest req,@RequestBody Map<String,Object> makeOnCard) {
        makeOnCard.put("wechatId",req.getSession().getAttribute(WECHATID));
        return carCardDetailService.updateCardMess(makeOnCard);
    }

}
