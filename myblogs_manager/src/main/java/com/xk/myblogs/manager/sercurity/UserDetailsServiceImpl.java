package com.xk.myblogs.manager.sercurity;

import com.xk.myblogs.client.entity.Permission;
import com.xk.myblogs.client.entity.UserAdmin;
import com.xk.myblogs.service.UserAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 登录相关API
 * @author Xiong Kai
 * @date 2020年01月16日 11时16分00秒
 */
@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserAdminService userAdminService;
    /**
     * 根据用户名，获取用户及其权限
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public AdminUserDetail loadUserByUsername(String username) throws UsernameNotFoundException {
        if(StringUtils.isEmpty(username)){
            throw new UsernameNotFoundException("用户名为空");
        }
        UserAdmin userAdmin = userAdminService.getUserAdminByUsername(username);
        if(userAdmin!=null){
            List<Permission> permissionList = userAdminService.getPermissionsByUsername(username);
            return new AdminUserDetail(userAdmin,permissionList);
        }
        throw new UsernameNotFoundException("用户名或密码错误");
    }
}
