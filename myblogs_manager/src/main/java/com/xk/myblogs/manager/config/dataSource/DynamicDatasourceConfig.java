package com.xk.myblogs.manager.config.dataSource;

import org.springframework.context.annotation.Configuration;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * 动态数据源配置
 * @author: tian
 * @date: 2020/7/28 17:05
 */
@Configuration
@MapperScan(basePackages = "com.xk.myblogs.service.mapper", sqlSessionFactoryRef = "myBlogSqlSessionFactory")
public class DynamicDatasourceConfig {

}
