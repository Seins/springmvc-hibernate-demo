package cm.util;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * class name： cm.util.DateUtil
 * author： CM
 * create time： 2016/4/21.
 */
public final class DateUtil {
    private static DateFormat format = new SimpleDateFormat();

    /**
     * 获取当前时间的 时间戳
     *
     * @return
     */
    public static long getCurrentDateTime() {
        return System.currentTimeMillis();
    }

    /**
     * 获取今日开始的时间 0点0分0秒 的时间戳
     *
     * @return
     */
    public static long getTodayStartDateTime() {
        Timestamp time = new Timestamp(System.currentTimeMillis());
        time.setNanos(0);
        time.setHours(0);
        time.setMinutes(0);
        time.setSeconds(0);

        return time.getTime();
    }

    /**
     * 获取今日结束的时间戳
     *
     * @return
     */
    public static long getTodayEndDateTime() {
        Timestamp time = new Timestamp(System.currentTimeMillis());
        time.setNanos(999999999);
        time.setHours(23);
        time.setMinutes(59);
        time.setSeconds(59);

        return time.getTime();
    }

    /**
     * 获取今天剩余的时间 时间戳计算结果
     *
     * @return
     */
    public static long getTodayRestTime() {
        return getTodayEndDateTime() - getCurrentDateTime();
    }

    /**
     * 获取某个日期的 0点0分0秒 的时间戳
     *
     * @param date 指定的日期
     * @return
     */
    public static long getDateStartTime(Date date) {
        Timestamp time = new Timestamp(date.getTime());
        time.setNanos(0);
        time.setHours(0);
        time.setMinutes(0);
        time.setSeconds(0);

        return time.getTime();
    }
}
