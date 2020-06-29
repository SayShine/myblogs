package com.xk.myblogs.service.impl;

import com.xk.myblogs.client.entity.UserAdmin;
import com.xk.myblogs.client.entity.UserAdminExample;
import com.xk.myblogs.client.entity.UserBlogs;
import com.xk.myblogs.client.entity.UserBlogsExample;
import com.xk.myblogs.service.RedisService;
import com.xk.myblogs.service.ToolService;
import com.xk.myblogs.service.dao.UserBlogsDao;
import com.xk.myblogs.service.mapper.UserAdminMapper;
import com.xk.myblogs.service.mapper.UserBlogsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.*;

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

    @Resource
    private UserAdminMapper userAdminMapper;
    @Resource
    private UserBlogsMapper userBlogsMapper;
    @Resource
    private UserBlogsDao userBlogsDao;


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

    @Override
    public List<UserBlogs> getMdListByUsername(String username) {
        if(StringUtils.isEmpty(username)){
            return null;
        }
        UserAdminExample userAdminExample = new UserAdminExample();
        userAdminExample.createCriteria().andUsernameEqualTo(username);
        List<UserAdmin> userAdminList = userAdminMapper.selectByExample(userAdminExample);
        UserAdmin userAdmin = CollectionUtils.isEmpty(userAdminList)?null:userAdminList.get(0);
        if(userAdmin == null){
            return null;
        }
        //存在用户

        List<UserBlogs> userBlogsList = userBlogsDao.getMdListByUserId(userAdmin.getId());
        return  CollectionUtils.isEmpty(userBlogsList)?null:userBlogsList;


    }

    @Override
    public int savaMdList(UserBlogs userBlogs) {
        userBlogs.setUpdateTime(new Date());
        return userBlogsMapper.updateByPrimaryKey(userBlogs);
    }

    @Override
    public int deleteMdList(String idsString) {
        String[] ids = idsString.split(",");
        if(ids.length==0){
            return 0;
        }
        List<Long> list = new ArrayList<>();
        for (int i = 0; i < ids.length; i++) {
            String id = ids[i];
            list.add(Long.parseLong(id));
        }

        UserBlogs userBlogs = new UserBlogs();
        userBlogs.setStatus(0);
        UserBlogsExample example = new UserBlogsExample();
        example.createCriteria().andStatusEqualTo(1).andIdIn(list);
        return userBlogsMapper.updateByExampleSelective(userBlogs,example);

    }
}
