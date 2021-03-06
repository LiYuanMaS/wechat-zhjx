package com.example.springbootdemo.controller;


import com.example.springbootdemo.common.CommonUser;
import com.example.springbootdemo.common.wechatcommon.CommonWechatUser;
import com.example.springbootdemo.pojo.UserDetail;
import com.example.springbootdemo.pojo.WeixinUser;
import com.example.springbootdemo.service.UserDetailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 注册个人信息controller
 *
 */
@Controller
@RequestMapping("/api/user")
public class UserDetailAction
{
    private Logger log = LoggerFactory.getLogger(UserDetailAction.class);

    private final static String WECHATID = "wechatId";
    private final static String IMAGEURL = "wxImgUrl";
    private final static String WXUSERNAME = "wxUserName";
    @Autowired
    private UserDetailService userDetailService;

    @Autowired
    private CommonUser commonUser;

    @Autowired
    private CommonWechatUser commonWechatUser;
    /**
     * 进入个人中心首页
     */
    @RequestMapping("/usercenter")
    public void usercenter(HttpServletRequest request,HttpServletResponse response) throws IOException {
        log.info("进入个人中心首页---------------");
        WeixinUser weixinUser = commonWechatUser.getTheCode(request.getParameter("code"));
        log.info("wechatId-------:{}",weixinUser.getOpenId());
        log.info("授权成功，准备回调页面*****************");
        response.sendRedirect("http://h5.zhjxw.cn/#/user/index?headPath=" + weixinUser.getHeadImgUrl() + "&username=" + weixinUser.getNickname() + "&wechatId=" + weixinUser.getOpenId());
    }
    /**
     * 获取微信头像姓名
     */
    @RequestMapping("/pushImage")
    @ResponseBody
    public Map<String,Object> pushImage(HttpServletRequest req,@RequestBody Map<String,Object> makeOnCard){
        Map<String,Object> map = new HashMap<>();
        String wechatId = makeOnCard.get("wechatId").toString();
        req.getSession().setAttribute(WECHATID,wechatId);
        return map;
    }


    /**
     * 注册个人信息逻辑
     * @return
     */
    @RequestMapping(value={"/adduser"})
    @ResponseBody
    public Map<String,Object> getAllUsers(HttpServletRequest req,@RequestBody Map<String,Object> makeOnCard){
        Map<String,Object> map = new HashMap<>();
        Object code = req.getSession().getAttribute(WECHATID).toString();
        if(StringUtils.isEmpty(code.toString())){
            map.put("resCode","neterror");
            return map;
        }
        makeOnCard.put("wechatid",code);
        Map<String,Object> result = userDetailService.insertUser(makeOnCard);

        return result;
    }

    /**
     * 查询个人信息逻辑
     * @return
     */
    @RequestMapping(value={"/finduser"})
    @ResponseBody
    public Map<String,Object> finduser(HttpServletRequest request,Map<String,Object> reqMap){
        String wechatId = request.getSession().getAttribute(WECHATID).toString();
        log.info("查询用户信息wechatid----:{}",wechatId);
//        String wechatId = "1242141";
        Map<String,Object> result = new HashMap<>();
        if(StringUtils.isEmpty(wechatId)){
            result.put("rspCode","neterror");
            result.put("rspMsg","非法请求!");
            return result;
        }

        UserDetail user = commonUser.findUser(wechatId);
        if(StringUtils.isEmpty(user)){
            result.put("rspCode","noBind");
            result.put("rspMsg","未找到用户信息");
            result.put("rspData",user);
            return result;
        }
        result.put("rspCode","000");
        result.put("rspMsg","用户信息");
        result.put("rspData",user);

        return result;
    }

}
