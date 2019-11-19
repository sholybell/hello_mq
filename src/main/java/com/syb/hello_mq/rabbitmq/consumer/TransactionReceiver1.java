package com.syb.hello_mq.rabbitmq.consumer;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeoutException;

public class TransactionReceiver1 {

    private final static String QUEUE_NAME = "transition";

    public static void main(String[] argv) throws IOException, InterruptedException, TimeoutException {

        ConnectionFactory factory = new ConnectionFactory();

        factory.setUsername("lansun");
        factory.setPassword("123456");
        factory.setHost("127.0.0.1");
        factory.setVirtualHost("/");
        factory.setPort(5672);
        // 打开连接和创建频道，与发送端一样

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        // 声明队列，主要为了防止消息接收者先运行此程序，队列还不存在时创建队列。
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        System.out.println("Receiver1 waiting for messages. To exit press CTRL+C");

        // 创建队列消费者
        final Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
                                       byte[] body) throws IOException {
                SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSSS");

                String message = new String(body, "UTF-8");

                System.out.println(" TransactionReceiver1  : " + message);
                System.out.println(" TransactionReceiver1 Done! at " + time.format(new Date()));
            }
        };
        channel.basicConsume(QUEUE_NAME, true, consumer);
    }

}