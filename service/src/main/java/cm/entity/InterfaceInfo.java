package cm.entity;

import javax.persistence.*;

/**
 * Created by CM on 16/4/16.
 */
@Entity
@Table(name = "interface_info", schema = "log_db", catalog = "")
public class InterfaceInfo {
    private long id;
    private ServerInfo serverInfo;
    private String interfaceName;
    private String interfaceUri;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @JoinColumn(name = "server_id")
    @ManyToOne(targetEntity = ServerInfo.class, fetch = FetchType.EAGER)
    public ServerInfo getServerInfo() {
        return serverInfo;
    }

    public void setServerInfo(ServerInfo serverInfo) {
        this.serverInfo = serverInfo;
    }

    @Basic
    @Column(name = "interface_name", nullable = true, length = 200)
    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    @Basic
    @Column(name = "interface_uri", nullable = true, length = 500)
    public String getInterfaceUri() {
        return interfaceUri;
    }

    public void setInterfaceUri(String interfaceUri) {
        this.interfaceUri = interfaceUri;
    }

}
