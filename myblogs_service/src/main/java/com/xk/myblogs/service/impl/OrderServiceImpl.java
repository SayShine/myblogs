package com.xk.myblogs.service.impl;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

/**
 * 订单业务服务
 * @author: tian
 * @date: 2020/8/4 22:28
 */
@Service
public class OrderServiceImpl implements ApplicationContextAware {
    private ApplicationContext applicationContext;

    /**
     * 获取今日订单数
     * @return
     */
    public CompletableFuture<String> todayOrderCount(){
        String name = "hah";
        return CompletableFuture.supplyAsync(() -> this.getTodayOrderCount(name));
    }

    /**
     * 获取今日营业额
     */
    public CompletableFuture<String> todayTurnover(){
        return CompletableFuture.supplyAsync(this::getTodayTurnover);
    }

    /**
     * 获取总营业额
     * @return
     */
    public CompletableFuture<String> totalTurnover(){
        return CompletableFuture.supplyAsync(this::getTotalTurnover, getExecutor());
    }

    private String getTodayOrderCount(String name){
        System.out.println(name);
        System.out.println(">>>> 查询今日订单数： " + Thread.currentThread().getName());
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "50";
    }

    private String getTodayTurnover(){
        System.out.println(">>>> 查询今日交易额： " + Thread.currentThread().getName());
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "200";
    }

    private String getTotalTurnover(){
        System.out.println(">>>> 查询总交易额： " + Thread.currentThread().getName());
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "800";
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    private Executor getExecutor(){
        return (Executor) applicationContext.getBean("myThreadPool");
    }
}
