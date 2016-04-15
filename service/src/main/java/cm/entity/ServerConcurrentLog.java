package cm.entity;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * ������cm.entity.ServerConcurrentLog
 * �����ߣ� CM .
 * ����ʱ�䣺2016/4/15
 */
@Entity
@Table(name = "server_concurrent_log", schema = "", catalog = "log_db")
public class ServerConcurrentLog {
    private long id;
    private Timestamp logTime;
    private ServerInfo serverInfo;
    private Long concurrentAmount;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = true, insertable = true, updatable = true)
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

    @JoinColumn(name = "server_id",nullable = false)
    @ManyToOne(targetEntity = ServerInfo.class, fetch = FetchType.EAGER)
    public ServerInfo getServerInfo() {
        return serverInfo;
    }

    public void setServerInfo(ServerInfo serverInfo) {
        this.serverInfo = serverInfo;
    }

    @Basic
    @Column(name = "concurrent_amount", nullable = true, insertable = true, updatable = true)
    public Long getConcurrentAmount() {
        return concurrentAmount;
    }

    public void setConcurrentAmount(Long concurrentAmount) {
        this.concurrentAmount = concurrentAmount;
    }


}
