package com.xk.myblogs.service;

import com.xk.myblogs.client.dto.LoginResultDto;
import com.xk.myblogs.client.entity.Permission;
import com.xk.myblogs.client.entity.UserAdmin;
import com.xk.myblogs.service.mapper.UserAdminMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * @author Xiong Kai
 * @date 2020年01月16日 11时27分19秒
 */
public interface UserAdminService {

    UserAdmin getUserAdminByUsername(String username);

    List<Permission> getPermissionsByUsername(String username);

    LoginResultDto toLogin(String username, String password);
}
