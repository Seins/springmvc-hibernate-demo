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
import org.springframework.web.bind.annotation.RequestParam;
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
    public String concurrentList(ModelMap modelMap, @RequestParam(value = "time") Long time) {
        try {
            List<ServerInfo> servers = concurrentLogService.findAllServer();
            List<String> serverNames = new ArrayList();
            for(ServerInfo info : servers){
                serverNames.add(info.getServerName());
            }
            List logData = new ArrayList();
            List<String> xAxis  =new ArrayList<>();
            Timestamp timestamp = new Timestamp(time);
            timestamp.setYear(timestamp.getYear() - 1);
            timestamp.setMinutes(0);
            timestamp.setNanos(0);
            timestamp.setSeconds(0);
            for(int i = 0 ;i<24;i++){
                xAxis.add(i+":00");
            }
            for(ServerInfo server: servers) {

                List<ServerConcurrentLog> logs = concurrentLogService.findConcurrentByCondition(server, timestamp);
                Map logMap = new HashMap();
                logMap.put("name", server.getServerName());
                List<Long> amounts = new ArrayList<>();

                for (ServerConcurrentLog log : logs) {
                    amounts.add(log.getConcurrentAmount());
                }
                logMap.put("data", amounts);
                logMap.put("type","line");
                logMap.put("stack","总量");
                logData.add(logMap);
            }
            Map data = new HashMap();
            data.put("series",logData);
            data.put("xAxis",xAxis);
            data.put("legend",serverNames);
            modelMap.put("data", data);
            this.success(modelMap);
        } catch (Exception ex) {
            this.failed(modelMap);
            LOGGER.error("get concurrent log list occur error:", ex);
        }

        return JSON.toJSONString(modelMap);
    }


    @ResponseBody
    @RequestMapping(value = "/server/name/list", method = RequestMethod.POST, produces = MediaTypes.JSON_UTF_8)
    public String serverList(ModelMap modelMap) {
        try {
            List<ServerInfo> serverInfos = concurrentLogService.findAllServer();
            List<String> serverNames = new ArrayList();
            for(ServerInfo info : serverInfos){
                serverNames.add(info.getServerName());
            }
            modelMap.put("data", serverNames);
            this.success(modelMap);
        } catch (Exception ex) {
            this.failed(modelMap);
            LOGGER.error("get concurrent log list occur error:", ex);
        }

        return JSON.toJSONString(modelMap);
    }
}
