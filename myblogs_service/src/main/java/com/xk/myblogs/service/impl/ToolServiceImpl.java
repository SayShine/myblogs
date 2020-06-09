package com.xk.myblogs.service.impl;

import com.xk.myblogs.service.RedisService;
import com.xk.myblogs.service.ToolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Random;

/**
 * @author: tian
 * @date: 2020/6/8 19:29
 */
@Service
public class ToolServiceImpl implements ToolService {
    @Autowired
    private RedisService redisService;
    //验证码字段和时间限制
    @Value("${redis.key.prefix.authCode}")
    private String REDIS_KEY_PREFIX_AUTH_CODE;
    @Value("${redis.key.expire.authCode}")
    private Long AUTH_CODE_EXPIRE_SECONDS;


    @Override
    public String generateAuthCode(String telephone) {
        //获取四位数验证码
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        int counts = 6;
        for (int i = 0; i < counts; i++) {
            sb.append(random.nextInt(10));
        }

        //验证码与手机号绑定并存储到redis中
        redisService.set(REDIS_KEY_PREFIX_AUTH_CODE+telephone,sb.toString());
        //设置过期时间
        redisService.expire(REDIS_KEY_PREFIX_AUTH_CODE+telephone,AUTH_CODE_EXPIRE_SECONDS);
        return sb.toString();
    }

    @Override
    public boolean verifyAuthCode(String telephone, String authCode) {
        String realAuthCode = redisService.get(REDIS_KEY_PREFIX_AUTH_CODE+telephone);
        return authCode.equals(realAuthCode);
    }
}
