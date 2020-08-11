package com.xk.myblogs.service.mapper.myblog;

import com.xk.myblogs.client.entity.myblog.PurchaseRecord;
import com.xk.myblogs.client.entity.myblog.PurchaseRecordExample;
import com.xk.myblogs.common.annotion.DataSource;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PurchaseRecordMapper {
    /**
     * 按条件计数
	 * @param example 条件
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    @DataSource("myblog")
    long countByExample(PurchaseRecordExample example);

    /**
     * 按条件删除
	 * @param example 条件
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    @DataSource("myblog")
    int deleteByExample(PurchaseRecordExample example);

    /**
     * 按主键删除
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    @DataSource("myblog")
    int deleteByPrimaryKey(Long id);

    /**
     * 插入数据 所有字段
     * @param record 操作 实体 bean 对象
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    @DataSource("myblog")
    int insert(PurchaseRecord record);

    /**
     * 插入数据 值不为 null 的字段
     * @param record 操作 实体 bean 对象
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    @DataSource("myblog")
    int insertSelective(PurchaseRecord record);

    /**
     * 按条件查询
	 * @param example 条件
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    @DataSource("myblog")
    List<PurchaseRecord> selectByExample(PurchaseRecordExample example);

    /**
     * 按主键查询
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    @DataSource("myblog")
    PurchaseRecord selectByPrimaryKey(Long id);

    /**
     * 按条件更新值不为 null 的字段
     * @param record 操作 实体 bean 对象
	 * @param example 条件
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    @DataSource("myblog")
    int updateByExampleSelective(@Param("record") PurchaseRecord record, @Param("example") PurchaseRecordExample example);

    /**
     * 按条件更新所有字段
     * @param record 操作 实体 bean 对象
	 * @param example 条件
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    @DataSource("myblog")
    int updateByExample(@Param("record") PurchaseRecord record, @Param("example") PurchaseRecordExample example);

    /**
     * 按主键更新值不为 null 的字段
     * @param record 操作 实体 bean 对象
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    @DataSource("myblog")
    int updateByPrimaryKeySelective(PurchaseRecord record);

    /**
     * 按主键更新所有字段
     * @param record 操作 实体 bean 对象
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    @DataSource("myblog")
    int updateByPrimaryKey(PurchaseRecord record);
}