package com.xk.myblogs.manager.component;

import com.alibaba.fastjson.JSONObject;
import com.xk.myblogs.manager.pojo.WebLog;
import io.swagger.annotations.ApiOperation;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 统一日志处理切面
 * @author: tian
 * @date: 2020/6/12 10:00
 */
@Aspect
@Component
@Order(1)
public class WebLogAspect {
    public static final Logger LOGGER = LoggerFactory.getLogger(WebLogAspect.class);

    //定义切点表达式 格式：execution(方法修饰符 返回类型 方法所属的包.类名.方法名称(方法参数)
    @Pointcut("execution(public * com.xk.myblogs.manager.controller.*.*(..))")
    public void webLog() { }

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable{
        //“JoinPoint对象封装了SpringAop中切面方法的信息,在切面方法中添加JoinPoint参数,
        // 就可以获取到封装了该方法信息的JoinPoint对象. ”
        //不是Joinpoint   而是 JoinPoint ！！
        // 如果写成Joinpoint 会报错 “error at ::0 formal unbound in pointcut ”

    }

    @AfterReturning(value = "webLog()", returning = "ret")
    public void doAfterReturning(Object ret){

    }

    @Around("webLog()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        //开始执行时间
        long startTime =System.currentTimeMillis();

        //非controller层获取当前对象
        ServletRequestAttributes attributes =(ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        //记录请求信息
        WebLog webLog = new WebLog();
        //环绕通知 ProceedingJoinPoint 执行proceed方法的作用是让目标方法执行
        Object result = joinPoint.proceed();
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature)signature;
        Method method = methodSignature.getMethod();
        // A.isAnnotationPresent(B.class)；意思就是：注释B是否在此A上。如果在则返回true；不在则返回false。
        if(method.isAnnotationPresent(ApiOperation.class)){
            ApiOperation apiOperation =method.getAnnotation(ApiOperation.class);
            webLog.setDescription(apiOperation.value());
        }
        long endTime = System.currentTimeMillis();

        String urlStr = request.getRequestURL().toString();
        //打印url
        LOGGER.info(urlStr);
        //request.getRemoteUser();//获取当前缓存的用户，比如Spring Security做权限控制后就会将用户登录名缓存到这里
        webLog.setIp(request.getRemoteUser());
        webLog.setResult(result);
        webLog.setSpendTime((int) (endTime - startTime));
        webLog.setStartTime(startTime);
        webLog.setUri(request.getRequestURI());
        webLog.setUrl(urlStr);
        //这里比较重要 获取方法参数
        webLog.setParameter(getParameter(method, joinPoint.getArgs()));
        LOGGER.info("{}", JSONObject.toJSONString(webLog));

        return result;


    }

    /**
     * 根据方法和传入的参数获取请求参数
     * @param method
     * @param args
     * @return
     */
    private Object getParameter(Method method, Object[] args){
        List<Object> argList = new ArrayList<>();
        //返回一个参数对象数组，该数组表示该方法对象的所有参数
        Parameter[] parameters = method.getParameters();
        for (int i = 0; i < parameters.length; i++) {

            //寻找参数中类型为requestbody的注解参数
            RequestBody requestBody = parameters[i].getAnnotation(RequestBody.class);
            if(requestBody!=null){
                //测试语句
                System.out.println("args[i]: " + args[i] + ", parameters[i]: " + parameters[i]);
                argList.add(args[i]);
            }

            //寻找参数中类型为RequestParam的注解参数
            RequestParam requestParam = parameters[i].getAnnotation(RequestParam.class);
            if(requestParam!=null){
                Map<String,Object> map = new HashMap<>();
                String key = parameters[i].getName();
                if(!StringUtils.isEmpty(requestParam.value())){
                    key = requestParam.value();
                }
                map.put(key,args[i]);
                argList.add(map);
            }

        }

        if (argList.size() == 0) {
            return null;
        } else if (argList.size() == 1) {
            return argList.get(0);
        } else {
            return argList;
        }
    }


}
