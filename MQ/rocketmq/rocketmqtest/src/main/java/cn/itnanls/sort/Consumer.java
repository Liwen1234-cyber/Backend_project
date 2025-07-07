package cn.itnanls.sort;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.*;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

public class Consumer {
    public static void main(String[] args) throws MQClientException {
        //1.创建一个接收消息的对象Consumer
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("group1");
        //2.设定接收的命名服务器地址
        consumer.setNamesrvAddr("localhost:9876");
        //3.设置接收消息对应的topic,对应的sub标签为任意
        consumer.subscribe("topic13","*");
        //3.开启监听，用于接收消息
//        consumer.registerMessageListener(new MessageListenerConcurrently() {
//            @Override
//            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt>list,
//                                                            ConsumeConcurrentlyContext consumeConcurrentlyContext) {
//                //遍历消息
//                for (MessageExt msg : list) {
//                    System.out.println("收到消息："+msg);
//                    byte[] body = msg.getBody();
//                    System.out.println(new String(body));
//                }
//                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
//            }
//        });
        // 顺序监听,一个线程监听一个队列
        consumer.registerMessageListener(new MessageListenerOrderly() {
            @Override
            public ConsumeOrderlyStatus consumeMessage(List<MessageExt> list, ConsumeOrderlyContext consumeOrderlyContext) {
                for (MessageExt msg : list) {
                    System.out.println("收到消息："+msg);
                    byte[] body = msg.getBody();
                    System.out.println(new String(body));
                }
                return ConsumeOrderlyStatus.SUCCESS;
            }
        });
        //4.启动接收消息的服务
        consumer.start();
        System.out.println("接受消息服务已经开启！");
        //5 不要关闭消费者！
    }
}
