package cm.model;

/**
 * @修改人：CM
 * @修改时间： 2015/4/9 12:27.
 */
public class WXUserBaseInfoModel {
    private String access_token;
    private String expires_in;
    private String refresh_token;
    private String openid;
    private String scope;
    private String unionId;

    public WXUserBaseInfoModel() {
    }

    /**
     * @param access_token  网页授权接口调用凭证,注意：此access_token与基础支持的access_token不同
     * @param expires_in    access_token接口调用凭证超时时间，单位（秒）
     * @param refresh_token 用户刷新access_token
     * @param openid        用户唯一标识，请注意，在未关注公众号时，用户访问公众号的网页，也会产生一个用户和公众号唯一的OpenID
     * @param scope         用户授权的作用域，使用逗号（,）分隔
     * @param unionId       只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段。
     */
    public WXUserBaseInfoModel(String access_token, String expires_in, String refresh_token, String openid, String scope, String unionId) {
        this.access_token = access_token;
        this.expires_in = expires_in;
        this.refresh_token = refresh_token;
        this.openid = openid;
        this.scope = scope;
        this.unionId = unionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WXUserBaseInfoModel that = (WXUserBaseInfoModel) o;

        if (access_token != null ? !access_token.equals(that.access_token) : that.access_token != null) return false;
        if (expires_in != null ? !expires_in.equals(that.expires_in) : that.expires_in != null) return false;
        if (openid != null ? !openid.equals(that.openid) : that.openid != null) return false;
        if (refresh_token != null ? !refresh_token.equals(that.refresh_token) : that.refresh_token != null)
            return false;
        if (scope != null ? !scope.equals(that.scope) : that.scope != null) return false;
        if (unionId != null ? !unionId.equals(that.unionId) : that.unionId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = access_token != null ? access_token.hashCode() : 0;
        result = 31 * result + (expires_in != null ? expires_in.hashCode() : 0);
        result = 31 * result + (refresh_token != null ? refresh_token.hashCode() : 0);
        result = 31 * result + (openid != null ? openid.hashCode() : 0);
        result = 31 * result + (scope != null ? scope.hashCode() : 0);
        result = 31 * result + (unionId != null ? unionId.hashCode() : 0);
        return result;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(String expires_in) {
        this.expires_in = expires_in;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getUnionId() {
        return unionId;
    }

    public void setUnionId(String unionId) {
        this.unionId = unionId;
    }

    @Override
    public String toString() {
        return "WXUserBaseInfoModel{" +
                "access_token='" + access_token + '\'' +
                ", expires_in='" + expires_in + '\'' +
                ", refresh_token='" + refresh_token + '\'' +
                ", openid='" + openid + '\'' +
                ", scope='" + scope + '\'' +
                ", unionId='" + unionId + '\'' +
                '}';
    }


}
