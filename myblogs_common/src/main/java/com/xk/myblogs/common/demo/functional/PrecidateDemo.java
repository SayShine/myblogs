package com.xk.myblogs.common.demo.functional;

import java.util.function.Predicate;

/**
 * 断言型函数接口
 * @author: tian
 * @date: 2020/8/5 18:05
 */
public class PrecidateDemo {

    public static void main(String[] args) {
        boolean b = checkString("hello", s -> s.length() > 8);
        System.out.println(b);
    }


    private static boolean checkString(String s, Predicate<String> pre){
        //negate 逻辑的否定
//        return pre.negate().test(s);
        return pre.test(s);
    }


}
