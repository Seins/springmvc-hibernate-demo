package cm.exception;

import cm.model.WXErrorMsgModel;
import cm.util.ErrorMessageUtils;
import com.alibaba.fastjson.JSONObject;

/**
 * 类名：cm.exception.BussinessException
 * 创建者： CM .
 * 创建时间：2016/4/14
 */
public class BussinessException extends Exception {

    public BussinessException() {
        super();
    }

    public BussinessException(String message) {
        super(message);
    }

    public BussinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public BussinessException(Throwable cause) {
        super(cause);
    }

    protected BussinessException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public BussinessException(WXErrorMsgModel model) {
        super(model.toString());
    }

    /**
     * 根据微信错误消息代码获取对应的错误消息
     *
     * @param obj
     */
    public BussinessException(JSONObject obj) {
        super(ErrorMessageUtils.getErrorMessageContent(obj.getInteger("errcode")).toString());
    }

}
