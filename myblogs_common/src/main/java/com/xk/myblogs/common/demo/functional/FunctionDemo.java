package com.xk.myblogs.common.demo.functional;

import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

/**
 * 函数型接口Demo
 * @author: tian
 * @date: 2020/8/5 18:11
 */
public class FunctionDemo {
    public static void main(String[] args) {
        Convert("100", s -> Integer.parseInt(s));
        Convert("100", Integer::parseInt);
        Convert("300", Integer::parseInt, s -> String.valueOf(s+300));

    }


    //定义一个方法，把一个字符串转换int类型，在控制台输出
    private static void Convert(String s, Function<String, Integer> fun) {
//        Integer i = fun.apply(s);
        int i = fun.apply(s);
        System.out.println(i);
    }


    //定义一个方法，把一个int类型的数据加上一个整数之后，转为字符串在控制台输出
    private static void Convert(int i, Function<Integer, String> fun) {
        String s = fun.apply(i);
        System.out.println(s);
    }


    //定义一个方法，把一个字符串转换int类型，把int类型的数据加上一一个整数之后,
    // 转为字符串在控制台输出
    private static void Convert(String s,Function<String,Integer> fun1,Function<Integer,String> fun2) {
//        Integer i = fun1.apply(s);
//        String s1 = fun2.apply(i);
//        System.out.println(s1);
        String s1 = fun1.andThen(fun2).apply(s);
        System.out.println(s1);
    }
}
