package cm.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * emoji表情字符过滤器，兼容低版本数据库无法保存emoji表情的问题
 * 类名：fm.util.EmojiFilterUtils
 * 创建者： CM .
 * 创建时间：2016/3/22
 */
public class EmojiFilterUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmojiFilterUtils.class);

    /**
     * 过滤表情
     *
     * @param source
     * @return
     */
    public static String filterEmoji(String source) {
        if (!containsEmoji(source)) {
            return source;// 如果不包含，直接返回
        }

        StringBuilder buf = null;
        int len = source.length();
        for (int i = 0; i < len; i++) {
            char codePoint = source.charAt(i);
            if (!isEmojiCharacter(codePoint)) {
                if (buf == null) {
                    buf = new StringBuilder(source.length());
                }
                buf.append(codePoint);
            } else {
            }
        }
        if (buf == null) {
            return "";
        } else {
            if (buf.length() == len) {// 这里的意义在于尽可能少的toString，因为会重新生成字符串
                buf = null;
                return source;
            } else {
                return buf.toString();
            }
        }
    }

    /**
     * 判断是否包含emoji表情
     *
     * @param str
     * @return
     */
    private static boolean containsEmoji(String str) {
        int len = str.length();
        for (int i = 0; i < len; i++) {
            if (isEmojiCharacter(str.charAt(i))) {
                LOGGER.info("source str :{} ,contains emoji char,filter!", str);
                return true;
            }
        }
        return false;
    }

    /**
     * 判断是否为emoji表情符
     *
     * @param codePoint
     * @return
     */
    private static boolean isEmojiCharacter(char codePoint) {
        return !((codePoint == 0x0) || (codePoint == 0x9) ||
                (codePoint == 0xA) || (codePoint == 0xD) ||
                ((codePoint >= 0x20) && (codePoint <= 0xD7FF)) ||
                ((codePoint >= 0xE000) && (codePoint <= 0xFFFD)) ||
                ((codePoint >= 0x10000) && (codePoint <= 0x10FFFF)));
    }


}
