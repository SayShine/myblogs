package com.xk.myblogs.service.dao.myblog;

import org.apache.ibatis.annotations.Param;
/**
 *
 * @Author: tian
 * @Date: 2020/8/9 22:00
*/
public interface ProductDao {
    int decreaseProduct(@Param("id") Long id,
                        @Param("quantity") int quantity);
}
