package cm.model;

/**
 * 类名：fm.model.LongToShortMsgModel
 * 创建者： CM .
 * 创建时间：2016/3/28
 */
public class LongToShortMsgModel extends WXErrorMsgModel {
    private String short_url;


    public String getShort_url() {
        return short_url;
    }

    public void setShort_url(String short_url) {
        this.short_url = short_url;
    }
}
