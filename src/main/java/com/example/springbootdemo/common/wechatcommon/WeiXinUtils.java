package com.example.springbootdemo.common.wechatcommon;


import com.alibaba.fastjson.JSONObject;
import com.example.springbootdemo.constant.CommonConst;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @author scw
 * @create 2018-01-17 14:13
 * @desc 用户获取access_token,众号调用各接口时都需使用access_token
 **/
public class WeiXinUtils {
    private Logger log = LoggerFactory.getLogger(WeiXinUtils.class);
    /**
     * Get请求，方便到一个url接口来获取结果
     *
     * @param url
     * @return
     */
    public static JSONObject doGetStr(String url) {
        CloseableHttpClient defaultHttpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        JSONObject jsonObject = null;
        try {
            HttpResponse response = defaultHttpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                String result = EntityUtils.toString(entity, "UTF-8");
                jsonObject = JSONObject.parseObject(result);
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonObject;

    }



    /**
     * 获取access_token
     * @return
     */
    public static AccessToken getAccessToken() {
        AccessToken accessToken = new AccessToken();
        String url = CommonConst.ACCESS_TOKEN_URL.replace("APPID", "wx56026fd2f599d104").replace("APPSECRET", "18aa2b002251df97f0637719488a3000");
        JSONObject jsonObject = doGetStr(url);
        if (jsonObject != null) {
            accessToken.setToken(jsonObject.getString("access_token"));
            accessToken.setExpireIn(jsonObject.getInteger("expires_in"));
        }
        return accessToken;

    }
}