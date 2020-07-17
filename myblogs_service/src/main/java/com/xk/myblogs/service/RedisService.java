package com.xk.myblogs.service;

import org.springframework.data.redis.core.ZSetOperations;

import java.util.HashMap;

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

    /**
     * redis向对应hash添加键值对
     * @param hashMapName hashMap表名
     * @param key 键
     * @param value 值
     */
    void put(String hashMapName,String key, String value);

    /**
     * redis中添加hashMap
     * @param hashMapName
     * @param hashMap
     */
    void putall(String hashMapName, HashMap hashMap);
}
