package cm.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.util.Map;

/**
 * Created by CM on 2015/4/30.
 */
public class CommonUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(CommonUtils.class);

    /**
     * 判空函数
     *
     * @param obj
     * @return
     */
    public static boolean isEmpty(Object obj) {
        if (obj == null)
            return true;

        if (obj instanceof CharSequence)
            return ((CharSequence) obj).length() == 0;

        if (obj instanceof Collection)
            return ((Collection) obj).isEmpty();

        if (obj instanceof Map)
            return ((Map) obj).isEmpty();

        if (obj instanceof Object[]) {
            Object[] object = (Object[]) obj;
            if (object.length == 0) {
                return true;
            }
            boolean empty = true;
            for (int i = 0; i < object.length; i++) {
                if (!isEmpty(object[i])) {
                    empty = false;
                    break;
                }
            }
            return empty;
        }

        return false;
    }

    /**
     * 对象数组中是否有空对象
     *
     * @param objects
     * @return
     */
    public static boolean isEmpty(Object... objects) {
        for (Object o : objects) {
            if (isEmpty(o)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 构建带参数的uri
     *
     * @param uri
     * @param paramStr
     * @return
     */
    public static String uriBuilder(String uri, String paramStr) {
        final StringBuffer buffer = new StringBuffer();
        buffer.append(uri);
        if (uri.contains("?")) {
            buffer.append("&").append(paramStr);
        } else {
            buffer.append("?").append(paramStr);
        }
        LOGGER.info("uri build :{}", buffer.toString());
        return buffer.toString();
    }

    /**
     * 根据要访问的URI构建对应的完整网络地址
     *
     * @param request
     * @param response
     * @param uri
     * @return
     */
    public static String getServiceUrl(HttpServletRequest request, HttpServletResponse response, String uri) {
        final StringBuffer buffer = new StringBuffer();
        buffer.append(request.isSecure() ? "https://" : "http://");
        buffer.append(request.getServerName());
        buffer.append(uri);
        LOGGER.info("serviceUrl before encode:{}", buffer.toString());
        return response.encodeURL(buffer.toString());
    }


    /**
     * 获取当前页面完整链接
     *
     * @param request
     * @return
     */
    public static String fullUrlBuild(HttpServletRequest request) {
        String contextPath = request.getContextPath();
        String uri = request.getRequestURI();
        StringBuffer buffer = new StringBuffer();
        buffer.append(request.getScheme()).append("://").append(request.getServerName())
//                .append(":").append(request.getServerPort())
                .append(contextPath).append(uri);
        String queryStr = request.getQueryString();
        if (!CommonUtils.isEmpty(queryStr)) {
            buffer.append("?").append(queryStr);
        }
        String fullUrl = buffer.toString();
        if (LOGGER.isDebugEnabled()) {
            LOGGER.info("full url build before encode : {}", fullUrl);
        }
        return fullUrl;
    }
}