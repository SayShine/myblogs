package com.xk.myblogs.service.impl;

import com.xk.myblogs.client.dto.LoginResultDto;
import com.xk.myblogs.client.entity.Permission;
import com.xk.myblogs.client.entity.UserAdmin;
import com.xk.myblogs.client.entity.UserAdminExample;
import com.xk.myblogs.common.enums.DataStatusEnum;
import com.xk.myblogs.common.enums.LoginStatusEnum;
import com.xk.myblogs.common.utils.JwtUtil;
import com.xk.myblogs.service.UserAdminService;
import com.xk.myblogs.service.mapper.UserAdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Xiong Kai
 * @date 2020年01月16日 11时27分33秒
 */
@Service
public class UserAdminServiceImpl implements UserAdminService {

    @Resource
    private UserAdminMapper userAdminMapper;

    @Autowired(required = false)
    private JwtUtil jwtUtil;

    @Override
    public UserAdmin getUserAdminByUsername(String username) {
        if(StringUtils.isEmpty(username)){
            return null;
        }
        UserAdminExample userAdminExample = new UserAdminExample();
        UserAdminExample.Criteria criteria = userAdminExample.createCriteria();
        criteria.andAccountEqualTo(username);
        criteria.andStatusEqualTo(DataStatusEnum.USE_ENABLE.getType());
        List<UserAdmin> userAdminList = userAdminMapper.selectByExample(userAdminExample);
        return  CollectionUtils.isEmpty(userAdminList)?null:userAdminList.get(0);
    }

    @Override
    public List<Permission> getPermissionsByUsername(String username) {
        //TODO 完成业务代码
        return null;
    }

    @Override
    public LoginResultDto toLogin(String username, String password) {
        LoginResultDto loginResultDto = new LoginResultDto();

        if(StringUtils.isEmpty(username)||StringUtils.isEmpty(password)){
            return null;
        }
        //根据用户名查询用户
        UserAdmin userAdmin = getUserAdminByUsername(username);
        //对比用户名及密码
        if(userAdmin==null){//用户名不存在
            loginResultDto.setStatus(LoginStatusEnum.LOGIN_USER_ERROR.getType());
            loginResultDto.setMsg(LoginStatusEnum.LOGIN_USER_ERROR.getMsg());
        }else if (password.equals(userAdmin.getPassword())){//用户名及密码正确
            loginResultDto.setStatus(LoginStatusEnum.LOGIN_SUCCESS.getType());
            loginResultDto.setMsg(LoginStatusEnum.LOGIN_SUCCESS.getMsg());
            loginResultDto.setToken(jwtUtil.generateToken(username));
        }else{//用户名或密码错误
            loginResultDto.setStatus(LoginStatusEnum.LOGIN_PASSWORD_ERROR.getType());
            loginResultDto.setMsg(LoginStatusEnum.LOGIN_PASSWORD_ERROR.getMsg());
        }
        return loginResultDto;
    }
}
