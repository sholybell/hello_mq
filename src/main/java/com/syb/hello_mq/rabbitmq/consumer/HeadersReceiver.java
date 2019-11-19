package com.syb.hello_mq.rabbitmq.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "headers.A")
public class HeadersReceiver {

    @RabbitHandler
    public void process(String msg) {
        System.out.println("HeadersReceiverA  : " + msg);
    }

}