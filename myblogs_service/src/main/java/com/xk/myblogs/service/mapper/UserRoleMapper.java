package com.xk.myblogs.service.mapper;

import com.xk.myblogs.client.entity.UserRole;
import com.xk.myblogs.client.entity.UserRoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserRoleMapper {
    /**
     * 按条件计数
	 * @param example 条件
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    long countByExample(UserRoleExample example);

    /**
     * 按条件删除
	 * @param example 条件
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    int deleteByExample(UserRoleExample example);

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
    int insert(UserRole record);

    /**
     * 插入数据 值不为 null 的字段
     * @param record 操作 实体 bean 对象
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    int insertSelective(UserRole record);

    /**
     * 按条件查询
	 * @param example 条件
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    List<UserRole> selectByExample(UserRoleExample example);

    /**
     * 按主键查询
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    UserRole selectByPrimaryKey(Long id);

    /**
     * 按条件更新值不为 null 的字段
     * @param record 操作 实体 bean 对象
	 * @param example 条件
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    int updateByExampleSelective(@Param("record") UserRole record, @Param("example") UserRoleExample example);

    /**
     * 按条件更新所有字段
     * @param record 操作 实体 bean 对象
	 * @param example 条件
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    int updateByExample(@Param("record") UserRole record, @Param("example") UserRoleExample example);

    /**
     * 按主键更新值不为 null 的字段
     * @param record 操作 实体 bean 对象
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    int updateByPrimaryKeySelective(UserRole record);

    /**
     * 按主键更新所有字段
     * @param record 操作 实体 bean 对象
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    int updateByPrimaryKey(UserRole record);
}