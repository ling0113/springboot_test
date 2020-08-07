package com.springboot_test.util;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import org.apache.commons.lang3.RandomStringUtils;

/**
 * @Description:
 * @Author: sxw
 * @Version: 1.0
 */
public class SnowflakeUtil {
    public static final Snowflake SNOWFLAKE = IdUtil.createSnowflake(1, 1);

    private static final char[] chars = new char[]{
        '0', '1', '2', '3', '4', '5', '6', '7', '8', '8',
        'a', 'b', 'c', 'd', 'e', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
        'A', 'B', 'C', 'D', 'E', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
    };

    public static String getSnowflakeId() {
        return String.valueOf(SNOWFLAKE.nextId());
    }

    /**
     * 返回指定长度的随机字符串
     *
     * @author Tanwei
     * @date 2019-08-08 18:04
     * @return
    */
    public static String randomStr(int count) {
        return RandomStringUtils.random(count, chars);
    }

    /**
     * 默认返回16位的随机字符串
     *
     * @author Tanwei
     * @date 2019-08-08 18:05
     * @return
    */
    public static String randomStr() {
        return randomStr(16);
    }
}
