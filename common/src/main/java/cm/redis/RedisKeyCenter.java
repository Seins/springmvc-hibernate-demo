package cm.redis;

import cm.util.DateUtil;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * redis key 常量�?
 */
public final class RedisKeyCenter {

    public static final String TEST_DATA_CHANGE_LOG = "redis:test:data:change:log"; //���ݱ仯�����¼

    public static final String TEST_DATA_WRITE_LOG = "redis:test:data:write:log"; //����д�������¼

    public static final String TEST_DATA_MODEL_LOG = "model:model:data:log"; //��������ģ��1

    public static final String TEST_DATA_MODEL2_LOG = "model:model:data2:log"; //��������ģ��2

    public static final String REQUEST_LOG = "redis:request:data:log"; // �ӿ�������־ key

    /**
     * ��ȡ���յ�������־��¼
     *
     * @return
     */
    public static final String getTodayRequestLogKey() {
        return REQUEST_LOG + ":" + DateUtil.getTodayStartDateTime();
    }

    /**
     * 获取redis数据条目数在不同时间统计的数据记�?
     * 根据每天的时间戳生成key，保证每�?天的数据分开
     *
     * @return
     */
    public static String getTodayDataChangeLogKey() {
        return TEST_DATA_CHANGE_LOG + ":" + DateUtil.getTodayStartDateTime();
    }

    /**
     * 获取指定日期redis数据条目数在不同时间统计的数据记�?
     *
     * @return
     */
    public static String getSomeDateChangeLogKey(Date date) {
        return TEST_DATA_CHANGE_LOG + ":" + DateUtil.getDateStartTime(date);
    }

    /**
     * 获取redis数据写入情况在不同时间统计的数据记录
     * 根据每天的时间戳生成key，保证每�?天的数据分开
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
