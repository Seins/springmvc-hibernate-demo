package cm.util;

import cm.entity.PublicNumber;
import cm.entity.WxUser;
import cm.exception.BussinessException;
import cm.model.*;
import cm.model.user.mgr.Group;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tencent.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.security.auth.login.LoginException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by CM on 2015/4/22.
 */
public class WechatUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(WechatUtils.class);

    public static Map<String, Token> tokenQueue = new HashMap<String, Token>();
    public static Map<String, JsApiTicket> ticketQueue = new HashMap<String, JsApiTicket>();

    /**
     * 应用授权作用域，
     * snsapi_base （不弹出授权页面，直接跳转，只能获取用户openid），
     * snsapi_userinfo （弹出授权页面，可通过openid拿到昵称、性别、所在地。并且，即使在未关注的情况下，只要用户授权，也能获取其信息）
     */
    private static final String SCOPE_BASE = "snsapi_base";
    private static final String SCOPE_USER_INFO = "snsapi_userinfo";
    private static final String RESPONSE_TYPE = "code";

    //授权回调后会带上此参数
    private static final String STATE = "authorizated";
    /**
     * 微信授权网址
     */
    private static final String OAUTH_URL = "https://open.weixin.qq.com/connect/oauth2/authorize";


    /**
     * 网页授权标识 只能使用一次 声明周期是五分钟，超过即过期
     *
     * @param code
     * @param appId
     * @param appSecret
     * @return
     */
    public static AccessTokenModel getAccessToken(String code, String appId, String appSecret) throws Exception {
        String toUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + appId + "&secret=" + appSecret + "&code=" + code + "&grant_type=authorization_code";
        String msg = RequestUtils.httpRequest(toUrl, code, appId, appSecret);
        AccessTokenModel token = JSON.parseObject(msg, AccessTokenModel.class);
        if (!CommonUtils.isEmpty(token.getErrcode())) {
            throw new BussinessException(new WXErrorMsgModel(token.getErrmsg(), Integer.parseInt(token.getErrcode())));
        }
        LOGGER.info("get access token success!");
        return token;
    }

    /**
     * 网页授权获取用户信息
     *
     * @param accessToken
     * @param openId
     * @param lang
     * @return
     */
    public static WxUser getUserInformationFromPage(String accessToken, String openId, String lang) throws Exception {
        String url = "https://api.weixin.qq.com/sns/userinfo?access_token=" + accessToken + "&openid=" + openId + "&lang=zh_CN";
        String msg = RequestUtils.httpRequest(url, accessToken, openId, lang);
        JSONObject obj = JSON.parseObject(msg);
        if (obj.containsKey("errcode")) {
            throw new BussinessException(new WXErrorMsgModel(obj.getString("errmsg"), obj.getInteger("errcode")));
        }
        WxUser user = new WxUser();
        user.setOpenid(obj.getString("openid"));
        user.setProvince(obj.getString("province"));
        user.setCity(obj.getString("city"));
        user.setCountry(obj.getString("country"));
        user.setNickname(obj.getString("nickname"));
        user.setHeadimgurl(obj.getString("headimgurl"));
        return user;
    }

    /**
     * 获取用户基本信息 可判断是否关注
     *
     * @param accessToken
     * @param openId
     * @param lang
     * @return
     */
    public static WxUser getUserInformationFromUnion(String accessToken, String openId, String lang) throws Exception {
        String url = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=" + accessToken + "&openid=" + openId + "&lang=" + lang;
        String msg = RequestUtils.httpRequest(url, accessToken, openId, lang);
        JSONObject obj = JSON.parseObject(msg);
        if (obj.containsKey("errcode")) {
            throw new BussinessException(new WXErrorMsgModel(obj.getString("errmsg"), obj.getInteger("errcode")));
        }
        WxUser user = new WxUser();
        user.setCity(obj.getString("city"));
        user.setCountry(obj.getString("country"));
        user.setSubscribed(obj.getInteger("subscribe"));
        user.setHeadimgurl(obj.getString("headimgurl"));
        user.setNickname(obj.getString("nickname"));
        user.setProvince(obj.getString("province"));
        return user;
    }

    /**
     * 刷新用户的Token
     *
     * @param appId
     * @param refreshToken 在getOpenId方法中获取的对象中带来的参数，生命周期较长，用于刷新token
     * @return
     */
    public static WXUserBaseInfoModel refreshUserToken(String appId, String refreshToken) throws Exception {
        String grantType = "refresh_token";
        String toUrl = "https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=" + appId + "&grant_type="
                + grantType + "&refresh_token=" + refreshToken;

        String msg = RequestUtils.httpRequest(toUrl, appId, grantType, refreshToken);

        JSONObject obj = JSON.parseObject(msg);
        WXUserBaseInfoModel userInfo = new WXUserBaseInfoModel(obj.getString("access_token"),
                obj.getString("expires_in"), obj.getString("refresh_token"), obj.getString("openid"),
                obj.getString("scope"), obj.containsKey("unionid") ? obj.getString("") : null);
        return userInfo;
    }

    /**
     * 根据OpenId获取用户信息
     *
     * @param accessToken
     * @param openId
     * @return
     */
    public static JSONObject getFansByUserOpenId(String accessToken, String openId) throws Exception {
        String url = "https://api.weixin.qq.com/cgi-bin/user/get?access_token=" + accessToken + "&next_openid=" + openId;
        String msg = RequestUtils.httpRequest(url, accessToken, openId);
        return JSON.parseObject(msg);
    }


    /**
     * 推送消息（群发）
     *
     * @param access_token
     * @param msg
     * @return
     */
    public static JSONObject pushMsg(String access_token, String msg) throws Exception {
        String url = "https://api.weixin.qq.com/cgi-bin/message/mass/sendall?access_token=" + access_token;
        String m = RequestUtils.httpRequest(url, msg);
        JSONObject obj = JSON.parseObject(m);
        return obj;
    }


    /**
     * 创建菜单
     *
     * @param request
     * @param access_token
     * @param msg
     * @return
     */
    public static JSONObject createMenu(HttpServletRequest request, String access_token, String msg) throws Exception {
        String url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=" + access_token;
        String res = RequestUtils.httpRequest(url, msg);
        return JSON.parseObject(res);
    }

    /**
     * 查询菜单
     *
     * @param request
     * @param access_token
     * @return
     */
    public static JSONObject query(HttpServletRequest request, String access_token) throws Exception {
        String url = "https://api.weixin.qq.com/cgi-bin/menu/get?access_token=" + access_token;
        String res = RequestUtils.httpRequest(url);
        return JSON.parseObject(res);
    }

    /**
     * 删除菜单
     *
     * @param request
     * @param access_token
     * @return
     */
    public static JSONObject delete(HttpServletRequest request, String access_token) throws Exception {
        String url = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=" + access_token;
        String res = RequestUtils.httpRequest(url);
        Util.log(res);
        return JSON.parseObject(res);
    }

    /**
     * 获取基础授权凭证
     *
     * @param appId
     * @param appSecret
     * @param grantType
     * @return
     */
    public static String getBaseAccessToken(String appId, String appSecret, String grantType, HttpServletRequest request) throws Exception {
        if (tokenQueue.containsKey(appId)) {
            if (tokenQueue.get(appId).restTime() < 200) {
                //token失效  创建一个新的token
                tokenQueue.get(appId).clear();
                tokenQueue.get(appId).updateToken(appId, appSecret, grantType, request);
            }
        } else {
            tokenQueue.put(appId, new Token(appId, appSecret, grantType, request));
        }
        return tokenQueue.get(appId).getACCESS_TOKEN();
    }

    /**
     * 获取js验证凭据
     *
     * @param request
     * @param access_token
     * @return
     * @throws Exception
     */
    public static String getJsApiTicket(HttpServletRequest request, String access_token, String uuid) throws Exception {
        if (ticketQueue.containsKey(uuid)) {
            if (ticketQueue.get(uuid).restTime() < 200) {
                //token失效  创建一个新的token
                ticketQueue.get(uuid).clear();
                ticketQueue.get(uuid).updateJsApiTicket(access_token);
            }
        } else {
            ticketQueue.put(uuid, new JsApiTicket(request, access_token));

        }
        return ticketQueue.get(uuid).getJS_API_TICKET();
    }

    /**
     * 构建autho2认证地址
     *
     * @param serviceUrl
     * @param appid
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String createAutho2Url(String serviceUrl, String appid) throws Exception {
        LOGGER.info("serviceUrl:{}", serviceUrl);
        final StringBuffer buffer = new StringBuffer();
        buffer.append(OAUTH_URL).append("?").append("appid=").append(appid).append("&redirect_uri=").append
                (URLEncoder.encode(serviceUrl, "utf-8")).append("&response_type=").append(RESPONSE_TYPE).append("&scope=")
                .append(SCOPE_USER_INFO)
                .append("&state=").append(STATE).append("#wechat_redirect");
        LOGGER.info("redirectUrl:{},after decode:{}", buffer.toString());
        return buffer.toString();
    }


    /**
     * 获取AppSecret
     *
     * @param request
     * @return
     * @throws LoginException
     */
    public static String getAppSecret(HttpServletRequest request) throws Exception {
        PublicNumber u = (PublicNumber) request.getSession().getAttribute(Constant.SESSION_PUBLIC_NUMBER);
        if (CommonUtils.isEmpty(u)) {
            throw new LoginException("公众号账号不存在");
        } else {
            return u.getAppsecret();
        }
    }

    /**
     * 网页授权地址，获取code
     *
     * @param toUrl 目标链接
     * @param appId
     * @param scope 授权域
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String changeUrlToGetCode(String toUrl, String appId, String scope, String state) throws Exception {
        String encodeUrl = URLEncoder.encode(toUrl, "utf-8");
        String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="
                + appId + "&redirect_uri=" + encodeUrl + "&response_type=code&scope="
                + scope + "&state=" + state + "#wechat_redirect";
        return url;
    }

    /**
     * 获取用户分组
     *
     * @param access_token
     * @return
     */
    public static List<Group> getUserGroup(String access_token) throws Exception {
        String url = "https://api.weixin.qq.com/cgi-bin/groups/get?access_token=" + access_token;

        String msg = RequestUtils.httpRequest(url);
        JSONObject obj = JSON.parseObject(msg);
        String groups = obj.getString("groups");
        return JSON.parseArray(groups, Group.class);
    }

    /**
     * 获取短链接
     *
     * @param longUrl
     * @param accessToken
     * @return
     * @throws IOException
     */
    public static LongToShortMsgModel longToShortUrl(String longUrl, String accessToken) throws IOException {
        String url = "https://api.weixin.qq.com/cgi-bin/shorturl?access_token=" + accessToken;
        Map<String, String> params = new HashMap<>();
        params.put("long_url", longUrl);
        params.put("action", "long2short");
        params.put("access_token", accessToken);
        String pars = JSON.toJSONString(params);
        String res = RequestUtils.httpRequest(url, pars);
        System.out.println("res:{}" + res);
        LOGGER.info("long url:{} to short url.result :{}", longUrl, res);
        LongToShortMsgModel lts = JSON.parseObject(res, LongToShortMsgModel.class);
        return lts;
    }

}
