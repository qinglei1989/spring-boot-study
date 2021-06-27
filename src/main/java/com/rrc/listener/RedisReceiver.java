package com.rrc.listener;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.rrc.entity.MessageCollect;
import lombok.SneakyThrows;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

import java.net.InetAddress;

@Component
public class RedisReceiver implements MessageListener {

    @SneakyThrows
    @Override
    public void onMessage(Message message, byte[] pattern) {
        System.out.println(new String(message.getBody()));
        System.out.println(new String(message.getChannel()));
        MessageCollect msg = JSON.parseObject((String) JSON.parse(new String(message.getBody())), new TypeReference<MessageCollect>() {});
        System.out.println(msg);
        System.out.println("IP=" + InetAddress.getLocalHost().getHostAddress());

        System.out.println("===================");

    }
}