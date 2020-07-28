package com.xk.myblogs.service;

import com.xk.myblogs.client.entity.nosql.User;

/**
 * ActiveMqService服务接口
 * @author: tian
 * @date: 2020/7/22 9:54
 */
public interface ActiveMqService {
    //发送消息
    public void sendMsg(String message);

    //接收消息
    public void receiveMsg(String message);

    //发送对象消息
    public void sendUser(User user);

    //接收对象消息
    public void receiveUser(User user);
}
