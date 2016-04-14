package cm.model.menu;

import java.util.Arrays;

/**
 * Created by 蔻丁同学 on 2015/6/24.
 */
public class MenuButtonModel {
    private MenuButtonModel[] sub_button;
    private String type;
    private String name;
    private String key;
    private String url;
    private String media_id;

    public MenuButtonModel() {
    }

    /**
     *
     * @param sub_button 二级菜单数组，个数应为1~5个 否
     * @param type  菜单的响应动作类型 是
     * @param name 菜单标题，不超过16个字节，子菜单不超过40个字节 是
     * @param key 菜单KEY值，用于消息接口推送，不超过128字节 click等点击类型必须
     * @param url 网页链接，用户点击菜单可打开链接，不超过256字节 view类型必须
     * @param media_id 调用新增永久素材接口返回的合法media_id media_id类型和view_limited类型必须
     */
    public MenuButtonModel(MenuButtonModel[] sub_button, String type, String name, String key, String url, String media_id) {
        this.sub_button = sub_button;
        this.type = type;
        this.name = name;
        this.key = key;
        this.url = url;
        this.media_id = media_id;
    }

    @Override
    public String toString() {
        return "MenuButtonModel{" +
                "sub_button=" + Arrays.toString(sub_button) +
                ", type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", key='" + key + '\'' +
                ", url='" + url + '\'' +
                ", media_id='" + media_id + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MenuButtonModel that = (MenuButtonModel) o;

        if (key != null ? !key.equals(that.key) : that.key != null) return false;
        if (media_id != null ? !media_id.equals(that.media_id) : that.media_id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (!Arrays.equals(sub_button, that.sub_button)) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        if (url != null ? !url.equals(that.url) : that.url != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = sub_button != null ? Arrays.hashCode(sub_button) : 0;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (key != null ? key.hashCode() : 0);
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + (media_id != null ? media_id.hashCode() : 0);
        return result;
    }

    public MenuButtonModel[] getSub_button() {
        return sub_button;
    }

    public void setSub_button(MenuButtonModel[] sub_button) {
        this.sub_button = sub_button;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMedia_id() {
        return media_id;
    }

    public void setMedia_id(String media_id) {
        this.media_id = media_id;
    }
}
