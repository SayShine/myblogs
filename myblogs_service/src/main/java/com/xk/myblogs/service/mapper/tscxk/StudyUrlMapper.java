package com.xk.myblogs.service.mapper.tscxk;

import com.xk.myblogs.client.entity.tscxk.StudyUrl;
import com.xk.myblogs.client.entity.tscxk.StudyUrlExample;
import com.xk.myblogs.common.annotion.DataSource;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StudyUrlMapper {
    /**
     * 按条件计数
	 * @param example 条件
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    @DataSource("tscxk")
    long countByExample(StudyUrlExample example);

    /**
     * 按条件删除
	 * @param example 条件
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    @DataSource("tscxk")
    int deleteByExample(StudyUrlExample example);

    /**
     * 按主键删除
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    @DataSource("tscxk")
    int deleteByPrimaryKey(Long id);

    /**
     * 插入数据 所有字段
     * @param record 操作 实体 bean 对象
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    @DataSource("tscxk")
    int insert(StudyUrl record);

    /**
     * 插入数据 值不为 null 的字段
     * @param record 操作 实体 bean 对象
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    @DataSource("tscxk")
    int insertSelective(StudyUrl record);

    /**
     * 按条件查询
	 * @param example 条件
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    @DataSource("tscxk")
    List<StudyUrl> selectByExample(StudyUrlExample example);

    /**
     * 按主键查询
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    @DataSource("tscxk")
    StudyUrl selectByPrimaryKey(Long id);

    /**
     * 按条件更新值不为 null 的字段
     * @param record 操作 实体 bean 对象
	 * @param example 条件
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    @DataSource("tscxk")
    int updateByExampleSelective(@Param("record") StudyUrl record, @Param("example") StudyUrlExample example);

    /**
     * 按条件更新所有字段
     * @param record 操作 实体 bean 对象
	 * @param example 条件
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    @DataSource("tscxk")
    int updateByExample(@Param("record") StudyUrl record, @Param("example") StudyUrlExample example);

    /**
     * 按主键更新值不为 null 的字段
     * @param record 操作 实体 bean 对象
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    @DataSource("tscxk")
    int updateByPrimaryKeySelective(StudyUrl record);

    /**
     * 按主键更新所有字段
     * @param record 操作 实体 bean 对象
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    @DataSource("tscxk")
    int updateByPrimaryKey(StudyUrl record);
}