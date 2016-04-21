package cm.entity;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * 类名：cm.entity.CacheDataChangeLog
 * 创建者： CM .
 * 创建时间：2016/4/21
 */
@Entity
@Table(name = "cache_data_change_log", schema = "", catalog = "log_db")
public class CacheDataChangeLog {
    private long id;
    private Timestamp logTime;
    private Long dataAmount;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, insertable = true, updatable = true)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "log_time", nullable = true, insertable = true, updatable = true)
    public Timestamp getLogTime() {
        return logTime;
    }

    public void setLogTime(Timestamp logTime) {
        this.logTime = logTime;
    }

    @Basic
    @Column(name = "data_amount", nullable = true, insertable = true, updatable = true)
    public Long getDataAmount() {
        return dataAmount;
    }

    public void setDataAmount(Long dataAmount) {
        this.dataAmount = dataAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CacheDataChangeLog that = (CacheDataChangeLog) o;

        if (id != that.id) return false;
        if (logTime != null ? !logTime.equals(that.logTime) : that.logTime != null) return false;
        if (dataAmount != null ? !dataAmount.equals(that.dataAmount) : that.dataAmount != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (logTime != null ? logTime.hashCode() : 0);
        result = 31 * result + (dataAmount != null ? dataAmount.hashCode() : 0);
        return result;
    }
}
