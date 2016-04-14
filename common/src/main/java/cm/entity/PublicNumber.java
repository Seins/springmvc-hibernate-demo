package cm.entity;

import javax.persistence.*;

/**
 * 类名：fm.entity.PublicNumber
 * 创建者： CM .
 * 创建时间：2016/3/10
 */
@Entity
@Table(name = "public_number", schema = "", catalog = "fm_db")
public class PublicNumber {
    private long id;
    private String appid;
    private String appsecret;
    private String token;
    private String uuid;
    private String status;
    private String roles;
    private String orgnizationName;

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
    @Column(name = "appid", nullable = true, insertable = true, updatable = true, length = 100)
    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    @Basic
    @Column(name = "appsecret", nullable = true, insertable = true, updatable = true, length = 200)
    public String getAppsecret() {
        return appsecret;
    }

    public void setAppsecret(String appsecret) {
        this.appsecret = appsecret;
    }

    @Basic
    @Column(name = "token", nullable = true, insertable = true, updatable = true, length = 100)
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Basic
    @Column(name = "uuid", nullable = true, insertable = true, updatable = true, length = 50)
    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @Basic
    @Column(name = "status", nullable = true, insertable = true, updatable = true, length = 60)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Basic
    @Column(name = "roles", nullable = true, insertable = true, updatable = true, length = 100)
    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    @Basic
    @Column(name = "orgnization_name", nullable = true, insertable = true, updatable = true, length = 100)
    public String getOrgnizationName() {
        return orgnizationName;
    }

    public void setOrgnizationName(String orgnizationName) {
        this.orgnizationName = orgnizationName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PublicNumber that = (PublicNumber) o;

        if (id != that.id) return false;
        if (appid != null ? !appid.equals(that.appid) : that.appid != null) return false;
        if (appsecret != null ? !appsecret.equals(that.appsecret) : that.appsecret != null) return false;
        if (token != null ? !token.equals(that.token) : that.token != null) return false;
        if (uuid != null ? !uuid.equals(that.uuid) : that.uuid != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (roles != null ? !roles.equals(that.roles) : that.roles != null) return false;
        if (orgnizationName != null ? !orgnizationName.equals(that.orgnizationName) : that.orgnizationName != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (appid != null ? appid.hashCode() : 0);
        result = 31 * result + (appsecret != null ? appsecret.hashCode() : 0);
        result = 31 * result + (token != null ? token.hashCode() : 0);
        result = 31 * result + (uuid != null ? uuid.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (roles != null ? roles.hashCode() : 0);
        result = 31 * result + (orgnizationName != null ? orgnizationName.hashCode() : 0);
        return result;
    }
}
