package cm.controller;

import cm.web.MediaTypes;
import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
     * @param modelMap
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaTypes.JSON_UTF_8)
    public String msgDemo(ModelMap modelMap) {
        modelMap.clear();
        modelMap.put("msg", "request success!framework running success");
        modelMap.put("data","data content...");
        this.success(modelMap);
        return JSON.toJSONString(modelMap);
    }
}
