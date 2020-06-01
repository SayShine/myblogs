package com.xk.myblogs.service.dao;

import com.xk.myblogs.client.entity.Permission;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: tian
 * @date: 2020/6/1 18:28
 */
public interface UserAdminAuthDao {
    List<Permission> getPermissionList(@Param("id") Long id);
}
