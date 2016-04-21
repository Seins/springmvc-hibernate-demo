package cm.quartz;

import cm.redis.ConfigRedisOperator;
import cm.redis.RedisKeyCenter;
import cm.service.RedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * redis数据量变化统计数据持久化模块，每天同步一次，持久化前一天的数据
 */
@Component
public class LogPersistenceQuartz {
    private static final Logger LOGGER = LoggerFactory.getLogger(LogPersistenceQuartz.class);

    @Autowired
    protected RedisService redisService;
    @Autowired
    protected ConfigRedisOperator redisOperator;

    /**
     * 用于持久化数据的方法，redis中只存当前的日志数据，第二天将它写入到数据库。
     */
    public void run() {
        LOGGER.info("log sync task start working....");
        try {
            List<String> logs = redisOperator.lrange(RedisKeyCenter.getTodayDataChangeLogKey(), 0, -1);
            redisService.addLog(logs);
        } catch (Exception ex) {
            LOGGER.error("log sync task occur error:", ex);
        }
        LOGGER.info("log sync task finished working....");

    }

}
