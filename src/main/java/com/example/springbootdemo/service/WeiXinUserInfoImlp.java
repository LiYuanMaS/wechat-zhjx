package com.example.springbootdemo.service;

import com.alibaba.fastjson.JSONObject;
import com.example.springbootdemo.common.wechatcommon.WeiXinUserInfoService;
import com.example.springbootdemo.pojo.WeixinUser;
import com.example.springbootdemo.constant.CommonConst;
import com.example.springbootdemo.common.wechatcommon.WeiXinService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author scw
 * @create 2018-01-18 17:51
 * @desc 用于获取微信用户的信息
 **/
@Service
public class WeiXinUserInfoImlp implements WeiXinUserInfoService {
    private Logger log = LoggerFactory.getLogger(WeiXinUserInfoService.class);

    @Autowired
    private WeiXinService weiXinService;
    /**
     * 获取微信用户的信息
     *
     * @param accessToken
     * @param openId
     * @return
     */
    @Override
    public WeixinUser getUserInfo(String accessToken, String openId) {
        WeixinUser weixinUserInfo = null;
        // 拼接获取用户信息接口的请求地址
        String requestUrl = CommonConst.GET_WEIXIN_USER_URL.replace("ACCESS_TOKEN", accessToken).replace(
                "OPENID", openId);
        // 获取用户信息(返回的是Json格式内容)
        JSONObject jsonObject = weiXinService.doGetStr(requestUrl);

        if (null != jsonObject) {
            try {
                //封装获取到的用户信息
                weixinUserInfo = new WeixinUser();
                // 用户的标识
                weixinUserInfo.setOpenId(jsonObject.getString("openid"));
                // 昵称
                weixinUserInfo.setNickname(jsonObject.getString("nickname"));
                // 用户的性别（1是男性，2是女性，0是未知）
                weixinUserInfo.setSex(jsonObject.getIntValue("sex"));
                // 用户所在国家
                weixinUserInfo.setCountry(jsonObject.getString("country"));
                // 用户所在省份
                weixinUserInfo.setProvince(jsonObject.getString("province"));
                // 用户所在城市
                weixinUserInfo.setCity(jsonObject.getString("city"));
                // 用户头像
                weixinUserInfo.setHeadImgUrl(jsonObject.getString("headimgurl"));
            } catch (Exception e) {
                if (0 == weixinUserInfo.getSubscribe()) {
                    System.out.println("用户并没有关注本公众号");
                } else {
                    int errorCode = jsonObject.getIntValue("errcode");
                    String errorMsg = jsonObject.getString("errmsg");
                    System.out.println("由于" + errorCode + "错误码；错误信息为：" + errorMsg + "；导致获取用户信息失败");
                }
            }
        }
        return weixinUserInfo;
    }

    /**
     * 进行用户授权，获取到需要的授权字段，比如openId
     *
     * @param code 识别得到用户id必须的一个值
     *             得到网页授权凭证和用户id
     * @return
     */
    @Override
    public Map<String, String> oauth2GetOpenid(String code) {
        //自己的配置appid（公众号进行查阅）
        String appid = "wx56026fd2f599d104";
        //自己的配置APPSECRET;（公众号进行查阅）
        String appsecret = "18aa2b002251df97f0637719488a3000";
        //拼接用户授权接口信息
        String requestUrl = CommonConst.GET_WEBAUTH_URL.replace("APPID", appid).replace("SECRET", appsecret).replace("CODE", code);
        //存储获取到的授权字段信息
        Map<String, String> result = new HashMap<String, String>();
        try {
            JSONObject OpenidJSONO = weiXinService.doGetStr(requestUrl);
            //OpenidJSONO可以得到的内容：access_token expires_in  refresh_token openid scope
            String Openid = String.valueOf(OpenidJSONO.get("openid"));
            String AccessToken = String.valueOf(OpenidJSONO.get("access_token"));
            //用户保存的作用域
            String Scope = String.valueOf(OpenidJSONO.get("scope"));
            String refresh_token = String.valueOf(OpenidJSONO.get("refresh_token"));
            result.put("Openid", Openid);
            result.put("AccessToken", AccessToken);
            result.put("scope", Scope);
            result.put("refresh_token", refresh_token);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 获取到微信用户的唯一的OpendID
     *
     * @param code 这是要获取OpendId的必须的一个参数
     * @return
     */
    @Override
    public Map<String, String> getAuthInfo(String code) {
        //进行授权验证，获取到OpenID字段等信息
        Map<String, String> result = oauth2GetOpenid(code);
        // 从这里可以得到用户openid
        String openId = result.get("Openid");

        return result;
    }
}
