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
                            addLog(in, x * i, 2016-1900, m, d, h);
                        }
                        //21点-次日7点 请求数量降低 指数为100
                        for (int h = 21; h < 31; h++) {
                            int hour = h > 24 ? h - 24 : h;
                            long x = (long) (Math.random() * 100);
                            long i = (long) (Math.random() * 100);
                            addLog(in, x * i, 2016-1900, m, d, hour);
                        }
                    }
                }
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
