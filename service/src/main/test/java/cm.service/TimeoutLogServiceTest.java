package cm.service;

import cm.entity.InterfaceInfo;
import cm.entity.ServerConcurrentLog;
import cm.entity.ServerInfo;
import cm.entity.ServerTimeoutLog;
import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.util.CollectionUtils;

import java.sql.Timestamp;
import java.util.List;
import java.util.Random;

/**
 * Created by CM on 16/4/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
@WebAppConfiguration
public class TimeoutLogServiceTest {

    @Autowired
    private TimeOutLogService service;
    String[] interfaceName = {"查询手机", "查询IP", "查询身份证", "快递查询接口", "ip查询接口"};

    @Test
    public void testAddInterface() {
        InterfaceInfo interfaceInfo = new InterfaceInfo();
        List<ServerInfo> servers = service.findAllServer();
        for (ServerInfo server : servers) {
            for (String in : interfaceName) {
                interfaceInfo.setInterfaceName(in);
                interfaceInfo.setInterfaceUri("/test/uri");
                interfaceInfo.setServerInfo(server);
                service.addInterface(interfaceInfo);
            }
        }
    }

    /**
     * 生成服务器节点1 在2016年5月的五个测试接口(id in {1,2,3,4,5})的超时数据测试数据
     */
    @Test
    public void testAddTimeOutLog() {
        List<ServerInfo> servers = service.findAllServer();
        ServerInfo server = servers.get(2);
        List<InterfaceInfo> interfaces = service.findInterfaceByServerInfo(server);
        for(InterfaceInfo in : interfaces) {
            for (int d = 1; d < 30; d++) {
                //8-20点 概率为60%
                for (int h = 8; h < 20; h++) {
                    for (int mi = 0; mi < 59; mi++) {
                        int i = (int) (Math.random() * 100);
                        if (i <= 60) {
                            addLog(server, in, d, h, mi);
                        }
                    }
                }
                //21点-次日7点 概率为5%
                for (int h = 21; h < 31; h++) {
                    for (int mi = 0; mi < 59; mi++) {
                        int i = (int) (Math.random() * 100);
                        if (i <= 5) {
                            int hour = h > 23 ? h - 24 : h;
                            addLog(server, in, d, hour, mi);
                        }
                    }
                }
            }
        }
    }

    /**
     * 插入超时日志测试数据
     *
     * @param server
     * @param in
     * @param d
     * @param h
     * @param mi
     */
    private void addLog(ServerInfo server, InterfaceInfo in, int d, int h, int mi) {
        ServerTimeoutLog log = new ServerTimeoutLog();
        log.setErrorParams("{\"params\":\"value\"}");
        log.setErrorStack("java.lang.Exception:xxxxxxx");
        log.setInterfaceInfo(in);
        log.setServerInfo(server);
        Timestamp timestamp = new Timestamp(2016 - 1900, 4, d, h, mi, 0, 0);
        log.setLogTime(timestamp);
        Long id = service.addLog(log);
    }

    @Test
    public void testFindServerLog() {
        ServerInfo server = service.findAllServer().get(0);
        Timestamp timestamp = new Timestamp(2016 - 1900, 4, 10, 20, 0, 0, 0);
        List counts = service.findServerTimeoutLogByDate(server, timestamp);
        System.out.println("count:" + JSON.toJSONString(counts));
    }
}
