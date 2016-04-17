package cm.controller;

import cm.entity.ServerInfo;
import cm.service.MulInterfaceService;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by CM on 16/4/17.
 */
@Controller
@RequestMapping(value = "/analysis/business")
public class BusinessController extends BaseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(BusinessController.class);
    @Autowired
    private MulInterfaceService mulInterfaceService;

    @ResponseBody
    @RequestMapping(value = "/interface/list", method = RequestMethod.POST, produces = MediaTypes.JSON_UTF_8)
    public String timeoutList(ModelMap modelMap, @RequestParam(value = "logTime") Long logTime,
                              @RequestParam(value = "serverId")int serverId) {
        try {
            List<ServerInfo> servers = mulInterfaceService.findAllServer();
            Map data = new HashMap();
            Map<String, List> series = mulInterfaceService.findServerInterfaceLogByDate(servers.get(serverId), new Timestamp(logTime));
            data.put("series", series);
            data.put("xAxis", getXAxis());
            modelMap.put("data", data);
            this.success(modelMap);
        } catch (RuntimeException ex) {
            this.failed(modelMap, ex.getMessage());
            LOGGER.error("server time out list occur error:", ex);
        } catch (Exception ex) {
            this.failed(modelMap);
            LOGGER.error("server time out list occur error:", ex);
        }

        return JSON.toJSONString(modelMap);
    }


    @ResponseBody
    @RequestMapping(value = "/response/list", method = RequestMethod.POST, produces = MediaTypes.JSON_UTF_8)
    public String responseTimeList(ModelMap modelMap, @RequestParam(value = "logTime") Long logTime,
                              @RequestParam(value = "serverId")int serverId) {
        try {
            List<ServerInfo> servers = mulInterfaceService.findAllServer();
            Map data = new HashMap();
            Map<String, List> series = mulInterfaceService.findServerResponseLogByDate(servers.get(serverId), new Timestamp(logTime));
            data.put("series", series);
            data.put("xAxis", getXAxis());
            modelMap.put("data", data);
            this.success(modelMap);
        } catch (RuntimeException ex) {
            this.failed(modelMap, ex.getMessage());
            LOGGER.error("server time out list occur error:", ex);
        } catch (Exception ex) {
            this.failed(modelMap);
            LOGGER.error("server time out list occur error:", ex);
        }

        return JSON.toJSONString(modelMap);
    }
}
