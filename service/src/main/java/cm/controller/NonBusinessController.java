package cm.controller;

import cm.entity.ServerConcurrentLog;
import cm.entity.ServerInfo;
import cm.service.ConcurrentLogService;
import cm.web.MediaTypes;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 类名：cm.controller.NonBussinessController
 * 创建者： CM .
 * 创建时间：2016/4/15
 */
@Controller
@RequestMapping("/analysis/non-bussiness")
public class NonBusinessController extends BaseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(NonBusinessController.class);

    @Autowired
    private ConcurrentLogService concurrentLogService;

    @ResponseBody
    @RequestMapping(value = "/concurrent/list", method = RequestMethod.POST, produces = MediaTypes.JSON_UTF_8)
    public String concurrentList(ModelMap modelMap) {
        try {
            List<ServerInfo> servers = concurrentLogService.findAllServer();
            List<ServerConcurrentLog> logs = concurrentLogService.findConcurrentByCondition(servers.get(0), null);
            modelMap.put("data", logs);
            this.success(modelMap);
        } catch (Exception ex) {
            this.failed(modelMap);
            LOGGER.error("get concurrent log list occur error:", ex);
        }

        return JSON.toJSONString(modelMap);
    }
}
