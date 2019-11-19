package com.syb.hello_mq.rabbitmq.producer;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class TransactionSender2 {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    @Transactional(rollbackFor = Exception.class)
    public void send(String msg) {
        SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String sendMsg = msg + time.format(new Date()) + " This is a transaction message！ ";
        /**
         * 这里可以执行数据库操作
         *
         **/
        // int i=1/0;  // 模拟异常，如果这里除了一场，比如数据库，事务会回滚，并且消息并不会发送出去
        System.out.println("TransactionSender2 : " + sendMsg);
        this.rabbitTemplate.convertAndSend("transition", sendMsg);
    }

}