package cm.service;

import cm.entity.CacheDataChangeLog;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.sql.Timestamp;

/**
 * 类名：cm.service.CacheLogServiceTest
 * 创建者： CM .
 * 创建时间：2016/4/25
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath:applicationContext.xml"
})
@WebAppConfiguration
public class CacheLogServiceTest {
    @Autowired
    private CacheLogService service;

    @Test
    public void addLogTest() {
        for (int y = 2015; y <= 2016; y++) {
            int maxMonth = 12;
            if (y == 2016) {
                maxMonth = 5;
            }
            for (int m = 1; m < maxMonth; m++) {
                for (int d = 0; d < 30; d++) {
                    //8-20点 高峰期，数据量大，波动大,
                    for (int h = 8; h < 20; h++) {
                        long x = (long) (Math.random() * 13923);
                        int point = h % 2 == 0 ? 79 : 39;
                        long i = (long) (Math.random() * point);
                        Timestamp logTime = new Timestamp(y - 1900, m, d, h, 0, 0, 0);
                        addLog(logTime, x * i);
                    }
                    //21点-次日7点 请求数量降低 指数降低，波动小
                    for (int h = 21; h < 31; h++) {
                        int hour = h > 24 ? h - 24 : h;
                        Timestamp logTime = new Timestamp(y - 1900, m, d, hour, 0, 0, 0);

                        long x = (long) (Math.random() * 1323);
                        long i = (long) (Math.random() * 67);
                        addLog(logTime, x * i);
                    }
                }
            }
        }
    }


    private void addLog(Timestamp logTime, Long dataAmount) {
        CacheDataChangeLog log = new CacheDataChangeLog();
        log.setLogTime(logTime);
        log.setDataAmount(dataAmount);
        service.addLog(log);
    }
}
