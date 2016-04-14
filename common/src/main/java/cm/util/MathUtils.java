
package cm.util;

import java.util.Random;

/**
 * 类名：fm.util
 * 创建者： CM .
 * 创建时间：2015/9/15
 */
public abstract class MathUtils {
    static final int DOUBLE_POINT = 1000;
    static final float FLOAT_DOUBLE_POINT = 1000f;
    /**
     * 求1以内的随机数是否落在某个范围
     *
     * @param start
     * @param end
     * @return
     */
    public static boolean randomInRange(double start, double end, double max) {
        double random = new Random().nextDouble() * DOUBLE_POINT * max;
        random = ((int) random) / FLOAT_DOUBLE_POINT;
        if (random >= start && random <= end) {
            return true;
        }
        return false;
    }

    /**
     * 在最大值范围内取得随机数
     * @param max
     * @return
     */
    public static double randomInRange(double max) {
        double random = new Random().nextDouble() * DOUBLE_POINT * max;
        return ((int) random) / FLOAT_DOUBLE_POINT;
    }
}
