package com.xk.myblogs.common.demo.functional;

import java.util.function.Predicate;

/**
 * @author: tian
 * @date: 2020/8/5 18:08
 */
public class PrecidateDemo02 {
    public static void main(String[] args) {
        boolean b1 = checkStringandor("helloWorld", s -> s.length() > 8, s -> s.length() < 15);
        System.out.println(b1);
    }

    private static boolean checkStringandor(String s, Predicate<String> pre1, Predicate<String> pre2){
//        return pre1.or(pre2).test(s);
        return pre1.and(pre2).test(s);

    }
}
