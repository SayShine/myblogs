package com.xk.myblogs.service.mapper.ryvue;

import com.xk.myblogs.client.entity.ryvue.QrtzTriggers;
import com.xk.myblogs.client.entity.ryvue.QrtzTriggersKey;
import com.xk.myblogs.common.annotion.DataSource;

public interface QrtzTriggersMapper {
    /**
     * 按主键删除
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    @DataSource("ryvue")
    int deleteByPrimaryKey(QrtzTriggersKey key);

    /**
     * 插入数据 所有字段
     * @param record 操作 实体 bean 对象
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    @DataSource("ryvue")
    int insert(QrtzTriggers record);

    /**
     * 插入数据 值不为 null 的字段
     * @param record 操作 实体 bean 对象
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    @DataSource("ryvue")
    int insertSelective(QrtzTriggers record);

    /**
     * 按主键查询
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    @DataSource("ryvue")
    QrtzTriggers selectByPrimaryKey(QrtzTriggersKey key);

    /**
     * 按主键更新值不为 null 的字段
     * @param record 操作 实体 bean 对象
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    @DataSource("ryvue")
    int updateByPrimaryKeySelective(QrtzTriggers record);

    /**
     * 按主键更新所有字段（更新包含BLOB字段）
     * @param record 操作 实体 bean 对象
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    @DataSource("ryvue")
    int updateByPrimaryKeyWithBLOBs(QrtzTriggers record);

    /**
     * 按主键更新所有字段
     * @param record 操作 实体 bean 对象
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    @DataSource("ryvue")
    int updateByPrimaryKey(QrtzTriggers record);
}