package cm.model;

import java.sql.Timestamp;

/**
 * class name： cm.model.DataChangeLogModel
 * author： CM
 * create time： 2016/4/21.
 * redis 数据条目统计情况数据类
 */
public class DataChangeLogModel {
    private Timestamp logTime; //日志记录时间
    private long dataAmount; //日志记录时redis的数据条目数

    public Timestamp getLogTime() {
        return logTime;
    }

    public void setLogTime(Timestamp logTime) {
        this.logTime = logTime;
    }

    public long getDataAmount() {
        return dataAmount;
    }

    public void setDataAmount(long dataAmount) {
        this.dataAmount = dataAmount;
    }
}
