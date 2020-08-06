package com.xk.myblogs.common.demo.functional;

import java.util.concurrent.CompletableFuture;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * 供给型函数式Demo
 * @author: tian
 * @date: 2020/8/5 18:03
 */
public class SupplierDemo {
    public static void main(String[] args) {
        //定义一个数组
        int[] arr = {15, 20, 3, 65, 45, 6};

        int x = getMax(() -> {
            int Max = arr[0];
            for (int i = 0; i < arr.length; i++) {
                if (Max < arr[i]) {
                    Max = arr[i];
                }

            }
            return Max;
        });
        System.out.println(x);

        int y = getMax(() -> 200);
        System.out.println(y);


    }

    private static int getMax(Supplier<Integer> sup) {
        return sup.get();
    }
}
