package com.xk.myblogs.manager.component.nouseagecomponent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.concurrent.Executor;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * 监测数据库连接池类型
 * @author: tian
 * @date: 2020/7/8 20:28
 */
@Component
public class DataSourceShow implements ApplicationContextAware {
    private final static Logger LOGGER = LoggerFactory.getLogger(DataSourceShow.class);

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        DataSource dataSource = applicationContext.getBean(DataSource.class);
        Executor executor = (Executor) applicationContext.getBean("myThreadPool");
        LOGGER.info("当前数据库连接池类型：{}",dataSource.getClass().getName());
    }
}
