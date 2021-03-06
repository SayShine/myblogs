package com.xk.myblogs.service.config.dynamicDataSource;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.*;

/**
 * 动态数据源配置
 * @author: tian
 * @date: 2020/7/28 17:05
 */
@Configuration
//@MapperScan(basePackages = "com.xk.myblogs.service.mapper", sqlSessionFactoryRef = "dynamicSqlSessionFactory")
public class DynamicDatasourceConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger(DynamicDatasourceConfig.class);

    //主数据库
    @Primary
    @Bean(name = "myblogDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.myblog", ignoreInvalidFields = true)
    public DataSource getMyBlogDataSource(){
        return new DruidDataSource();
    }

    //tscxk数据库
    @Bean(name = "tscxkDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.tscxk", ignoreInvalidFields = true)
    public DataSource getTscxkDataSource(){
        return new DruidDataSource();
    }

    //ruoyi数据库
    @Bean(name = "ryvueDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.ryvue", ignoreInvalidFields = true)
    public DataSource getRyVueDataSource(){
        return new DruidDataSource();
    }

    @Bean(name = "dynamicDataSource")
    public DynamicDataSource dataSource(@Qualifier("myblogDataSource")DataSource myblogDataSource,
                                        @Qualifier("tscxkDataSource")DataSource tscxkDataSource,
                                        @Qualifier("ryvueDataSource")DataSource ryvueDataSource){
        Map<Object,Object> targetDataSource = new HashMap<>();

        targetDataSource.put(DataSourceType.DataBaseType.MYBLOG, myblogDataSource);
        targetDataSource.put(DataSourceType.DataBaseType.TSCXK, tscxkDataSource);
        targetDataSource.put(DataSourceType.DataBaseType.RYVUE, ryvueDataSource);

        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        dynamicDataSource.setTargetDataSources(targetDataSource);
        dynamicDataSource.setDefaultTargetDataSource(myblogDataSource);//设置默认对象
        return dynamicDataSource;
    }

    @Bean(name = "dynamicSqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("dynamicDataSource")DataSource dynamicDataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dynamicDataSource);
        bean.setMapperLocations(resoleveMapperLocations());

        //读取mybatis配置文件
//        Resource resource = new PathMatchingResourcePatternResolver().getResource("mybatis-config.xml");
        Resource resource = new ClassPathResource("mybatis-config.xml");
        System.out.println(resource.getFilename());
        bean.setConfigLocation(resource);

        bean.setTypeHandlersPackage("com.xk.myblogs.service.typehandler");
        return bean.getObject();
    }

    public Resource[] resoleveMapperLocations(){
        ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();

        List<String> mapperLocations = new ArrayList<>();
        mapperLocations.add("classpath*:com/xk/myblogs/service/mapper/**/xml/*.xml");
        mapperLocations.add("classpath*:mapper/**/*.xml");

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
            LOGGER.info("搜索到xml文件:{}",resources.get(i));
        }

        return resources.toArray(new Resource[resources.size()]);
    }

}
