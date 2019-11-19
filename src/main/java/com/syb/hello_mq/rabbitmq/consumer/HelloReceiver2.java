package com.syb.hello_mq.rabbitmq.consumer;

import java.io.IOException;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.rabbitmq.client.Channel;

@Component
public class HelloReceiver2 {
    @RabbitListener(queues = "hello")
    public void process(Message message, Channel channel) throws IOException {
        System.out.println("Receiver2 : " + new String(message.getBody()));

        // 以下代码需要去配置文件开启手动确认属性

        // 消息的标识，false只确认当前一个消息收到，true确认所有consumer获得的消息
//        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);

        // true 发送给下一个消费者
        // false 谁都不接受，从队列中删除
        // 拒绝消息，RabbitMQ把消息发送给下一个监听hello的队列(HelloReceiver2或CheckReceiver)
//        channel.basicReject(message.getMessageProperties().getDeliveryTag(), true);
    }
}