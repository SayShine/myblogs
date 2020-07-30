package com.xk.myblogs.service.mapper.myblog;

import com.xk.myblogs.client.entity.myblog.Product;
import com.xk.myblogs.client.entity.myblog.ProductExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProductMapper {
    /**
     * 按条件计数
	 * @param example 条件
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    long countByExample(ProductExample example);

    /**
     * 按条件删除
	 * @param example 条件
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    int deleteByExample(ProductExample example);

    /**
     * 按主键删除
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 插入数据 所有字段
     * @param record 操作 实体 bean 对象
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    int insert(Product record);

    /**
     * 插入数据 值不为 null 的字段
     * @param record 操作 实体 bean 对象
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    int insertSelective(Product record);

    /**
     * 按条件查询
	 * @param example 条件
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    List<Product> selectByExample(ProductExample example);

    /**
     * 按主键查询
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    Product selectByPrimaryKey(Long id);

    /**
     * 按条件更新值不为 null 的字段
     * @param record 操作 实体 bean 对象
	 * @param example 条件
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    int updateByExampleSelective(@Param("record") Product record, @Param("example") ProductExample example);

    /**
     * 按条件更新所有字段
     * @param record 操作 实体 bean 对象
	 * @param example 条件
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    int updateByExample(@Param("record") Product record, @Param("example") ProductExample example);

    /**
     * 按主键更新值不为 null 的字段
     * @param record 操作 实体 bean 对象
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    int updateByPrimaryKeySelective(Product record);

    /**
     * 按主键更新所有字段
     * @param record 操作 实体 bean 对象
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    int updateByPrimaryKey(Product record);
}