package cm.model.menu;

import java.util.Arrays;

/**
 * Created by 蔻丁同学 on 2015/6/24.
 */
public class CreateMenuReqData {
    MenuButtonModel[] button;

    public CreateMenuReqData() {
    }

    public CreateMenuReqData(MenuButtonModel[] button) {
        this.button = button;
    }

    @Override
    public String toString() {
        return "CreateMenuReqData{" +
                "button=" + Arrays.toString(button) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CreateMenuReqData that = (CreateMenuReqData) o;

        if (!Arrays.equals(button, that.button)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return button != null ? Arrays.hashCode(button) : 0;
    }

    public MenuButtonModel[] getButton() {
        return button;
    }

    public void setButton(MenuButtonModel[] button) {
        this.button = button;
    }
}
