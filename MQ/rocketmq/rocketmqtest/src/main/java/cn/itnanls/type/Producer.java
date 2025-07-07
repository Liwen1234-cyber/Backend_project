package cn.itnanls.type;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

import java.util.concurrent.TimeUnit;


public class Producer {
    public static void main(String[] args) throws Exception {
        //1.创建一个发送消息的对象Producer
        DefaultMQProducer producer = new DefaultMQProducer("group1");
        //2.设定发送的命名服务器地址
        producer.setNamesrvAddr("localhost:9876");
        //3.1启动发送的服务
        producer.start();
        //4.创建要发送的消息对象,指定topic，指定内容body

        for(int i = 0; i < 10; i++){
            String msg = "hello rocketmq";
            Message message = new Message("topic1", msg, msg.getBytes("UTF-8"));

            producer.send(message, new SendCallback() {
                @Override
                public void onSuccess(SendResult sendResult) {
                    System.out.println(sendResult);
                }

                @Override
                public void onException(Throwable e) {
                    System.out.println(e);
                }

            });
            System.out.println("消息"+i+"发完了，做业务逻辑去了！");
        }


        //休眠10秒
//        TimeUnit.SECONDS.sleep(10);
        //5.关闭连接
        producer.shutdown();
    }

}

