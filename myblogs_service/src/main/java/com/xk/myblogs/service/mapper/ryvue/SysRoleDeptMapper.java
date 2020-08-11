package com.xk.myblogs.service.mapper.ryvue;

import com.xk.myblogs.client.entity.ryvue.SysRoleDeptKey;
import com.xk.myblogs.common.annotion.DataSource;

public interface SysRoleDeptMapper {
    /**
     * 按主键删除
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    @DataSource("ryvue")
    int deleteByPrimaryKey(SysRoleDeptKey key);

    /**
     * 插入数据 所有字段
     * @param record 操作 实体 bean 对象
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    @DataSource("ryvue")
    int insert(SysRoleDeptKey record);

    /**
     * 插入数据 值不为 null 的字段
     * @param record 操作 实体 bean 对象
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    @DataSource("ryvue")
    int insertSelective(SysRoleDeptKey record);
}