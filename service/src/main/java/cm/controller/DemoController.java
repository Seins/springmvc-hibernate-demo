package cm.controller;

import cm.util.CommonUtils;
import cm.util.Constant;
import cm.util.JsApiSignUtil;
import cm.util.WechatUtils;
import cm.web.MediaTypes;
import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 类名：cm.controller.DemoController
 * 创建者： CM .
 * 创建时间：2016/3/30
 */
@Controller
@RequestMapping(value = "/service/json")
public class DemoController extends BaseController {

    /**
     * 演示方法,JSON数据统一输出格式
     *
     * @param modelMap
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "", produces = MediaTypes.JSON_UTF_8)
    public String msgDemo(ModelMap modelMap) {
        modelMap.clear();
        modelMap.put("msg", "request success!framework running success");
        modelMap.put("data", "data content...");
        this.success(modelMap);
        return JSON.toJSONString(modelMap);
    }

    @RequestMapping(value = "/amazing/run/index", method = RequestMethod.GET)
    public String index(ModelMap modelMap, HttpServletRequest request) throws Exception {
        String appId = Constant.getPropertie("appid");
        String appSecret = Constant.getPropertie("appsecret");
        String uuid = Constant.getPropertie("uuid");
        String accessToken = WechatUtils.getBaseAccessToken(appId, appSecret, Constant.GRANT_TYPE_CLIENT_CREDENTIAL, request);
        String jsTicket = WechatUtils.getJsApiTicket(request, accessToken, uuid);
        Map sign = JsApiSignUtil.sign(jsTicket, CommonUtils.fullUrlBuild(request));
        modelMap.put("appid", appId);
        modelMap.put("timestamp", sign.get("timestamp"));
        modelMap.put("nonceStr", sign.get("nonceStr"));
        modelMap.put("signature", sign.get("signature"));
        return "/index";
    }

    @RequestMapping(value = "/amazing/run/qrcode", method = RequestMethod.GET)
    public String qrcode(ModelMap modelMap) throws Exception {

        return "/qrcode";
    }

    @ResponseBody
    @RequestMapping(value = "/amazing/run/qrcode/check", method = RequestMethod.GET)
    public String qrcodeCheck(ModelMap modelMap, @RequestParam Long id) throws Exception {
        modelMap.put("icon", "100.00");
        this.success(modelMap);
        return JSON.toJSONString(modelMap);
    }

}
