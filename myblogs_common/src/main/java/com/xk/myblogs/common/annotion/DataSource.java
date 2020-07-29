package com.xk.myblogs.common.annotion;

import java.lang.annotation.*;

/**
 * 动态数据源注解
 * @Author: tian
 * @Date: 2020/7/29 10:20
*/
@Documented
@Target({ElementType.METHOD, ElementType.TYPE, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface DataSource {
    String value() default "myblog"; //默认数据库名称
}
