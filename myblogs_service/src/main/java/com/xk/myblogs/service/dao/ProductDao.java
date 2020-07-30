package com.xk.myblogs.service.dao;

import org.apache.ibatis.annotations.Param;

public interface ProductDao {
    int decreaseProduct(@Param("id") Long id,
                        @Param("quantity") int quantity);
}
