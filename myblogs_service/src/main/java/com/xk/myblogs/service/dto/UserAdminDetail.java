package com.xk.myblogs.service.dto;

import com.xk.myblogs.client.entity.myblog.Permission;
import com.xk.myblogs.client.entity.myblog.UserAdmin;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: tian
 * @date: 2020/6/1 17:08
 */
public class UserAdminDetail implements UserDetails {
    private UserAdmin userAdmin;//用户
    private List<Permission> permissionList;//权限列表

    public UserAdminDetail(UserAdmin userAdmin, List<Permission> permissionList){
        this.userAdmin = userAdmin;
        this.permissionList = permissionList;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //返回当前用户的权限
        return permissionList==null?null:
                permissionList.stream()
                        .filter(permission -> permission.getType()!=null)
                        .map(permission ->new SimpleGrantedAuthority(permission.getType().toString()))
                        .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return userAdmin.getPassword();
    }

    @Override
    public String getUsername() {
        return userAdmin.getUsername();
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