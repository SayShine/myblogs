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
 * tscxk数据源
 * @author: tian
 * @date: 2020/7/24 20:40
 */
@Configuration
@MapperScan(basePackages = "com.xk.myblogs.service.mapper.tscxk", sqlSessionFactoryRef = "tscxkSqlSessionFactory")
public class TscxkConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger(TscxkConfig.class);

    @Bean(name = "tscxkDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.tscxk", ignoreInvalidFields = true)
    public DataSource getTscxkDataSource(){
        return new DruidDataSource();
    }

    @Bean(name = "tscxkSqlSessionFactory")
    public SqlSessionFactory tscxkSqlSessionFactory(@Qualifier("tscxkDataSource")DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(resoleveMapperLocations());
        return bean.getObject();
    }

    @Bean("tscxkSqlSessionTemplate")
    public SqlSessionTemplate tscxkSqlSessionTemplate(@Qualifier("tscxkSqlSessionFactory")SqlSessionFactory sqlSessionFactory){
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    public Resource[] resoleveMapperLocations(){
        ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();

        List<String> mapperLocations = new ArrayList<>();
        mapperLocations.add("classpath*:com/xk/myblogs/service/mapper/tscxk/xml/*.xml");
        mapperLocations.add("classpath*:mapper/tscxk/*.xml");

        List<Resource> resources = new ArrayList<>();

        if(mapperLocations != null){
            for (String mapperLocation : mapperLocations) {
                try {
                    Resource[] mappers = resourcePatternResolver.getResources(mapperLocation);
                    resources.addAll(Arrays.asList(mappers));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        for (int i = 0; i < resources.size(); i++) {
            LOGGER.info("tscxk数据库获取到xml文件:{}",resources.get(i));
        }

        return resources.toArray(new Resource[resources.size()]);
    }

}
