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

import java.sql.Timestamp;
import java.util.*;

/**
 * ������cm.controller.NonBussinessController
 * �����ߣ� CM .
 * ����ʱ�䣺2016/4/15
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
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            timestamp.setMinutes(0);
            timestamp.setNanos(0);
            timestamp.setSeconds(0);
            List<ServerConcurrentLog> logs = concurrentLogService.findConcurrentByCondition(servers.get(0), timestamp);
            List logData = new ArrayList();
            for(ServerConcurrentLog log : logs){
                Map logMap  = new HashMap();
                logMap.put("logTime",log.getLogTime());
                logMap.put("amount",log.getConcurrentAmount());
                logMap.put("serverName",log.getServerInfo().getServerName());
                logData.add(logMap);

            }
            modelMap.put("data", logData);
            this.success(modelMap);
        } catch (Exception ex) {
            this.failed(modelMap);
            LOGGER.error("get concurrent log list occur error:", ex);
        }

        return JSON.toJSONString(modelMap);
    }
}
