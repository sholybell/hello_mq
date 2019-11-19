package com.syb.hello_mq.rabbitmq.consumer;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class TransactionReceiver2 {

    @RabbitListener(queues = "transition2")
    public void process(Message message, Channel channel) throws IOException {
        System.out.println("TransactionReceiver2  : " + new String(message.getBody()));
    }
}
