package com.syb.hello_mq.rabbitmq.producer;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class TransactionSender1 {

    private final static String QUEUE_NAME = "transition";

    public static void main(String[] args) throws IOException, TimeoutException {
        /**
         * 创建连接连接到MabbitMQ
         */
        ConnectionFactory factory = new ConnectionFactory();

        // 设置MabbitMQ所在主机ip或者主机名
        factory.setUsername("lansun");
        factory.setPassword("123456");
        factory.setHost("127.0.0.1");
        factory.setVirtualHost("/");
        factory.setPort(5672);

        // 创建一个连接
        Connection connection = factory.newConnection();

        // 创建一个频道
        Channel channel = connection.createChannel();

        // 指定一个队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        // 发送的消息
        String message = "This is a transaction message！";

        try {
            // 开启事务
            channel.txSelect();
            // 往队列中发出一条消息，使用rabbitmq默认交换机
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            // 提交事务
            channel.txCommit();
        } catch (Exception e) {
            e.printStackTrace();
            // 事务回滚
            channel.txRollback();
        }

        System.out.println(" TransactionSender1 Sent '" + message + "'");
        // 关闭频道和连接
        channel.close();
        connection.close();
    }

}