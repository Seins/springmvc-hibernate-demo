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
 * redis�������仯ͳ��ģ�飬ÿСʱͳ��һ�� ��һ���¼24�Σ��ڶ���־û������ݿ⣨��һ����ʱ����
 */
@Component
public class LogSyncQuartz {
    private static final Logger LOGGER = LoggerFactory.getLogger(LogSyncQuartz.class);

    @Autowired
    protected ConfigRedisOperator redisOperator;

    /**
     * ����ͳ�����ݵķ�����redis��ֻ�浱ǰ����־���ݣ��ڶ��콫��д�뵽���ݿ⡣
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
            //��¼��־�����ù���ʱ��
            LOGGER.info("sync time:{}", logTime.getTime());
            redisOperator.lpush(RedisKeyCenter.getTodayDataChangeLogKey(), JSON.toJSONString(log));
            redisOperator.expire(RedisKeyCenter.getTodayDataChangeLogKey(), (int) (DateUtil.getTodayRestTime() / 1000));
        } catch (Exception ex) {
            LOGGER.error("log sync task occur error:", ex);
        }
        LOGGER.info("log sync task finished working....");

    }

    /**
     * ��ȡ���з�����key������ ����
     */
    private Long amountAllKeysData() {
        String matcher = "model:*";
        Set<String> keys = redisOperator.keys(matcher);
        Long amount = 0L;
        for (String key : keys) {
            if (key.indexOf("test") != -1) {
                //�������ͳ����־�����ݣ�����Ҫ����ͳ��
                continue;
            }
            //�������������ж��field
            amount += redisOperator.hlen(key);
        }
        LOGGER.info("log sync model obj amount:{}", amount);
        return amount;
    }

    /**
     * ��ȡ�������������� ����
     * return {long} ���
     */
    private Long amoutAllListKeysData() {
        String matcher = "redis:*";
        Set<String> keys = redisOperator.keys(matcher);
        Long amount = 0L;
        for (String key : keys) {
            if (key.indexOf("test") != -1) {
                //�������ͳ����־�����ݣ�����Ҫ����ͳ��
                continue;
            }
            //������ֱ��ͳ����Ŀ��
            amount += redisOperator.llen(key);
        }
        LOGGER.info("log sync array obj amount:{}", amount);
        return amount;
    }
}
