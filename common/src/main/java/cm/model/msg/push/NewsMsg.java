package cm.model.msg.push;

/**
 * Created by 蔻丁同学 on 2015/5/1.
 */
public class NewsMsg {
    private Filter filter;
    private MpNews mpnews;
    private String msgtype;

    public NewsMsg() {
    }

    public NewsMsg(Filter filter, MpNews mpnews, String msgtype) {
        this.filter = filter;
        this.mpnews = mpnews;
        this.msgtype = msgtype;
    }

    @Override
    public String toString() {
        return "NewsMsg{" +
                "filter=" + filter +
                ", mpnews=" + mpnews +
                ", msgtype='" + msgtype + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NewsMsg newsMsg = (NewsMsg) o;

        if (filter != null ? !filter.equals(newsMsg.filter) : newsMsg.filter != null) return false;
        if (mpnews != null ? !mpnews.equals(newsMsg.mpnews) : newsMsg.mpnews != null) return false;
        if (msgtype != null ? !msgtype.equals(newsMsg.msgtype) : newsMsg.msgtype != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = filter != null ? filter.hashCode() : 0;
        result = 31 * result + (mpnews != null ? mpnews.hashCode() : 0);
        result = 31 * result + (msgtype != null ? msgtype.hashCode() : 0);
        return result;
    }

    public Filter getFilter() {
        return filter;
    }

    public void setFilter(Filter filter) {
        this.filter = filter;
    }

    public MpNews getMpnews() {
        return mpnews;
    }

    public void setMpnews(MpNews mpnews) {
        this.mpnews = mpnews;
    }

    public String getMsgtype() {
        return msgtype;
    }

    public void setMsgtype(String msgtype) {
        this.msgtype = msgtype;
    }
}
