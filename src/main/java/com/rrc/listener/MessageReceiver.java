package com.rrc.listener;

import com.rrc.entity.MessageCollect;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @ClassName MessageReceiver
 * @Description TODO
 * @Author wang
 * @Date 2021/6/18 0:35
 * @Version 1.0
 **/
@Component
@Slf4j
public class MessageReceiver {
    /**
     * 处理WebSocket消息
     */
    public void receiveMessage(String redisWebsocketMsg) {
        log.info("Received Message: {0}", redisWebsocketMsg);
    }
}
