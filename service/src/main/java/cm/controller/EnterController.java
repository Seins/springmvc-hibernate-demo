package cm.controller;

import cm.domainEnum.ModuleEnmu;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by CM on 16/4/16.
 */
@Controller
@RequestMapping("/{module}")
public class EnterController {

    /**
     * 页面数据注入,不同的模块,主体内容的页面URI不同
     *
     * @param modelMap
     * @param module
     * @return
     */
    @RequestMapping()
    public String index(ModelMap modelMap, @PathVariable(value = "module") ModuleEnmu module) {
        switch (module) {
            case CONCURRENT:
                modelMap.put("uri", "/WEB-INF/view/concurrent.jsp");
                break;
            case TIME_OUT:
                modelMap.put("uri", "/WEB-INF/view/timeout.jsp");
                break;
            case ALL_INTERFACE_FREQUENCE:
                modelMap.put("uri", "/WEB-INF/view/concurrent.jsp");
                break;
            case INTERFACE_FREQUENCE:
                modelMap.put("uri", "/WEB-INF/view/concurrent.jsp");
                break;
            case INTERFACE_RESPONSE:
                modelMap.put("uri", "/WEB-INF/view/concurrent.jsp");
                break;
            default:
                modelMap.put("uri", "/WEB-INF/view/concurrent.jsp");
        }

        return "forward:/";
    }
}
