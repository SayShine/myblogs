package com.xk.myblogs.manager.sercurity;

import com.xk.myblogs.client.entity.Authority;
import com.xk.myblogs.client.entity.Permission;
import com.xk.myblogs.client.entity.User;
import com.xk.myblogs.client.entity.UserAdmin;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户以及对应权限的包装类
 * @author Xiong Kai
 * @date 2020年01月16日 10时58分55秒
 */
public class AdminUserDetail implements UserDetails {
    private UserAdmin userAdmin;//用户
    private List<Permission> permissionList;//权限列表

    public AdminUserDetail(UserAdmin userAdmin,List<Permission> permissionList){
        this.userAdmin = userAdmin;
        this.permissionList = permissionList;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //返回当前用户的权限
        return permissionList==null?null:
                permissionList.stream()
                .filter(permission -> permission.getValue()!=null)
                .map(permission ->new SimpleGrantedAuthority(permission.getValue()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return userAdmin.getPassword();
    }

    @Override
    public String getUsername() {
        return userAdmin.getAccount();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        //TODO 解决硬编码问题
        return userAdmin.getStatus().equals(1)||userAdmin.getStatus().equals(2);
    }
}
