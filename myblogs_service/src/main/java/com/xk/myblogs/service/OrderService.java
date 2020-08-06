package com.xk.myblogs.service;

import java.util.concurrent.CompletableFuture;

/**
 * @author: tian
 * @date: 2020/8/6 23:12
 */
public interface OrderService {

    public CompletableFuture<String> todayOrderCount();

    public CompletableFuture<String> todayTurnover();

    public CompletableFuture<String> totalTurnover();
}
