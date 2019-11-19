package com.syb.hello_mq.rabbitmq.producer;

import com.syb.hello_mq.rabbitmq.entity.User;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserSender {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send() {
        User user = new User();
        user.setName("anumbrella");
        user.setAddress("China");
        System.out.println("user send : " + user.getName() + "/" + user.getAddress());
        this.rabbitTemplate.convertAndSend("user", user);
    }
}
