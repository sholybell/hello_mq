package com.syb.hello_mq.rabbitmq.producer;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 异步事务消息
 */
@Component
public class TransactionSender3 {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    @Transactional(rollbackFor = Exception.class)
    public void send(String msg) {
        SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String sendMsg = msg + time.format(new Date()) + " This is a async transaction message！ ";
        System.out.println("TransactionSender3 : " + sendMsg);
        this.rabbitTemplate.convertAndSend("asyncTransition", sendMsg);
    }

}