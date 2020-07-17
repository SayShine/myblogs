package com.xk.myblogs.manager.component.nouseagecomponent;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;

/**
 * Redis消息监听器
 * @author: tian
 * @date: 2020/7/10 22:36
 */
public class RedisMessageListener implements MessageListener {
    @Override
    public void onMessage(Message message, byte[] bytes) {
        //消息体
        String body = new String(message.getBody());
        //渠道名称
        String topic = new String(bytes);

        System.out.println(body);
        System.out.println(topic);
    }
}
