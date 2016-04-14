package cm.model.msg.push;

/**
 * Created by 蔻丁同学 on 2015/5/1.
 */
public class MpNews {
    private String media_id;

    public MpNews(String media_id) {
        this.media_id = media_id;
    }

    @Override
    public String toString() {
        return "MpNews{" +
                "media_id='" + media_id + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MpNews mpNews = (MpNews) o;

        if (media_id != null ? !media_id.equals(mpNews.media_id) : mpNews.media_id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return media_id != null ? media_id.hashCode() : 0;
    }

    public MpNews() {
    }

    public String getMedia_id() {
        return media_id;
    }

    public void setMedia_id(String media_id) {
        this.media_id = media_id;
    }
}
