package com.xk.myblogs.service.typehandler;

import com.xk.myblogs.client.enums.SexEnum;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * mybatis性别转换
 * @author: tian
 * @date: 2020/7/9 14:10
 */
@MappedJdbcTypes(JdbcType.INTEGER)
@MappedTypes(SexEnum.class)
public class SexTypeHandler extends BaseTypeHandler<SexEnum> {

    /**
     * 设置非空性别参数
     * @param preparedStatement
     * @param index 下标
     * @param sexEnum 性别枚举
     * @param jdbcType jdbc中的参数
     * @throws SQLException
     */
    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int index, SexEnum sexEnum, JdbcType jdbcType) throws SQLException {
        preparedStatement.setInt(index,sexEnum.getId());
    }

    /**
     * 通过列名读取性别
     * @param rs
     * @param s 列名
     * @return
     * @throws SQLException
     */
    @Override
    public SexEnum getNullableResult(ResultSet rs, String s) throws SQLException {
        int sex = rs.getInt(s);
        if(sex != 1 && sex != 2){
            return null;
        }
        return SexEnum.getEnumById(sex);
    }

    /**
     * 通过下标读取性别
     * @param rs
     * @param index
     * @return
     * @throws SQLException
     */
    @Override
    public SexEnum getNullableResult(ResultSet rs, int index) throws SQLException {
        int sex = rs.getInt(index);
        if(sex != 1 && sex != 2){
            return null;
        }
        return SexEnum.getEnumById(sex);
    }

    /**
     * 通过存储过程读取性别
     * @param cs
     * @param index
     * @return
     * @throws SQLException
     */
    @Override
    public SexEnum getNullableResult(CallableStatement cs, int index) throws SQLException {
        int sex = cs.getInt(index);
        if(sex != 1 && sex != 2){
            return null;
        }
        return SexEnum.getEnumById(sex);
    }
}
