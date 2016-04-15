package cm.entity;

import javax.persistence.*;

/**
 * 类名：cm.entity.ServerInfo
 * 创建者： CM .
 * 创建时间：2016/4/15
 */
@Entity
@Table(name = "server_info", schema = "", catalog = "log_db")
public class ServerInfo {
    private long id;
    private String realIp;
    private String realPort;
    private String serverName;

    @Id
    @Column(name = "id", nullable = false, insertable = true, updatable = true)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "real_ip", nullable = true, insertable = true, updatable = true, length = 40)
    public String getRealIp() {
        return realIp;
    }

    public void setRealIp(String realIp) {
        this.realIp = realIp;
    }

    @Basic
    @Column(name = "real_port", nullable = true, insertable = true, updatable = true, length = 20)
    public String getRealPort() {
        return realPort;
    }

    public void setRealPort(String realPort) {
        this.realPort = realPort;
    }

    @Basic
    @Column(name = "server_name", nullable = true, insertable = true, updatable = true, length = 100)
    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ServerInfo that = (ServerInfo) o;

        if (id != that.id) return false;
        if (realIp != null ? !realIp.equals(that.realIp) : that.realIp != null) return false;
        if (realPort != null ? !realPort.equals(that.realPort) : that.realPort != null) return false;
        if (serverName != null ? !serverName.equals(that.serverName) : that.serverName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (realIp != null ? realIp.hashCode() : 0);
        result = 31 * result + (realPort != null ? realPort.hashCode() : 0);
        result = 31 * result + (serverName != null ? serverName.hashCode() : 0);
        return result;
    }
}
