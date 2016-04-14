package cm.model;

import java.util.Arrays;

/**
 * @修改人：CM
 * @修改时间： 2015/4/9 14:22.
 */
public class WXUserDetailInfoModel {
    private String openid;
    private String nickname;
    private String sex;
    private String province;
    private String city;
    private String country;
    private String headimgurl;
    private String[] privilege;
    private String unionid;
    private Integer suscribe;

    public WXUserDetailInfoModel() {
    }

    /**
     * @param openid     用户的唯一标识
     * @param nickname   用户昵称
     * @param sex        用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
     * @param province   用户个人资料填写的省份
     * @param city       普通用户个人资料填写的城市
     * @param country    国家，如中国为CN
     * @param headimgurl 用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空。若用户更换头像，原有头像URL将失效。
     * @param privilege  用户特权信息，json 数组，如微信沃卡用户为（chinaunicom）
     * @param unionid    只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段.
     */
    public WXUserDetailInfoModel(String openid, String nickname, String sex, String province, String city, String country, String headimgurl, String[] privilege, String unionid) {
        this.openid = openid;
        this.nickname = nickname;
        this.sex = sex;
        this.province = province;
        this.city = city;
        this.country = country;
        this.headimgurl = headimgurl;
        this.privilege = privilege;
        this.unionid = unionid;
    }

    public Integer getSuscribe() {
        return suscribe;
    }

    public void setSuscribe(Integer suscribe) {
        this.suscribe = suscribe;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }

    public String[] getPrivilege() {
        return privilege;
    }

    public void setPrivilege(String[] privilege) {
        this.privilege = privilege;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }


    @Override
    public String toString() {
        return "WXUserDetailInfoModel{" +
                "openid='" + openid + '\'' +
                ", nickname='" + nickname + '\'' +
                ", sex='" + sex + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", headimgurl='" + headimgurl + '\'' +
                ", privilege=" + Arrays.toString(privilege) +
                ", unionid='" + unionid + '\'' +
                ", suscribe=" + suscribe +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WXUserDetailInfoModel that = (WXUserDetailInfoModel) o;

        if (city != null ? !city.equals(that.city) : that.city != null) return false;
        if (country != null ? !country.equals(that.country) : that.country != null) return false;
        if (headimgurl != null ? !headimgurl.equals(that.headimgurl) : that.headimgurl != null) return false;
        if (nickname != null ? !nickname.equals(that.nickname) : that.nickname != null) return false;
        if (openid != null ? !openid.equals(that.openid) : that.openid != null) return false;
        if (!Arrays.equals(privilege, that.privilege)) return false;
        if (province != null ? !province.equals(that.province) : that.province != null) return false;
        if (sex != null ? !sex.equals(that.sex) : that.sex != null) return false;
        if (suscribe != null ? !suscribe.equals(that.suscribe) : that.suscribe != null) return false;
        if (unionid != null ? !unionid.equals(that.unionid) : that.unionid != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = openid != null ? openid.hashCode() : 0;
        result = 31 * result + (nickname != null ? nickname.hashCode() : 0);
        result = 31 * result + (sex != null ? sex.hashCode() : 0);
        result = 31 * result + (province != null ? province.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (headimgurl != null ? headimgurl.hashCode() : 0);
        result = 31 * result + (privilege != null ? Arrays.hashCode(privilege) : 0);
        result = 31 * result + (unionid != null ? unionid.hashCode() : 0);
        result = 31 * result + (suscribe != null ? suscribe.hashCode() : 0);
        return result;
    }
}
