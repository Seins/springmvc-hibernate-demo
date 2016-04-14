package cm.model;

import cm.exception.BussinessException;
import cm.util.RequestUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by CM on 2015/7/22.
 */


public class Token {
    private String ACCESS_TOKEN = null;
    private Long TOKEN_CREATE_TIME = null;
    private Integer TOKEN_EXPIRES_IN = null;

    public String getACCESS_TOKEN() {
        return ACCESS_TOKEN;
    }

    public void setACCESS_TOKEN(String ACCESS_TOKEN) {
        this.ACCESS_TOKEN = ACCESS_TOKEN;
    }

    public Long getTOKEN_CREATE_TIME() {
        return TOKEN_CREATE_TIME;
    }

    public void setTOKEN_CREATE_TIME(Long TOKEN_CREATE_TIME) {
        this.TOKEN_CREATE_TIME = TOKEN_CREATE_TIME;
    }

    public Integer getTOKEN_EXPIRES_IN() {
        return TOKEN_EXPIRES_IN;
    }

    public void setTOKEN_EXPIRES_IN(Integer TOKEN_EXPIRES_IN) {
        this.TOKEN_EXPIRES_IN = TOKEN_EXPIRES_IN;
    }


    public Token(String appId, String appSecret, String grantType, HttpServletRequest request) throws BussinessException {
        String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=" + grantType + "&appid=" + appId + "&secret=" + appSecret;
        String msg = RequestUtils.httpRequest(url, grantType, appId, appSecret);
        JSONObject obj = JSON.parseObject(msg);
        if (obj.containsKey("errmsg")) {
            System.out.println("获取token失败");
            throw new BussinessException(obj);
        } else {
            this.ACCESS_TOKEN = obj.getString("access_token");
            this.TOKEN_CREATE_TIME = System.currentTimeMillis();
            this.TOKEN_EXPIRES_IN = obj.getInteger("expires_in");
        }
    }


    public void updateToken(String appId, String appSecret, String grantType, HttpServletRequest request) throws BussinessException {
        String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=" + grantType + "&appid=" + appId + "&secret=" + appSecret;
        String msg = RequestUtils.httpRequest(url, grantType, appId, appSecret);
        JSONObject obj = JSON.parseObject(msg);
        if (obj.containsKey("errmsg")) {
            System.out.println("获取token失败");
            throw new BussinessException(obj);
        } else {
            this.ACCESS_TOKEN = obj.getString("access_token");
            this.TOKEN_CREATE_TIME = System.currentTimeMillis();
            this.TOKEN_EXPIRES_IN = obj.getInteger("expires_in");
        }
    }


    public long restTime() {
        return this.TOKEN_EXPIRES_IN - ((System.currentTimeMillis() - this.TOKEN_CREATE_TIME) / 1000);
    }


    public void clear() {
        this.ACCESS_TOKEN = null;
        this.TOKEN_CREATE_TIME = null;
        this.TOKEN_EXPIRES_IN = null;
    }
}

