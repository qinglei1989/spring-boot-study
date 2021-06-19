package com.rrc.listener;

import com.rrc.entity.MessageCollect;
import com.rrc.service.IReceiverService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.concurrent.CountDownLatch;

/**
 * @ClassName ReceiverServiceImpl
 * @Description TODO
 * @Author wang
 * @Date 2021/6/17 23:02
 * @Version 1.0
 **/
@Slf4j
@Service
public class ReceiverServiceImpl  implements IReceiverService {

    @Override
    public void collectReceive(MessageCollect messageCollect) {
        log.info("消费收藏数据:[{}]", messageCollect);
        log.info(messageCollect.getToUserId(), "新消息",
                messageCollect.getUserName() + "收藏了您的" + messageCollect.getTitle() + " 作品");
    }

    @Override
    public void commentReceive(MessageCollect messageComment) {
        log.info("消费评论数据:[{}]", messageComment);
        log.info(messageComment.getToUserId(), "您有新的评论",
                messageComment.getUserName() + "对" + messageComment.getTitle() + "进行了评论:");
    }
}
