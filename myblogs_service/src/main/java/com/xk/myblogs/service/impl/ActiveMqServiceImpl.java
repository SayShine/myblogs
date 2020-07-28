package com.xk.myblogs.service.impl;

import com.xk.myblogs.client.entity.nosql.User;
import com.xk.myblogs.service.ActiveMqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

/**
 * @author: tian
 * @date: 2020/7/22 9:55
 */
@Service
public class ActiveMqServiceImpl implements ActiveMqService {
    //注入spring boot自动生产的template
    @Autowired
    private JmsTemplate jmsTemplate;
    //自定义地址
    private static final String myDestination = "my-destination";


    @Override
    public void sendMsg(String message) {
        System.out.println("发送消息["+ message +"]");
        //发送到activemq的默认地址
        jmsTemplate.convertAndSend(message);
        //自定义发送地址
//        jmsTemplate.convertAndSend("your-destination", message);
    }

    @Override
    @JmsListener(destination = "${spring.jms.template.default-destination}")
    public void receiveMsg(String message) {
        //需要使用注解 监听地址发送过来的消息
        System.out.println("收到消息[" + message + "]");

    }

    @Override
    public void sendUser(User user) {
        //发送对象需要配置 让activemq信任user类
        System.out.println("发送消息["+ user +"]");
        jmsTemplate.convertAndSend(myDestination, user);
    }

    @Override
    @JmsListener(destination = myDestination)
    public void receiveUser(User user) {
        System.out.println("收到消息[" + user + "]");
    }
}
