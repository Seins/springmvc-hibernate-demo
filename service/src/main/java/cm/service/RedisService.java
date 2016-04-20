package cm.service;

import cm.model.DataChangeLogModel;
import cm.redis.RedisKeyCenter;
import cm.util.DateUtil;
import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * class name： cm.service.RedisService
 * author： CM
 * create time： 2016/4/21.
 */
@Service
public class RedisService extends BaseService {

    /**
     * 添加测试数据，原则上每天分24个小时记录，第二天会持久化到数据库中，
     * redis中只存一天的数据，避免数据量积累过多
     */
    public void addTestData() {
        System.out.println("start:" + System.currentTimeMillis());
        for (int y = 2015; y < 2017; y++) {
            int month = 12;
            if (y >= 2016) {
                month = 5;//16年数据到5月份
            }
            for (int m = 1; m < month; m++) {
                for (int d = 1; d < 31; d++) {
                    DataChangeLogModel log = new DataChangeLogModel();
                    for (int i = 0; i < 24; i++) {
                        if ((i > 8 && i < 12) || (i > 14 && i < 18)) {
                            double amount = Math.random() * 10000000;
                            log.setDataAmount((long) amount);
                        } else {
                            double amount = Math.random() * 60000;
                            log.setDataAmount((long) amount);
                        }
                        Date date = new Date(y - 1900, m, d);
                        Timestamp timestamp = new Timestamp(date.getTime());
                        timestamp.setHours(i);
                        timestamp.setNanos(0);
                        timestamp.setSeconds(0);
                        timestamp.setSeconds(0);
                        log.setLogTime(timestamp);
                        System.out.println(RedisKeyCenter.getSomeDateChangeLogKey(date));
                        redisOperator.lpush(RedisKeyCenter.getSomeDateChangeLogKey(date), JSON.toJSONString(log));
                        //设置有效期为一天
                        redisOperator.expire(RedisKeyCenter.getSomeDateChangeLogKey(date), (int) (DateUtil.getTodayRestTime() / 1000));
                    }
                }
            }
        }
        System.out.println("end:" + System.currentTimeMillis());
    }
}
