package com.xk.myblogs.common.annotion;

import java.lang.annotation.*;

/**
 * 自定义注解
 * @Author: tian
 * @Date: 2020/7/28 17:26
*/
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface TestAnnotion {
    String value() default "first one";
}
