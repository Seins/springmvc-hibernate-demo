package cm.entity;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by CM on 16/4/16.
 */
@Entity
@Table(name = "server_timeout_log", schema = "log_db", catalog = "")
public class ServerTimeoutLog {
    private long id;
    private ServerInfo serverInfo;
    private InterfaceInfo interfaceInfo;

    private Timestamp logTime;
    private String errorStack;
    private String errorParams;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @JoinColumn(name = "server_id", nullable = false)
    @ManyToOne(targetEntity = ServerInfo.class, fetch = FetchType.EAGER)
    public ServerInfo getServerInfo() {
        return serverInfo;
    }

    public void setServerInfo(ServerInfo serverInfo) {
        this.serverInfo = serverInfo;
    }

    @JoinColumn(name = "interface_id", nullable = false)
    @ManyToOne(targetEntity = InterfaceInfo.class, fetch = FetchType.EAGER)
    public InterfaceInfo getInterfaceInfo() {
        return interfaceInfo;
    }

    public void setInterfaceInfo(InterfaceInfo interfaceInfo) {
        this.interfaceInfo = interfaceInfo;
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
    @Column(name = "error_stack", nullable = true, length = -1)
    public String getErrorStack() {
        return errorStack;
    }

    public void setErrorStack(String errorStack) {
        this.errorStack = errorStack;
    }

    @Basic
    @Column(name = "error_params", nullable = true, length = 1000)
    public String getErrorParams() {
        return errorParams;
    }

    public void setErrorParams(String errorParams) {
        this.errorParams = errorParams;
    }


}
