package com.tencent.protocol.send_red_pack;

/**
 * Created by 蔻丁同学 on 2015/6/24.
 */
public class SendRedPackResData {
    private String return_code;
    private String return_msg;

    private String sign;
    private String result_code;
    private String err_code;
    private String err_code_des;

    private String mch_billno;
    private String mch_id;
    private String wxappid;
    private String re_openid;
    private String total_amount;
    private int send_time;
    private int send_listid;

    public SendRedPackResData() {
    }

    /**
     *
     * @param return_code 此字段是通信标识，非交易标识
     * @param return_msg 返回信息，如非空，为错误原因
     * @param sign 签名
     * @param result_code SUCCESS/FAIL 业务结果
     * @param err_code 错误代码
     * @param err_code_des 错误代码描述
     * @param mch_billno 商户订单号
     * @param mch_id 商户号
     * @param wxappid 公众账号appid
     * @param re_openid 用户openid
     * @param total_amount 付款金额
     * @param send_time 发放成功时间
     * @param send_listid 微信单号
     */
    public SendRedPackResData(String return_code, String return_msg, String sign, String result_code, String err_code, String err_code_des, String mch_billno, String mch_id, String wxappid, String re_openid, String total_amount, int send_time, int send_listid) {
        this.return_code = return_code;
        this.return_msg = return_msg;
        this.sign = sign;
        this.result_code = result_code;
        this.err_code = err_code;
        this.err_code_des = err_code_des;
        this.mch_billno = mch_billno;
        this.mch_id = mch_id;
        this.wxappid = wxappid;
        this.re_openid = re_openid;
        this.total_amount = total_amount;
        this.send_time = send_time;
        this.send_listid = send_listid;
    }

    @Override
    public String toString() {
        return "SendRedPackResData{" +
                "return_code='" + return_code + '\'' +
                ", return_msg='" + return_msg + '\'' +
                ", sign='" + sign + '\'' +
                ", result_code='" + result_code + '\'' +
                ", err_code='" + err_code + '\'' +
                ", err_code_des='" + err_code_des + '\'' +
                ", mch_billno='" + mch_billno + '\'' +
                ", mch_id='" + mch_id + '\'' +
                ", wxappid='" + wxappid + '\'' +
                ", re_openid='" + re_openid + '\'' +
                ", total_amount='" + total_amount + '\'' +
                ", send_time=" + send_time +
                ", send_listid=" + send_listid +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SendRedPackResData that = (SendRedPackResData) o;

        if (send_listid != that.send_listid) return false;
        if (send_time != that.send_time) return false;
        if (err_code != null ? !err_code.equals(that.err_code) : that.err_code != null) return false;
        if (err_code_des != null ? !err_code_des.equals(that.err_code_des) : that.err_code_des != null) return false;
        if (mch_billno != null ? !mch_billno.equals(that.mch_billno) : that.mch_billno != null) return false;
        if (mch_id != null ? !mch_id.equals(that.mch_id) : that.mch_id != null) return false;
        if (re_openid != null ? !re_openid.equals(that.re_openid) : that.re_openid != null) return false;
        if (result_code != null ? !result_code.equals(that.result_code) : that.result_code != null) return false;
        if (return_code != null ? !return_code.equals(that.return_code) : that.return_code != null) return false;
        if (return_msg != null ? !return_msg.equals(that.return_msg) : that.return_msg != null) return false;
        if (sign != null ? !sign.equals(that.sign) : that.sign != null) return false;
        if (total_amount != null ? !total_amount.equals(that.total_amount) : that.total_amount != null) return false;
        if (wxappid != null ? !wxappid.equals(that.wxappid) : that.wxappid != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = return_code != null ? return_code.hashCode() : 0;
        result = 31 * result + (return_msg != null ? return_msg.hashCode() : 0);
        result = 31 * result + (sign != null ? sign.hashCode() : 0);
        result = 31 * result + (result_code != null ? result_code.hashCode() : 0);
        result = 31 * result + (err_code != null ? err_code.hashCode() : 0);
        result = 31 * result + (err_code_des != null ? err_code_des.hashCode() : 0);
        result = 31 * result + (mch_billno != null ? mch_billno.hashCode() : 0);
        result = 31 * result + (mch_id != null ? mch_id.hashCode() : 0);
        result = 31 * result + (wxappid != null ? wxappid.hashCode() : 0);
        result = 31 * result + (re_openid != null ? re_openid.hashCode() : 0);
        result = 31 * result + (total_amount != null ? total_amount.hashCode() : 0);
        result = 31 * result + send_time;
        result = 31 * result + send_listid;
        return result;
    }

    public String getReturn_code() {
        return return_code;
    }

    public void setReturn_code(String return_code) {
        this.return_code = return_code;
    }

    public String getReturn_msg() {
        return return_msg;
    }

    public void setReturn_msg(String return_msg) {
        this.return_msg = return_msg;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getResult_code() {
        return result_code;
    }

    public void setResult_code(String result_code) {
        this.result_code = result_code;
    }

    public String getErr_code() {
        return err_code;
    }

    public void setErr_code(String err_code) {
        this.err_code = err_code;
    }

    public String getErr_code_des() {
        return err_code_des;
    }

    public void setErr_code_des(String err_code_des) {
        this.err_code_des = err_code_des;
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

    public String getWxappid() {
        return wxappid;
    }

    public void setWxappid(String wxappid) {
        this.wxappid = wxappid;
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

    public int getSend_time() {
        return send_time;
    }

    public void setSend_time(int send_time) {
        this.send_time = send_time;
    }

    public int getSend_listid() {
        return send_listid;
    }

    public void setSend_listid(int send_listid) {
        this.send_listid = send_listid;
    }
}
