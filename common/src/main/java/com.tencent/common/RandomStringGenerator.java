package com.tencent.common;

import java.util.Random;

/**
 * User: rizenguo
 * Date: 2014/10/29
 * Time: 14:18
 */
public class RandomStringGenerator {

    /**
     * 获取一定长度的随机字符串
     * @param length 指定字符串长度
     * @return 一定长度的字符串
     */
    public static String getRandomStringByLength(int length) {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    /**
     * 获取随机的布尔值
     * @return 布尔值
     */
    public static boolean getRandomBoolean() {
        String base = "01";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 1; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        if("0".equals(sb.toString())){
            return false;
        }else if("1".equals(sb.toString())){
            return true;
        }else{
            Util.log("随机获取判断结果,出错，未知结果:"+sb.toString());
            return false;
        }
    }
}
