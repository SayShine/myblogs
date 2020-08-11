package com.xk.myblogs.common.utils;

import java.util.UUID;

/**
 * ID生成器工具类
 * @author: tian
 * @date: 2020/8/10 11:20
 */
public class IdUtils {
    /**
     * 获取随机UUID
     *
     * @return 随机UUID
     */
    public static String randomUUID()
    {
        return UUID.randomUUID().toString();
    }

    /**
     * 简化的UUID，去掉了横线
     *
     * @return 简化的UUID，去掉了横线
     */
    public static String simpleUUID()
    {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

}