package com.xk.myblogs.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 定时任务
 * @author: tian
 * @date: 2020/7/22 17:44
 */
@Service
public class ScheduleServiceImpl {
    private static final Logger LOGGER = LoggerFactory.getLogger(ScheduleServiceImpl.class);

    int count1 = 1;
    int count2 = 2;

    //每隔一秒执行一次
//    @Scheduled(cron = "0 * * * * ?")
//    @Async
    public void job1(){
        LOGGER.info("[" + Thread.currentThread().getName() + "]" + "[job1]每秒执行一次," +
                "执行第[" + count1 + "]次");
        count1++;
    }

    //每隔一秒执行一次
//    @Scheduled(fixedRate = 1000)
//    @Async
    public void job2(){
        LOGGER.info("[" + Thread.currentThread().getName() + "]" + "[job2]每秒执行一次," +
                "执行第[" + count2 + "]次");
        count2++;
    }

}
