package cm.model;

import cm.exception.BussinessException;
import cm.util.RequestUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2015/7/22.
 */
public class JsApiTicket {
    private String JS_API_TICKET = null;
    private Long JS_API_TICKET_CREATE_TIM = null;
    private Integer JS_API_TICKET_EXPIRES_IN = null;


    public JsApiTicket(HttpServletRequest request, String access_token) throws BussinessException {
        String url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=" + access_token + "&type=jsapi";
        String res = RequestUtils.httpRequest(url);
        JSONObject obj = JSON.parseObject(res);
        if (obj.getInteger("errcode") != 0) {
            System.out.println("获取js api ticket失败:" + obj.getString("errmsg"));
            throw new BussinessException(obj);
        } else {
            this.JS_API_TICKET = (String) obj.get("ticket");
            this.JS_API_TICKET_EXPIRES_IN = (Integer) obj.get("expires_in");
            this.JS_API_TICKET_CREATE_TIM = System.currentTimeMillis();
        }
    }

    public void updateJsApiTicket(String access_token) throws BussinessException {
        String url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=" + access_token + "&type=jsapi";
        String res = RequestUtils.httpRequest(url);
        JSONObject obj = JSON.parseObject(res);
        if (obj.getInteger("errcode") != 0) {
            System.out.println("获取js api ticket失败:" + obj.getString("errmsg"));
            throw new BussinessException(obj);
        } else {
            this.JS_API_TICKET = (String) obj.get("ticket");
            this.JS_API_TICKET_EXPIRES_IN = (Integer) obj.get("expires_in");
            this.JS_API_TICKET_CREATE_TIM = System.currentTimeMillis();
        }
    }

    public long restTime() {
        return JS_API_TICKET_EXPIRES_IN - ((System.currentTimeMillis() - JS_API_TICKET_CREATE_TIM) / 1000);
    }

    public void clear() {
        this.JS_API_TICKET = null;
        this.JS_API_TICKET_EXPIRES_IN = null;
        this.JS_API_TICKET_CREATE_TIM = null;
    }

    public String getJS_API_TICKET() {
        return JS_API_TICKET;
    }

    public void setJS_API_TICKET(String JS_API_TICKET) {
        this.JS_API_TICKET = JS_API_TICKET;
    }

    public Long getJS_API_TICKET_CREATE_TIM() {
        return JS_API_TICKET_CREATE_TIM;
    }

    public void setJS_API_TICKET_CREATE_TIM(Long JS_API_TICKET_CREATE_TIM) {
        this.JS_API_TICKET_CREATE_TIM = JS_API_TICKET_CREATE_TIM;
    }

    public Integer getJS_API_TICKET_EXPIRES_IN() {
        return JS_API_TICKET_EXPIRES_IN;
    }

    public void setJS_API_TICKET_EXPIRES_IN(Integer JS_API_TICKET_EXPIRES_IN) {
        this.JS_API_TICKET_EXPIRES_IN = JS_API_TICKET_EXPIRES_IN;
    }


}
