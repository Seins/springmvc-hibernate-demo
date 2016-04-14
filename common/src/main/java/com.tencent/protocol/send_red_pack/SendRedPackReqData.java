package com.tencent.protocol.send_red_pack;

import com.tencent.common.Configure;
import com.tencent.common.RandomStringGenerator;
import com.tencent.common.Signature;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 蔻丁同学 on 2015/6/23.
 */
public class SendRedPackReqData {

    private String nonce_str;
    private String sign;
    private String mch_billno;
    private String mch_id;
    private String sub_mch_id;
    private String wxappid;
    private String nick_name;
    private String send_name;
    private String re_openid;
    private String total_amount;
    private String min_value;
    private String max_value;
    private String total_num;
    private String wishing;
    private String client_ip;
    private String act_name;
    private String remark;
    private String logo_imgurl;
    private String share_content;
    private String share_url;
    private String share_imgurl;

    public SendRedPackReqData() {
    }

    /**
     *
     * @param mch_billno 商户订单号（每个订单号必须唯一） 组成： mch_id+yyyymmdd+10位一天内不能重复的数字。  接口根据商户订单号支持重入， 如出现超时可再调用。
     * @param nick_name 提供方名称
     * @param send_name 红包发送者名称
     * @param re_openid 接受收红包的用户 用户在wxappid下的openid
     * @param total_amount 付款金额，单位分
     * @param min_value  最小红包金额，单位分
     * @param max_value 最大红包金额，单位分（ 最小金额等于最大金额： min_value=max_value =total_amount）
     * @param total_num 红包发放总人数 total_num=1
     * @param wishing 红包祝福语
     * @param client_ip 调用接口的机器Ip地址
     * @param act_name 活动名称
     * @param remark 备注信息
     * @param logo_imgurl  商户logo的url
     * @param share_content 分享文案
     * @param share_url 分享链接
     * @param share_imgurl 分享的图片url
     */
    public SendRedPackReqData(String mch_billno, String nick_name, String send_name, String re_openid, String total_amount, String min_value, String max_value, String total_num, String wishing, String client_ip, String act_name, String remark, String logo_imgurl, String share_content, String share_url, String share_imgurl) {
        this.mch_billno = mch_billno;
        this.nick_name = nick_name;
        this.send_name = send_name;
        this.re_openid = re_openid;
        this.total_amount = total_amount;
        this.min_value = min_value;
        this.max_value = max_value;
        this.total_num = total_num;
        this.wishing = wishing;
        this.client_ip = client_ip;
        this.act_name = act_name;
        this.remark = remark;
//        this.logo_imgurl = logo_imgurl;
//        this.share_content = share_content;
//        this.share_url = share_url;
//        this.share_imgurl = share_imgurl;
        setMch_id(Configure.getMchid());
        setSub_mch_id(null);
        setWxappid(Configure.getAppid());
        setNonce_str(RandomStringGenerator.getRandomStringByLength(32));
        String sign= Signature.getSign(toMap());
        setSign(sign);
    }

    public String getNonce_str() {
        return nonce_str;
    }

