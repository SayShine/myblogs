package com.xk.myblogs.service.impl;

import com.xk.myblogs.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * redis服务实现类
 * @author: tian
 * @date: 2020/6/1 11:58
 */
@Service
public class RedisServiceImpl implements RedisService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public void set(String key, String value) {
        stringRedisTemplate.opsForValue().set(key,value);
    }

    @Override
    public String get(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

    @Override
    public void remove(String key) {
        stringRedisTemplate.delete(key);
    }

    @Override
    public boolean expire(String key, long expire) {
        return stringRedisTemplate.expire(key, expire, TimeUnit.SECONDS);
    }

    @Override
    public Long increment(String key, long delta) {
        return stringRedisTemplate.opsForValue().increment(key,delta);
    }

    @Override
    public void put(String hashMapName, String key, String value) {
        stringRedisTemplate.opsForHash().put(hashMapName, key, value);
    }

    @Override
    public void putall(String hashMapName, HashMap hashMap) {
        stringRedisTemplate.opsForHash().putAll(hashMapName, hashMap);
    }
}
