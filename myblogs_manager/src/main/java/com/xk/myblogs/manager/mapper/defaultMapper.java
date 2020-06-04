package com.xk.myblogs.manager.mapper;
import org.apache.ibatis.annotations.Param;
/**
 * 默认启动mapper会扫描当前所在包
 * 添加无用mapper 否则会在启动时出现警告 no mapper found in instant package
 * @Author: tian
 * @Date: 2020/6/4 23:09
 * @Param: nothing
 * @return: nothing
*/
@org.apache.ibatis.annotations.Mapper
public interface defaultMapper {
}
