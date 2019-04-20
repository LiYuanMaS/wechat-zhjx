package com.example.springbootdemo.common.wechatcommon;

import com.example.springbootdemo.pojo.WeixinUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author scw
 * @create 2018-01-18 17:47
 * @desc 获取微信用户的所有信息，这个主要是为了不要用户自己填写个人信息
 */
@Service
public class CommonWechatUser {
    private Logger log = LoggerFactory.getLogger(CommonWechatUser.class);

    @Autowired
    private WeiXinUserInfoService userService;

    @Autowired
    private WeiXinService weiXinService;
    public WeixinUser getTheCode(String code) {

        Map<String, String> authInfo = new HashMap<>();
        String openId = "";
        if (!StringUtils.isEmpty(code)) {
            // 调用根据用户的code得到需要的授权信息
            authInfo = userService.getAuthInfo(code);

            //获取到openId
            openId = authInfo.get("Openid");
        }else{
            log.info("微信授权code为null!!!!!!!!!!!!!!!!!!!!!");
        }
        // 获取基础刷新的接口访问凭证（目前还没明白为什么用authInfo.get("AccessToken");这里面的access_token就不行）
        String accessToken = weiXinService.getAccessToken().getToken();
        //获取到微信用户的信息
        WeixinUser userinfo = userService.getUserInfo(accessToken, openId);

        return userinfo;
    }

}