    public void setNonce_str(String nonce_str) {
        this.nonce_str = nonce_str;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getMch_billno() {
        return mch_billno;
    }

    public void setMch_billno(String mch_billno) {
        this.mch_billno = mch_billno;
    }

    public String getMch_id() {
        return mch_id;
    }

    public void setMch_id(String mch_id) {
        this.mch_id = mch_id;
    }

    public String getSub_mch_id() {
        return sub_mch_id;
    }

    public void setSub_mch_id(String sub_mch_id) {
        this.sub_mch_id = sub_mch_id;
    }

    public String getWxappid() {
        return wxappid;
    }

    public void setWxappid(String wxappid) {
        this.wxappid = wxappid;
    }

    public String getNick_name() {
        return nick_name;
    }

    public void setNick_name(String nick_name) {
        this.nick_name = nick_name;
    }

    public String getSend_name() {
        return send_name;
    }

    public void setSend_name(String send_name) {
        this.send_name = send_name;
    }

    public String getRe_openid() {
        return re_openid;
    }

    public void setRe_openid(String re_openid) {
        this.re_openid = re_openid;
    }

    public String getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(String total_amount) {
        this.total_amount = total_amount;
    }

    public String getMin_value() {
        return min_value;
    }

    public void setMin_value(String min_value) {
        this.min_value = min_value;
    }

    public String getMax_value() {
        return max_value;
    }

    public void setMax_value(String max_value) {
        this.max_value = max_value;
    }

    public String getTotal_num() {
        return total_num;
    }

    public void setTotal_num(String total_num) {
        this.total_num = total_num;
    }

    public String getWishing() {
        return wishing;
    }

    public void setWishing(String wishing) {
        this.wishing = wishing;
    }

    public String getClient_ip() {
        return client_ip;
    }

    public void setClient_ip(String client_ip) {
        this.client_ip = client_ip;
    }

    public String getAct_name() {
        return act_name;
    }

    public void setAct_name(String act_name) {
        this.act_name = act_name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getLogo_imgurl() {
        return logo_imgurl;
    }

    public void setLogo_imgurl(String logo_imgurl) {
        this.logo_imgurl = logo_imgurl;
    }

    public String getShare_content() {
        return share_content;
    }

    public void setShare_content(String share_content) {
        this.share_content = share_content;
    }

    public String getShare_url() {
        return share_url;
    }

    public void setShare_url(String share_url) {
        this.share_url = share_url;
    }

    public String getShare_imgurl() {
        return share_imgurl;
    }

    public void setShare_imgurl(String share_imgurl) {
        this.share_imgurl = share_imgurl;
    }

    @Override
    public String toString() {
        return "SendRedPackReqData{" +
                "nonce_str='" + nonce_str + '\'' +
                ", sign='" + sign + '\'' +
                ", mch_billno='" + mch_billno + '\'' +
                ", mch_id='" + mch_id + '\'' +
                ", sub_mch_id='" + sub_mch_id + '\'' +
                ", wxappid='" + wxappid + '\'' +
                ", nick_name='" + nick_name + '\'' +
                ", send_name='" + send_name + '\'' +
                ", re_openid='" + re_openid + '\'' +
                ", total_amount='" + total_amount + '\'' +
                ", min_value='" + min_value + '\'' +
                ", max_value='" + max_value + '\'' +
                ", total_num='" + total_num + '\'' +
                ", wishing='" + wishing + '\'' +
                ", client_ip='" + client_ip + '\'' +
                ", act_name='" + act_name + '\'' +
                ", remark='" + remark + '\'' +
                ", logo_imgurl='" + logo_imgurl + '\'' +
                ", share_content='" + share_content + '\'' +
                ", share_url='" + share_url + '\'' +
                ", share_imgurl='" + share_imgurl + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SendRedPackReqData that = (SendRedPackReqData) o;

        if (act_name != null ? !act_name.equals(that.act_name) : that.act_name != null) return false;
        if (client_ip != null ? !client_ip.equals(that.client_ip) : that.client_ip != null) return false;
        if (logo_imgurl != null ? !logo_imgurl.equals(that.logo_imgurl) : that.logo_imgurl != null) return false;
        if (max_value != null ? !max_value.equals(that.max_value) : that.max_value != null) return false;
        if (mch_billno != null ? !mch_billno.equals(that.mch_billno) : that.mch_billno != null) return false;
        if (mch_id != null ? !mch_id.equals(that.mch_id) : that.mch_id != null) return false;
        if (min_value != null ? !min_value.equals(that.min_value) : that.min_value != null) return false;
        if (nick_name != null ? !nick_name.equals(that.nick_name) : that.nick_name != null) return false;
        if (nonce_str != null ? !nonce_str.equals(that.nonce_str) : that.nonce_str != null) return false;
        if (re_openid != null ? !re_openid.equals(that.re_openid) : that.re_openid != null) return false;
        if (remark != null ? !remark.equals(that.remark) : that.remark != null) return false;
        if (send_name != null ? !send_name.equals(that.send_name) : that.send_name != null) return false;
        if (share_content != null ? !share_content.equals(that.share_content) : that.share_content != null)
            return false;
        if (share_imgurl != null ? !share_imgurl.equals(that.share_imgurl) : that.share_imgurl != null) return false;
        if (share_url != null ? !share_url.equals(that.share_url) : that.share_url != null) return false;
        if (sign != null ? !sign.equals(that.sign) : that.sign != null) return false;
        if (sub_mch_id != null ? !sub_mch_id.equals(that.sub_mch_id) : that.sub_mch_id != null) return false;
        if (total_amount != null ? !total_amount.equals(that.total_amount) : that.total_amount != null) return false;
        if (total_num != null ? !total_num.equals(that.total_num) : that.total_num != null) return false;
        if (wishing != null ? !wishing.equals(that.wishing) : that.wishing != null) return false;
        if (wxappid != null ? !wxappid.equals(that.wxappid) : that.wxappid != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = nonce_str != null ? nonce_str.hashCode() : 0;
        result = 31 * result + (sign != null ? sign.hashCode() : 0);
        result = 31 * result + (mch_billno != null ? mch_billno.hashCode() : 0);
        result = 31 * result + (mch_id != null ? mch_id.hashCode() : 0);
        result = 31 * result + (sub_mch_id != null ? sub_mch_id.hashCode() : 0);
        result = 31 * result + (wxappid != null ? wxappid.hashCode() : 0);
        result = 31 * result + (nick_name != null ? nick_name.hashCode() : 0);
        result = 31 * result + (send_name != null ? send_name.hashCode() : 0);
        result = 31 * result + (re_openid != null ? re_openid.hashCode() : 0);
        result = 31 * result + (total_amount != null ? total_amount.hashCode() : 0);
        result = 31 * result + (min_value != null ? min_value.hashCode() : 0);
        result = 31 * result + (max_value != null ? max_value.hashCode() : 0);
        result = 31 * result + (total_num != null ? total_num.hashCode() : 0);
        result = 31 * result + (wishing != null ? wishing.hashCode() : 0);
        result = 31 * result + (client_ip != null ? client_ip.hashCode() : 0);
        result = 31 * result + (act_name != null ? act_name.hashCode() : 0);
        result = 31 * result + (remark != null ? remark.hashCode() : 0);
        result = 31 * result + (logo_imgurl != null ? logo_imgurl.hashCode() : 0);
        result = 31 * result + (share_content != null ? share_content.hashCode() : 0);
        result = 31 * result + (share_url != null ? share_url.hashCode() : 0);
        result = 31 * result + (share_imgurl != null ? share_imgurl.hashCode() : 0);
        return result;
    }

    public Map<String,Object> toMap(){
        Map<String,Object> map = new HashMap<String, Object>();
        Field[] fields = this.getClass().getDeclaredFields();
        for (Field field : fields) {
            Object obj;
            try {
                obj = field.get(this);
                if(obj!=null){
                    map.put(field.getName(), obj);
                }
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return map;
    }
}
