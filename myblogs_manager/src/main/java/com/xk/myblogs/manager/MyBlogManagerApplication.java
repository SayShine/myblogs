package com.xk.myblogs.manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan(basePackages = {"com.xk.myblogs.service.mapper","com.xk.myblogs.service.dao"})
@ComponentScan(basePackages = {"com.xk.myblogs"})
//使用注解驱动缓存机制
@EnableCaching
//mongodb dao接口扫描
@EnableMongoRepositories("com.xk.myblogs.service.nosqldao")
@EnableScheduling
//开启事务控制
@EnableTransactionManagement
public class MyBlogManagerApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(MyBlogManagerApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(MyBlogManagerApplication.class);
    }


}
