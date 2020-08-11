package com.xk.myblogs.service.mapper.ryvue;

import com.xk.myblogs.client.entity.ryvue.QrtzFiredTriggers;
import com.xk.myblogs.client.entity.ryvue.QrtzFiredTriggersKey;
import com.xk.myblogs.common.annotion.DataSource;

public interface QrtzFiredTriggersMapper {
    /**
     * 按主键删除
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    @DataSource("ryvue")
    int deleteByPrimaryKey(QrtzFiredTriggersKey key);

    /**
     * 插入数据 所有字段
     * @param record 操作 实体 bean 对象
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    @DataSource("ryvue")
    int insert(QrtzFiredTriggers record);

    /**
     * 插入数据 值不为 null 的字段
     * @param record 操作 实体 bean 对象
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    @DataSource("ryvue")
    int insertSelective(QrtzFiredTriggers record);

    /**
     * 按主键查询
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    @DataSource("ryvue")
    QrtzFiredTriggers selectByPrimaryKey(QrtzFiredTriggersKey key);

    /**
     * 按主键更新值不为 null 的字段
     * @param record 操作 实体 bean 对象
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    @DataSource("ryvue")
    int updateByPrimaryKeySelective(QrtzFiredTriggers record);

    /**
     * 按主键更新所有字段
     * @param record 操作 实体 bean 对象
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    @DataSource("ryvue")
    int updateByPrimaryKey(QrtzFiredTriggers record);
}