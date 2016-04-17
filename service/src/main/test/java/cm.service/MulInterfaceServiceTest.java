package cm.service;

import cm.entity.InterfaceInfo;
import cm.entity.InterfaceRequestLog;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by CM on 16/4/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
@WebAppConfiguration
public class MulInterfaceServiceTest {

    @Autowired
    private MulInterfaceService service;

    @Test
    public void testAddLog() {
        List<InterfaceInfo> ins = service.findAllInterface();
        for (InterfaceInfo in : ins) {
            for (int m = 1; m <= 12; m++) {
                for (int d = 1; d <= 30; d++) {
                    // 8-20 请求数量增加 指数为10000
                    for (int h = 8; h < 20; h++) {
                        long x = (long) (Math.random() * 10000);
                        long i = (long) (Math.random() * 100);
                        addLog(in, x * i, 2016 - 1900, m, d, h);
                    }
                    //21点-次日7点 请求数量降低 指数为100
                    for (int h = 21; h < 31; h++) {
                        int hour = h > 24 ? h - 24 : h;
                        long x = (long) (Math.random() * 100);
                        long i = (long) (Math.random() * 100);
                        addLog(in, x * i, 2016 - 1900, m, d, hour);
                    }
                }
            }
        }
    }

    /**
     * 测试 注入请求响应时间数据,数据模拟半小时收集一次,1小时纪录一次平均值
     */
    @Test
    public void testUpdateResponseAverageTime() {
        List<InterfaceRequestLog> logs = service.findAllLogs();
        for (InterfaceRequestLog log : logs) {
            if (log.getLogTime().getHours() > 8 && log.getLogTime().getHours() < 20) {
                //8-20点  平均响应时间拉长,业务频繁起
                long x = (long) (Math.random() * 60);
                long i = (long) (Math.random() * 100);
                log.setAverageResponseTime(x * i);
            }
            if ((log.getLogTime().getHours() > 0 && log.getLogTime().getHours() <= 8) || (log.getLogTime().getHours() >= 20 || log.getLogTime().getHours() < 24)) {
                //21点-次日7点 请求数量降低 响应时间减小
                long x = (long) (Math.random() * 10);
                long i = (long) (Math.random() * 100);
                log.setAverageResponseTime(x * i);
            }
            service.updateLog(log);
        }
    }


    private void addLog(InterfaceInfo in, long amount, int year, int month, int day, int hours) {
        InterfaceRequestLog log = new InterfaceRequestLog();
        log.setInterfaceInfo(in);
        Timestamp logTime = new Timestamp(year, month, day, hours, 0, 0, 0);
        log.setLogTime(logTime);
        log.setRequestAmount(amount);
        service.addLog(log);
    }
}
