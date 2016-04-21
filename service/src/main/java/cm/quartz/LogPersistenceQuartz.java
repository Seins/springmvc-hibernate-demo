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
 * redis�������仯ͳ�����ݳ־û�ģ�飬ÿ��ͬ��һ�Σ��־û�ǰһ�������
 */
@Component
public class LogPersistenceQuartz {
    private static final Logger LOGGER = LoggerFactory.getLogger(LogPersistenceQuartz.class);

    @Autowired
    protected RedisService redisService;
    @Autowired
    protected ConfigRedisOperator redisOperator;

    /**
     * ���ڳ־û����ݵķ�����redis��ֻ�浱ǰ����־���ݣ��ڶ��콫��д�뵽���ݿ⡣
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
