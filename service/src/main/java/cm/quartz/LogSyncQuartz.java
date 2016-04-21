package cm.quartz;

import cm.model.DataChangeLogModel;
import cm.redis.ConfigRedisOperator;
import cm.redis.RedisKeyCenter;
import cm.util.DateUtil;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Set;

/**
 * redis数据量变化统计模块，每小时统计一次 ，一天记录24次，第二天持久化到数据库（另一个定时器）
 */
@Component
public class LogSyncQuartz {
    private static final Logger LOGGER = LoggerFactory.getLogger(LogSyncQuartz.class);

    @Autowired
    protected ConfigRedisOperator redisOperator;

    /**
     * 用于统计数据的方法，redis中只存当前的日志数据，第二天将它写入到数据库。
     */
    public void run() {
        LOGGER.info("log sync task start working....");
        try {
            Long modelAmount = amountAllKeysData();
            Long arrayAmount = amoutAllListKeysData();
            long amount = modelAmount + arrayAmount;
            Timestamp logTime = new Timestamp(System.currentTimeMillis());
            logTime.setNanos(0);
            logTime.setMinutes(0);
            logTime.setSeconds(0);
            DataChangeLogModel log = new DataChangeLogModel();
            log.setDataAmount(amount);
            log.setLogTime(logTime);
            //记录日志并设置过期时间
            LOGGER.info("sync time:{}", logTime.getTime());
            redisOperator.lpush(RedisKeyCenter.getTodayDataChangeLogKey(), JSON.toJSONString(log));
            redisOperator.expire(RedisKeyCenter.getTodayDataChangeLogKey(), (int) (DateUtil.getTodayRestTime() / 1000));
        } catch (Exception ex) {
            LOGGER.error("log sync task occur error:", ex);
        }
        LOGGER.info("log sync task finished working....");

    }

    /**
     * 获取所有非数组key的数据 数量
     */
    private Long amountAllKeysData() {
        String matcher = "model:*";
        Set<String> keys = redisOperator.keys(matcher);
        Long amount = 0L;
        for (String key : keys) {
            if (key.indexOf("test") != -1) {
                //如果数据统计日志的数据，则不需要计入统计
                continue;
            }
            //非数组型数据有多个field
            amount += redisOperator.hlen(key);
        }
        LOGGER.info("log sync model obj amount:{}", amount);
        return amount;
    }

    /**
     * 获取所有数组型数据 数量
     * return {long} 结果
     */
    private Long amoutAllListKeysData() {
        String matcher = "redis:*";
        Set<String> keys = redisOperator.keys(matcher);
        Long amount = 0L;
        for (String key : keys) {
            if (key.indexOf("test") != -1) {
                //如果数据统计日志的数据，则不需要计入统计
                continue;
            }
            //数组型直接统计条目数
            amount += redisOperator.llen(key);
        }
        LOGGER.info("log sync array obj amount:{}", amount);
        return amount;
    }
}
