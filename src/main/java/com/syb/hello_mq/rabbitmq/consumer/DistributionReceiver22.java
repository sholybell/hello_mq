package com.syb.hello_mq.rabbitmq.consumer;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeoutException;

public class DistributionReceiver22 {

    private final static String QUEUE_NAME = "test";

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
        System.out.println("Receiver3 waiting for messages. To exit press CTRL+C");

        // 创建队列消费者
        final Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");

                System.out.println(" DistributionReceiver3  : " + message);
                SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSSS");
                System.out.println(" Proccessing3... at " + time.format(new Date()));
                try {
                    for (char ch : message.toCharArray()) {
                        if (ch == '.') {
                            doWork(4000);
                        }
                    }
                } catch (InterruptedException e) {
                } finally {
                    System.out.println(" DistributionReceiver3 Done! at " + time.format(new Date()));
                }
            }
        };
        channel.basicConsume(QUEUE_NAME, true, consumer);
    }


    private static void doWork(long time) throws InterruptedException {
        Thread.sleep(time);
    }

}