package com.xk.myblogs.service.component.aspect;

import com.xk.myblogs.common.annotion.DataSource;
import com.xk.myblogs.service.config.dynamicDataSource.DataSourceType;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;

/**
 * 动态数据源  注解切换 添加数据源需要修改
 * @author: tian
 * @date: 2020/7/29 10:25
 */
@Aspect
@Component
public class DynamicDataSourceAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(DynamicDataSourceAspect.class);

//    @Pointcut("execution(public * com.xk.myblogs.manager.controller.*.*(..))")
    @Pointcut("execution(public * com.xk.myblogs.service.mapper..*.*(..)) " +
            "|| execution(public * com.xk.myblogs.service.dao.*.*(..))")
    public void dao(){
        //定义切点表达式
    }

    //拦截
    @Before(value = "dao()")
    public void changeDataSource(JoinPoint point){
        String value = "";
        //先判断类注解-->判断方法注解 聪明！

        //获取当前类对象 哦吼
        Class currentClass = point.getSignature().getDeclaringType();
        //如果当前类存在自定义注解
        if(currentClass.isAnnotationPresent(DataSource.class)){
            DataSource dataSource = (DataSource)currentClass.getAnnotation(DataSource.class);
            value = dataSource.value();
        }

        //如果当前方法存在自定义注解  （方法注解优先）
        MethodSignature methodSignature = (MethodSignature)point.getSignature();
        Method method = methodSignature.getMethod();
        if(method.isAnnotationPresent(DataSource.class)){
            DataSource dataSource = (DataSource)currentClass.getAnnotation(DataSource.class);
            //值不为空才覆盖
            if(!StringUtils.isEmpty(value)){
                value = dataSource.value();
            }
        }

        if(!StringUtils.isEmpty(value)){
            switch (value){
                case "myblog":
                    DataSourceType.setDataBaseType(DataSourceType.DataBaseType.MYBLOG);
                    break;
                case "tscxk":
                    DataSourceType.setDataBaseType(DataSourceType.DataBaseType.TSCXK);
                    break;
                default:
                    DataSourceType.setDataBaseType(DataSourceType.DataBaseType.MYBLOG);
            }
        }else{
            DataSourceType.setDataBaseType(DataSourceType.DataBaseType.MYBLOG);
        }
    }

    @After(value = "dao()")
    public void restoreDataSource(JoinPoint point){
        DataSourceType.clearDataBaseType();
    }
}
