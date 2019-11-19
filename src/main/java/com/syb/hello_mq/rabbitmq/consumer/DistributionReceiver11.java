package com.syb.hello_mq.rabbitmq.consumer;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class DistributionReceiver11 {


    /**
     * 消费者A,根据生产者生产的.符号，决定休眠多少秒
     */
    @SuppressWarnings("deprecation")
    @RabbitListener(queues = "distribu")
    public void processA(Message message) {
        String msg = new String(message.getBody());
        System.out.println(" DistributionReceiverA  : " + msg);
        SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSSS");
        System.out.println(" ProccessingA... at " + time.format(new Date()));

        try {
            for (char ch : msg.toCharArray()) {
                if (ch == '.') {
                    doWork(1000);
                }
            }
        } catch (InterruptedException e) {
        } finally {
            System.out.println(" A Done! at " + time.format(new Date()));
        }
    }

    private void doWork(long time) throws InterruptedException {
        Thread.sleep(time);
    }

}