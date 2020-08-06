package com.xk.myblogs.common.demo.functional;

import java.util.function.Consumer;

/**
 * 消费型函数式
 * @author: tian
 * @date: 2020/8/5 16:13
 */
public class ConsumerDemo {
    public static void main(String[] args) {
        String[] array = {"林青霞,30", "张曼玉,35", "王祖贤,33"};
        printString(array, (String str) ->{
            String name = str.split(",")[0];
            System.out.println("姓名： " + name);
        }, (String str) -> {
            String age = str.split(",")[1];
            System.out.println("年龄： " + age);
        });
    }

    private static void printString(String[] array, Consumer<String> con1, Consumer<String> con2){
        for (String s : array) {
            con1.andThen(con2).accept(s);
        }
    }
}
