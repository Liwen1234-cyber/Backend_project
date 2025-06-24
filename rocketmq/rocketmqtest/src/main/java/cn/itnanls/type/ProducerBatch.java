package cn.itnanls.type;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.w3c.dom.ls.LSInput;

import java.util.ArrayList;
import java.util.List;

public class ProducerBatch {
    public static void main(String[] args) throws Exception {
        //1.创建一个发送消息的对象Producer
        DefaultMQProducer producer = new DefaultMQProducer("group1");
        //2.设定发送的命名服务器地址
        producer.setNamesrvAddr("localhost:9876");
        //3.1启动发送的服务
        producer.start();
        //4.创建要发送的消息对象,指定topic，指定内容body

        List<Message> messageList = new ArrayList<>();

        String msg = "hello rocketmq";

        Message message = new Message("topic7", "tag1", msg.getBytes("UTF-8"));
        messageList.add(message);
        Message message2 = new Message("topic7", "tag1", msg.getBytes("UTF-8"));
        messageList.add(message2);
        Message message3 = new Message("topic7", "tag1", msg.getBytes("UTF-8"));
        messageList.add(message3);

        SendResult result = producer.send(messageList);
        System.out.println(result);

    }
}
