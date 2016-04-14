package cm.model.msg.push;

/**
 * Created by 蔻丁同学 on 2015/5/1.
 */
public class Filter {
    private boolean is_to_all ;
    private Integer group_id;

    public Filter() {
    }



    @Override
    public String toString() {
        return "Filter{" +
                "is_to_all=" + is_to_all +
                ", group_id=" + group_id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Filter filter = (Filter) o;

        if (is_to_all != filter.is_to_all) return false;
        if (group_id != null ? !group_id.equals(filter.group_id) : filter.group_id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (is_to_all ? 1 : 0);
        result = 31 * result + (group_id != null ? group_id.hashCode() : 0);
        return result;
    }

    public boolean isIs_to_all() {
        return is_to_all;
    }

    public void setIs_to_all(boolean is_to_all) {
        this.is_to_all = is_to_all;
    }

    public Integer getGroup_id() {
        return group_id;
    }

    public void setGroup_id(Integer group_id) {
        this.group_id = group_id;
    }
}
