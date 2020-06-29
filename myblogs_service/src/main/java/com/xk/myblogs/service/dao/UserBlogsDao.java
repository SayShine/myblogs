package com.xk.myblogs.service.dao;

import com.xk.myblogs.client.entity.UserBlogs;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface UserBlogsDao {
    int deleteMdList(@Param("array") String[] array);

    List<UserBlogs> getMdListByUserId(@Param("id")Long id);
}
