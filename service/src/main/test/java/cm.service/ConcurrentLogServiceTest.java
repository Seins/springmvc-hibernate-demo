package cm.service;

import cm.entity.ServerConcurrentLog;
import cm.entity.ServerInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.sql.Timestamp;
import java.util.List;
import java.util.Random;

/**
 * Created by CM on 16/4/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath:applicationContext.xml"
})
@WebAppConfiguration
public class ConcurrentLogServiceTest {
    @Autowired
    private ConcurrentLogService service;

    @Test
    public void testAddConcurrentLog() {

        int count = 0;
        for (int year = 2015; year < 2016; year++) {
            for (int m = 1; m <= 12; m++) {
                for (int d = 1; d < 30; d++) {
                    List<ServerInfo> servers = service.findAllServer();
                    for (ServerInfo server : servers) {
                        for(int h = 0;h<24;h++) {
                            ServerConcurrentLog log = new ServerConcurrentLog();
                            Random random = new Random();
                            int max = 100;
                            if(h>7 && h<11){
                                max = 600;
                            }else if(h>13&& h<20){
                                max = 900;
                            }
                            int amount = random.nextInt(max) % max + 1;
                            log.setConcurrentAmount(new Long(amount));

                            Timestamp timestamp = new Timestamp(year-1900, m, d, h, 0, 0, 0);
                            log.setLogTime(timestamp);
                            log.setServerInfo(server);
                            Long id = service.addConcurrentLog(log);
                            System.out.println(id);
                            count++;
                        }
                    }
                }
            }
        }
        System.out.println("count =" + count);
    }
}
