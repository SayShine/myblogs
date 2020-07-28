package com.xk.myblogs.manager.config.dataSource;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import tk.mybatis.spring.annotation.MapperScan;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 数据源myblog配置
 * @author: tian
 * @date: 2020/7/24 11:43
 */
//@Configuration
//@MapperScan(basePackages = "com.xk.myblogs.service.mapper.myblog", sqlSessionFactoryRef = "myBlogSqlSessionFactory")
public class MyblogConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger(MyblogConfig.class);

//    @Bean(name = "myblogDataSource")
//    @Primary
//    @ConfigurationProperties(prefix = "spring.datasource.myblog", ignoreInvalidFields = true)
    public DataSource getDataSource(){
        return new DruidDataSource();
    }

//    @Bean(name = "myBlogSqlSessionFactory")
//    @Primary
    public SqlSessionFactory myBlogSqlSessionFactory(@Qualifier("myblogDataSource") DataSource datasource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(datasource);
        bean.setMapperLocations(
                // 设置mybatis的xml所在位置
//                new PathMatchingResourcePatternResolver().getResources("classpath*:com/xk/myblogs/service/mapper/xml/*.xml")
                resoleveMapperLocations()
        );
        bean.setTypeHandlersPackage("com.xk.myblogs.service.typehandler");
        return bean.getObject();
    }

    //    @Bean(name = "myblogSqlSessionTemplate")
    //    @Primary
    public SqlSessionTemplate myblogSqlSessionTemplate(@Qualifier("myBlogSqlSessionFactory") SqlSessionFactory sessionfactory){
        return new SqlSessionTemplate(sessionfactory);
    }

    //获取所有匹配上路径的参数
    public Resource[] resoleveMapperLocations(){
        ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();

        List<String> mapperLocations = new ArrayList<>();
        mapperLocations.add("classpath*:com/xk/myblogs/service/mapper/myblog/xml/*.xml");
        mapperLocations.add("classpath*:mapper/myblog/*.xml");

        List<Resource> resources = new ArrayList<>();

        if(mapperLocations != null){
            for (String mapperLocation : mapperLocations) {
                try {
                    //这里获取到 匹配上路径的xml文件
                    Resource[] mappers = resourcePatternResolver.getResources(mapperLocation);
                    resources.addAll(Arrays.asList(mappers));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        for (int i = 0; i < resources.size(); i++) {
            LOGGER.info("myblog数据库获取到xml文件:{}",resources.get(i));
        }

        return resources.toArray(new Resource[resources.size()]);

    }



}
