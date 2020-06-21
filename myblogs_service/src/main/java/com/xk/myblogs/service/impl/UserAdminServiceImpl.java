package com.xk.myblogs.service.impl;

import com.xk.myblogs.client.dto.LoginResultDto;
import com.xk.myblogs.client.entity.Permission;
import com.xk.myblogs.client.entity.UserAdmin;
import com.xk.myblogs.client.entity.UserAdminExample;
import com.xk.myblogs.common.enums.DataStatusEnum;
import com.xk.myblogs.common.enums.LoginStatusEnum;
import com.xk.myblogs.common.utils.JwtUtil;
import com.xk.myblogs.service.RedisService;
import com.xk.myblogs.service.UserAdminService;
import com.xk.myblogs.service.dao.UserAdminAuthDao;
import com.xk.myblogs.service.mapper.UserAdminMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author Xiong Kai
 * @date 2020年01月16日 11时27分33秒
 */
@Service
public class UserAdminServiceImpl implements UserAdminService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserAdminServiceImpl.class);

    //redis中 验证码字段和时间限制
    @Value("${redis.key.prefix.authToken}")
    private String REDIS_KEY_PREFIX_AUTH_TOKEN;
    @Value("${redis.key.expire.authToken}")
    private Long AUTH_TOKEN_EXPIRE_SECONDS;

    @Resource
    private UserAdminMapper userAdminMapper;

    @Autowired(required = false)
    private JwtUtil jwtUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserAdminAuthDao userAdminAuthDao;

    @Autowired
    private RedisService redisService;

    @Override
    public UserAdmin getUserAdminByUsername(String username) {
        if(StringUtils.isEmpty(username)){
            return null;
        }

        UserAdminExample example = new UserAdminExample();
        example.createCriteria().andUsernameEqualTo(username);
        example.createCriteria().andStatusEqualTo(DataStatusEnum.USE_ENABLE.getType());
        List<UserAdmin> userAdminList = userAdminMapper.selectByExample(example);
        return  CollectionUtils.isEmpty(userAdminList)?null:userAdminList.get(0);
    }

    @Override
    public List<Permission> getPermissionsByUserid(Long id) {
        return userAdminAuthDao.getPermissionList(id);
    }

    @Override
    public LoginResultDto toLogin(String username, String password) {
        LoginResultDto loginResultDto = new LoginResultDto();
        //实际上已经判断过了
        if(StringUtils.isEmpty(username)||StringUtils.isEmpty(password)){
            return null;
        }
        //根据用户名查询用户
        try {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            if(passwordEncoder.matches(password,userDetails.getPassword())){
                //密码相等
                loginResultDto.setStatus(LoginStatusEnum.LOGIN_SUCCESS.getType());
                loginResultDto.setMsg(LoginStatusEnum.LOGIN_SUCCESS.getMsg());

                //设置token并添加进redis中
                String token = jwtUtil.generateToken(username);
                loginResultDto.setToken(token);

                //token和用户名绑定
                redisService.set(REDIS_KEY_PREFIX_AUTH_TOKEN+username,token);
                //设置过期时间
                redisService.expire(REDIS_KEY_PREFIX_AUTH_TOKEN+username,AUTH_TOKEN_EXPIRE_SECONDS);


            }else{
                //用户名或密码错误
                loginResultDto.setStatus(LoginStatusEnum.LOGIN_PASSWORD_ERROR.getType());
                loginResultDto.setMsg(LoginStatusEnum.LOGIN_PASSWORD_ERROR.getMsg());
            }
        } catch (UsernameNotFoundException e) {
            LOGGER.warn("登录异常：{}",e.getMessage());
            loginResultDto.setStatus(LoginStatusEnum.LOGIN_PASSWORD_ERROR.getType());
            loginResultDto.setMsg(LoginStatusEnum.LOGIN_PASSWORD_ERROR.getMsg());
        }

        return loginResultDto;
    }

    @Override
    public UserAdmin register(UserAdmin userAdminParam) {
        UserAdmin userAdmin = new UserAdmin();
        BeanUtils.copyProperties(userAdminParam,userAdmin);
        userAdmin.setStatus(DataStatusEnum.USE_ENABLE.getType());
        userAdmin.setUpdateTime(new Date());

        UserAdminExample userAdminExample = new UserAdminExample();
        userAdminExample.createCriteria().andUsernameEqualTo(userAdminParam.getUsername());
        List<UserAdmin> userAdminList =userAdminMapper.selectByExample(userAdminExample);
        if(!CollectionUtils.isEmpty(userAdminList)){
            //用户名被注册
            return null;
        }

        //密码加密 并保存
        String encodePassword = passwordEncoder.encode(userAdminParam.getPassword());
        userAdmin.setPassword(encodePassword);
        userAdminMapper.insert(userAdmin);
        return userAdmin;
    }

    @Override
    public String refreshToken(String username) {
        if(StringUtils.isEmpty(redisService.get(REDIS_KEY_PREFIX_AUTH_TOKEN+username))){
            LOGGER.info("-----------key is null----------");

            return null;
        }
        LOGGER.info("-----------key is not null----------");
        LOGGER.info("username:{}",redisService.get(REDIS_KEY_PREFIX_AUTH_TOKEN+username));
        return jwtUtil.generateToken(username);

//        return jwtUtil.refreshHeadToken(token);
    }
}
