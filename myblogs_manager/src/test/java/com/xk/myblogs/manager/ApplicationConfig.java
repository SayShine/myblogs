package com.xk.myblogs.manager;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author: tian
 * @date: 2020/6/30 10:45
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationConfig {

    @Before
    public void init(){
        System.out.println("初始化。。。");
    }

    @After
    public void after(){
        System.out.println("结束了。。。");
    }
}
