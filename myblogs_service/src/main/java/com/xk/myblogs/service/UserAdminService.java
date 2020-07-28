package com.xk.myblogs.service;

import com.xk.myblogs.client.dto.LoginResultDto;
import com.xk.myblogs.client.entity.myblog.Permission;
import com.xk.myblogs.client.entity.myblog.UserAdmin;

import java.util.List;

/**
 * @author Xiong Kai
 * @date 2020年01月16日 11时27分19秒
 */
public interface UserAdminService {
    /**
     * 根据用户名获取账号信息
     * @param username 用户名
     * @return
     */
    UserAdmin getUserAdminByUsername(String username);

    /**
     * 获取用户所有权限
     * @param id 用户id
     * @return
     */
    List<Permission> getPermissionsByUserid(Long id);

    /**
     * 登录功能
     * @param username
     * @param password
     * @return
     */
    LoginResultDto toLogin(String username, String password);

    /**
     * 注册功能
     * @param userAdmin 用户信息
     * @return
     */
    UserAdmin register(UserAdmin userAdmin);

    /**
     * 刷新token
     * @param username 用户名
     * @return
     */
    String refreshToken(String username);

    /**
     * Spring缓存测试
     * @param param
     * @return
     */
    boolean isApple(String param);
}
