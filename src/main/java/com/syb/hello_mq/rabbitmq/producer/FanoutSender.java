package com.syb.hello_mq.rabbitmq.producer;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FanoutSender {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send() {
        String msgString = "fanoutSender : hello i am anumbrella";
        System.out.println(msgString);
        this.rabbitTemplate.convertAndSend("fanoutExchange", "abcd.ee", msgString);
    }
}