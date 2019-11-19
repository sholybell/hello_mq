package com.syb.hello_mq.rabbitmq.consumer;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;


@Component
public class DistributionReceiver12 {

    /**
     * 消费者B
     */
    @SuppressWarnings("deprecation")
    @RabbitListener(queues = "distribu")
    public void processB(Message message) {
        String msg = new String(message.getBody());
        System.out.println(" DistributionReceiverB  : " + msg);
        SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSSS");
        System.out.println(" ProccessingB... at " + time.format(new Date()));

        try {
            for (char ch : msg.toCharArray()) {
                if (ch == '.') {
                    doWork(1000);
                }
            }
        } catch (InterruptedException e) {
        } finally {
            System.out.println(" B Done! at " + time.format(new Date()));
        }
    }

    private void doWork(long time) throws InterruptedException {
        Thread.sleep(time);
    }

}