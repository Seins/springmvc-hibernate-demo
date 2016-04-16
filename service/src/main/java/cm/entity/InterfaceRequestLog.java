package cm.entity;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by CM on 16/4/17.
 */
@Entity
@Table(name = "interface_request_log", schema = "log_db", catalog = "")
public class InterfaceRequestLog {
    private long id;
    private Timestamp logTime;
    private Long requestAmount;
    private InterfaceInfo interfaceInfo;

    @ManyToOne(targetEntity = InterfaceInfo.class, fetch = FetchType.EAGER)
    public InterfaceInfo getInterfaceInfo() {
        return interfaceInfo;
    }

    public void setInterfaceInfo(InterfaceInfo interfaceInfo) {
        this.interfaceInfo = interfaceInfo;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "log_time", nullable = true)
    public Timestamp getLogTime() {
        return logTime;
    }

    public void setLogTime(Timestamp logTime) {
        this.logTime = logTime;
    }

    @Basic
    @Column(name = "request_amount", nullable = true)
    public Long getRequestAmount() {
        return requestAmount;
    }

    public void setRequestAmount(Long requestAmount) {
        this.requestAmount = requestAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InterfaceRequestLog that = (InterfaceRequestLog) o;

        if (id != that.id) return false;
        if (logTime != null ? !logTime.equals(that.logTime) : that.logTime != null) return false;
        if (requestAmount != null ? !requestAmount.equals(that.requestAmount) : that.requestAmount != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (logTime != null ? logTime.hashCode() : 0);
        result = 31 * result + (requestAmount != null ? requestAmount.hashCode() : 0);
        return result;
    }
}
