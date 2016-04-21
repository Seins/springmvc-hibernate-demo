package cm.redis;

import cm.util.DateUtil;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * redis key 甯搁噺绫?
 */
public final class RedisKeyCenter {

    public static final String TEST_DATA_CHANGE_LOG = "redis:test:data:change:log"; //数据变化情况记录

    public static final String TEST_DATA_WRITE_LOG = "redis:test:data:write:log"; //数据写入情况记录

    public static final String TEST_DATA_MODEL_LOG = "model:model:data:log"; //测试数据模型1

    public static final String TEST_DATA_MODEL2_LOG = "model:model:data2:log"; //测试数据模型2

    public static final String REQUEST_LOG = "redis:request:data:log"; // 接口请求日志 key

    /**
     * 获取今日的请求日志记录
     *
     * @return
     */
    public static final String getTodayRequestLogKey() {
        return REQUEST_LOG + ":" + DateUtil.getTodayStartDateTime();
    }

    /**
     * 鑾峰彇redis鏁版嵁鏉＄洰鏁板湪涓嶅悓鏃堕棿缁熻鐨勬暟鎹褰?
     * 鏍规嵁姣忓ぉ鐨勬椂闂存埑鐢熸垚key锛屼繚璇佹瘡涓?澶╃殑鏁版嵁鍒嗗紑
     *
     * @return
     */
    public static String getTodayDataChangeLogKey() {
        return TEST_DATA_CHANGE_LOG + ":" + DateUtil.getTodayStartDateTime();
    }

    /**
     * 鑾峰彇鎸囧畾鏃ユ湡redis鏁版嵁鏉＄洰鏁板湪涓嶅悓鏃堕棿缁熻鐨勬暟鎹褰?
     *
     * @return
     */
    public static String getSomeDateChangeLogKey(Date date) {
        return TEST_DATA_CHANGE_LOG + ":" + DateUtil.getDateStartTime(date);
    }

    /**
     * 鑾峰彇redis鏁版嵁鍐欏叆鎯呭喌鍦ㄤ笉鍚屾椂闂寸粺璁＄殑鏁版嵁璁板綍
     * 鏍规嵁姣忓ぉ鐨勬椂闂存埑鐢熸垚key锛屼繚璇佹瘡涓?澶╃殑鏁版嵁鍒嗗紑
     *
     * @return
     */
    public static String getTodayDataWriteLogKey() {
        return TEST_DATA_WRITE_LOG + ":" + DateUtil.getTodayStartDateTime();
    }

    /**
     * 鑾峰彇鎸囧畾鏃ユ湡redis鏁版嵁鍐欏叆鎯呭喌鍦ㄤ笉鍚屾椂闂寸粺璁＄殑鏁版嵁璁板綍
     *
     * @return
     */
    public static String getTodayDataWriteLogKey(Date date) {
        return TEST_DATA_WRITE_LOG + ":" + +DateUtil.getDateStartTime(date);
    }

}
