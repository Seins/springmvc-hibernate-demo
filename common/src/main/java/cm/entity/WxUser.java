package cm.entity;

import javax.persistence.*;

/**
 * 类名：fm.entity.WxUser
 * 创建者： CM .
 * 创建时间：2016/3/10
 */
@Entity
@Table(name = "wx_user", schema = "", catalog = "fm_db")
public class WxUser {
    private long id;
    private String nickname;
    private String headimgurl;
    private String country;
    private String city;
    private String province;
    private String remark;
    private Integer subscribed;
    private String telephone;
    private String privilege;
    private String openid;
    private long publicNumberId;

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
    @Column(name = "nickname", nullable = true, insertable = true, updatable = true, length = 40)
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Basic
    @Column(name = "headimgurl", nullable = true, insertable = true, updatable = true, length = 500)
    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }

    @Basic
    @Column(name = "country", nullable = true, insertable = true, updatable = true, length = 40)
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Basic
    @Column(name = "city", nullable = true, insertable = true, updatable = true, length = 40)
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Basic
    @Column(name = "province", nullable = true, insertable = true, updatable = true, length = 40)
    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    @Basic
    @Column(name = "remark", nullable = true, insertable = true, updatable = true, length = 200)
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Basic
    @Column(name = "subscribed", nullable = true, insertable = true, updatable = true)
    public Integer getSubscribed() {
        return subscribed;
    }

    public void setSubscribed(Integer subscribed) {
        this.subscribed = subscribed;
    }

    @Basic
    @Column(name = "telephone", nullable = true, insertable = true, updatable = true, length = 20)
    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @Basic
    @Column(name = "privilege", nullable = true, insertable = true, updatable = true, length = 255)
    public String getPrivilege() {
        return privilege;
    }

    public void setPrivilege(String privilege) {
        this.privilege = privilege;
    }

    @Basic
    @Column(name = "openid", nullable = true, insertable = true, updatable = true, length = 100)
    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    @Basic
    @Column(name = "public_number_id", nullable = false, insertable = true, updatable = true)
    public long getPublicNumberId() {
        return publicNumberId;
    }

    public void setPublicNumberId(long publicNumberId) {
        this.publicNumberId = publicNumberId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WxUser user = (WxUser) o;

        if (id != user.id) return false;
        if (publicNumberId != user.publicNumberId) return false;
        if (nickname != null ? !nickname.equals(user.nickname) : user.nickname != null) return false;
        if (headimgurl != null ? !headimgurl.equals(user.headimgurl) : user.headimgurl != null) return false;
        if (country != null ? !country.equals(user.country) : user.country != null) return false;
        if (city != null ? !city.equals(user.city) : user.city != null) return false;
        if (province != null ? !province.equals(user.province) : user.province != null) return false;
        if (remark != null ? !remark.equals(user.remark) : user.remark != null) return false;
        if (subscribed != null ? !subscribed.equals(user.subscribed) : user.subscribed != null) return false;
        if (telephone != null ? !telephone.equals(user.telephone) : user.telephone != null) return false;
        if (privilege != null ? !privilege.equals(user.privilege) : user.privilege != null) return false;
        if (openid != null ? !openid.equals(user.openid) : user.openid != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (nickname != null ? nickname.hashCode() : 0);
        result = 31 * result + (headimgurl != null ? headimgurl.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (province != null ? province.hashCode() : 0);
        result = 31 * result + (remark != null ? remark.hashCode() : 0);
        result = 31 * result + (subscribed != null ? subscribed.hashCode() : 0);
        result = 31 * result + (telephone != null ? telephone.hashCode() : 0);
        result = 31 * result + (privilege != null ? privilege.hashCode() : 0);
        result = 31 * result + (openid != null ? openid.hashCode() : 0);
        result = 31 * result + (int) (publicNumberId ^ (publicNumberId >>> 32));
        return result;
    }
}
