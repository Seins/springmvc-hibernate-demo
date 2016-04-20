package cm.redis;

import cm.util.DateUtil;

import java.sql.Date;

/**
 * redis key 常量类
 */
public final class RedisKeyCenter {

    public static final String TEST_DATA_CHANGE_LOG = "redis:test:data:change:log";

    public static final String TEST_DATA_WRITE_LOG = "redis:test:data:write:log";

    /**
     * 获取redis数据条目数在不同时间统计的数据记录
     * 根据每天的时间戳生成key，保证每一天的数据分开
     *
     * @return
     */
    public static String getTodayDataChangeLogKey() {
        return TEST_DATA_CHANGE_LOG + ":" + DateUtil.getTodayStartDateTime();
    }

    /**
     * 获取指定日期redis数据条目数在不同时间统计的数据记录
     *
     * @return
     */
    public static String getSomeDateChangeLogKey(Date date) {
        return TEST_DATA_CHANGE_LOG + ":" + DateUtil.getDateStartTime(date);
    }

    /**
     * 获取redis数据写入情况在不同时间统计的数据记录
     * 根据每天的时间戳生成key，保证每一天的数据分开
     *
     * @return
     */
    public static String getTodayDataWriteLogKey() {
        return TEST_DATA_WRITE_LOG + ":" + DateUtil.getTodayStartDateTime();
    }

    /**
     * 获取指定日期redis数据写入情况在不同时间统计的数据记录
     *
     * @return
     */
    public static String getTodayDataWriteLogKey(Date date) {
        return TEST_DATA_WRITE_LOG + ":" + +DateUtil.getDateStartTime(date);
    }
}
