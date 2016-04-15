package cm.entity;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * 类名：cm.entity.ServerConcurrentLog
 * 创建者： CM .
 * 创建时间：2016/4/15
 */
@Entity
@Table(name = "server_concurrent_log", schema = "", catalog = "log_db")
public class ServerConcurrentLog {
    private long id;
    private Timestamp logTime;
    private ServerInfo serverInfo;
    private Long concurrentAmount;
    private Long serverId;

    @Basic
    @Column(name = "server_id", nullable = true, insertable = true, updatable = true)
    public Long getServerId() {
        return serverId;
    }

    public void setServerId(Long serverId) {
        this.serverId = serverId;
    }

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
