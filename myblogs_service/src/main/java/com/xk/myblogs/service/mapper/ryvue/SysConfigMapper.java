package com.xk.myblogs.service.mapper.ryvue;

import com.xk.myblogs.client.entity.ryvue.SysConfig;
import com.xk.myblogs.common.annotion.DataSource;

public interface SysConfigMapper {
    /**
     * 按主键删除
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    @DataSource("ryvue")
    int deleteByPrimaryKey(Integer configId);

    /**
     * 插入数据 所有字段
     * @param record 操作 实体 bean 对象
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    @DataSource("ryvue")
    int insert(SysConfig record);

    /**
     * 插入数据 值不为 null 的字段
     * @param record 操作 实体 bean 对象
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    @DataSource("ryvue")
    int insertSelective(SysConfig record);

    /**
     * 按主键查询
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    @DataSource("ryvue")
    SysConfig selectByPrimaryKey(Integer configId);

    /**
     * 按主键更新值不为 null 的字段
     * @param record 操作 实体 bean 对象
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    @DataSource("ryvue")
    int updateByPrimaryKeySelective(SysConfig record);

    /**
     * 按主键更新所有字段
     * @param record 操作 实体 bean 对象
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    @DataSource("ryvue")
    int updateByPrimaryKey(SysConfig record);
}