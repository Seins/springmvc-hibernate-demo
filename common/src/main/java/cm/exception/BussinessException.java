package cm.exception;

import cm.model.WXErrorMsgModel;
import cm.util.ErrorMessageUtils;
import com.alibaba.fastjson.JSONObject;

/**
 * ������cm.exception.BussinessException
 * �����ߣ� CM .
 * ����ʱ�䣺2016/4/14
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
     * ����΢�Ŵ�����Ϣ�����ȡ��Ӧ�Ĵ�����Ϣ
     *
     * @param obj
     */
    public BussinessException(JSONObject obj) {
        super(ErrorMessageUtils.getErrorMessageContent(obj.getInteger("errcode")).toString());
    }

}
