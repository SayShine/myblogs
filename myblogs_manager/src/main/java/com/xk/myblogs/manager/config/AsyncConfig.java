package com.xk.myblogs.manager.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * 异步线程池配置
 * @author: tian
 * @date: 2020/7/18 11:28
 */
@Configuration
public class AsyncConfig implements AsyncConfigurer {
    @Override
    @Bean(name = "myThreadPool")
    public Executor getAsyncExecutor() {
        System.out.println(Thread.currentThread().getName());
        //定义线程池
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        //核心线程数
        taskExecutor.setCorePoolSize(10);
        //线程池最大线程数
        taskExecutor.setMaxPoolSize(30);
        //线程队列最大线程数
        taskExecutor.setQueueCapacity(50);
        //初始化
        taskExecutor.initialize();
        return taskExecutor;
    }

    public void TestConfiguration() {
        System.out.println("TestConfiguration容器启动初始化。。。");
    }
}
