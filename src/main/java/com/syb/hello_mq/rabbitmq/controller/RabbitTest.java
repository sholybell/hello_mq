package com.syb.hello_mq.rabbitmq.controller;

import com.syb.hello_mq.rabbitmq.producer.HelloSender1;
import com.syb.hello_mq.rabbitmq.producer.HelloSender2;
import com.syb.hello_mq.rabbitmq.producer.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/rabbit")
public class RabbitTest {

    @Autowired
    private HelloSender1 helloSender1;

    @Autowired
    private HelloSender2 helloSender2;

    /**
     * 单生产者-单消费者
     */
    @GetMapping("/hello")
    public void hello() {
        helloSender1.send("hello1");
    }

    /**
     * 单生产者-多消费者
     */
    @GetMapping("/oneToMany")
    public void oneToMany() {
        for (int i = 0; i < 4; i++) {
            helloSender1.send("第[" + (i + 1) + "]个 ---------> ");
        }
    }

    /**
     * 多生产者-多消费者
     */
    @GetMapping("/manyToMany")
    public void manyToMany() {
        for (int i = 0; i < 4; i++) {
            helloSender1.send("第[" + (i + 1) + "]个 ---------> ");
            helloSender2.send("第[" + (i + 1) + "]个 ---------> ");
        }
    }


    @Autowired
    private TopicSender topicSender;

    @Autowired
    private FanoutSender fanoutSender;

    @Autowired
    private DirectSender directSender;

    @Autowired
    private HeadersSender headersSender;

    @Autowired
    private UserSender userSender;

    /**
     * topic exchange类型rabbitmq测试
     */
    @GetMapping("/topicTest")
    public void topicTest() {
        topicSender.send();
    }

    /**
     * fanout exchange类型rabbitmq测试
     */
    @GetMapping("/fanoutTest")
    public void fanoutTest() {
        fanoutSender.send();
    }

    /**
     * direct exchange类型rabbitmq测试
     */
    @GetMapping("/directTest")
    public void directTest() {
        directSender.send();
    }

    /**
     * headers exchange类型rabbitmq测试
     */
    @GetMapping("/headersTest")
    public void headersTest() {
        headersSender.send();
    }

    /**
     * 实体类，自动序列化测试
     */
    @GetMapping("/userTest")
    public void userTest() {
        userSender.send();
    }


    @Autowired
    private CallBackSender callBackSender;

    /**
     * broker回调生产者确认消息收到
     */
    @GetMapping("/callBackTest")
    public void callBackTest() {
        callBackSender.send();
    }


    @Autowired
    private DistributionSender1 distributionSender;

    /**
     * 分发机制消息发送测试
     */
    @GetMapping("/distribu")
    public void distribu() {
        for (int i = 0; i < 5; i++) {
            //发送任务复杂度都为1的消息
            distributionSender.send(1);
        }
    }


    @Autowired
    private TransactionSender2 transactionSender;

    /**
     * 事务消息发送测试
     */
    @GetMapping("/transition")
    public void transition() {
        transactionSender.send("Transition :");
    }

    @Autowired
    private TransactionSender3 transactionSender3;

    /**
     * 事务消息发送测试
     */
    @GetMapping("/asyncTransition")
    public void asyncTransition() {
        transactionSender3.send("asyncTransition :");
    }
}