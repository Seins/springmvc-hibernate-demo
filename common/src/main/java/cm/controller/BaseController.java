package cm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;

import javax.servlet.http.HttpSession;

/**
 * @修改人：CM
 * @修改时间： 2015/4/9 11:29.
 */
public class BaseController {

    @Autowired
    HttpSession session;


    /**
     * 成功
     *
     * @param modelMap
     */
    public void success(ModelMap modelMap) {
        modelMap.put("result", "0");
    }

    /**
     * 不可预期的错误
     *
     * @param modelMap
     */
    public void failed(ModelMap modelMap) {
        modelMap.put("result", "-1");
        modelMap.put("msg", "服务器发生未知错误，请求失败!");
    }

    /**
     * 业务异常
     *
     * @param modelMap
     * @param e
     */
    public void failed(ModelMap modelMap, RuntimeException e) {
        modelMap.put("result", "-3");
        modelMap.put("msg", e.getMessage());
    }

    /**
     * 已知异常
     *
     * @param modelMap
     * @param errorMsg
     */
    public void failed(ModelMap modelMap, String errorMsg) {
        modelMap.put("result", "-2");
        modelMap.put("msg", errorMsg);
    }


}
