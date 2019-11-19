package com.syb.hello_mq.rabbitmq.consumer;

import com.syb.hello_mq.rabbitmq.entity.User;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class UserReceiver {
    @RabbitListener(queues = "user")
    public void process(User user) {
        System.out.println("user receive  : " + user.getName() + " / " + user.getAddress());
    }
}
