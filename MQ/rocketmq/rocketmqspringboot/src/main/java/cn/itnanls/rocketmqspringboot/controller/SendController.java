package cn.itnanls.rocketmqspringboot.controller;

import cn.itnanls.rocketmqspringboot.demain.User;
import org.apache.logging.log4j.message.Message;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/demo")
public class SendController {

    @Autowired
    private RocketMQTemplate rocketmMQTemplate;

    @RequestMapping("/send")
    public String send() {

        User user = new User("jack", 20);
        rocketmMQTemplate.convertAndSend("topic10", user);

        // 同步消息
        SendResult syncSend = rocketmMQTemplate.syncSend("topic10", user);

        // 异步消息
        rocketmMQTemplate.asyncSend("topic10", user, new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                System.out.println(sendResult);
            }

            @Override
            public void onException(Throwable e) {
                System.out.println(e);
            }
        }, 1000);

        // 单向消息
        rocketmMQTemplate.sendOneWay("topic10", user);

        // 延时消息
        String mgs = "delay message";
        rocketmMQTemplate.syncSend("topic10", MessageBuilder.withPayload(mgs).setHeader("delayTime", 30000).build(), 2000, 3);

        return "send message success";
    }

}
