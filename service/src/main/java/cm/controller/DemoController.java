package cm.controller;

import cm.web.MediaTypes;
import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * ������cm.controller.DemoController
 * �����ߣ� CM .
 * ����ʱ�䣺2016/3/30
 */
@Controller
@RequestMapping(value = "/service/json")
public class DemoController extends BaseController {

    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaTypes.JSON_UTF_8)
    public String msgDemo(ModelMap modelMap) {
        modelMap.clear();
        modelMap.put("msg", "request success!framework running success");
        this.success(modelMap);
        return JSON.toJSONString(modelMap);
    }
}
