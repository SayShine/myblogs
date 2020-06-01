package com.xk.myblogs.service;

/**
 * redis操作Service
 * @Author: tian
 * @Date: 2020/6/1 11:53
*/
public interface RedisService {
    /**
     * 存储数据
     */
    void set(String key, String value);

    /**
     * 获取数据
     */
    String get(String key);

    /**
     * 删除数据
     */
    void remove(String key);

    /**
     * 设置超期时间
     */
    boolean expire(String key, long expire);

    /**
     * 自增操作
     */
    Long increment(String key, long delta);

}
