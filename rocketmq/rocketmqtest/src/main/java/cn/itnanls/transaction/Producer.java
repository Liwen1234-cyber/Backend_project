package cn.itnanls.transaction;

import org.apache.rocketmq.client.producer.*;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;


public class Producer {
    public static void main(String[] args) throws Exception {
        //事务消息使用的生产者是TransactionMQProducer
        TransactionMQProducer producer = new TransactionMQProducer("group1");
        producer.setNamesrvAddr("localhost:9876");
        //添加本地事务对应的监听
        producer.setTransactionListener(new TransactionListener() {
            //正常事务过程
            @Override
            public LocalTransactionState executeLocalTransaction(Message message, Object o)
            {
                System.out.println("事务消息正常执行");
//                return LocalTransactionState.COMMIT_MESSAGE;
                // 数据库出错，事务回滚
                return LocalTransactionState.UNKNOW;

            }

            @Override
            public LocalTransactionState checkLocalTransaction(MessageExt messageExt) {
                System.out.println("事务补偿检查");

                return LocalTransactionState.UNKNOW;
            } }
        );
        producer.start();
        Message msg = new Message("topic13",("事务消息：hello rocketmq ").getBytes("UTF8"));
        SendResult result = producer.sendMessageInTransaction(msg,null);
        System.out.println("返回结果："+result);

    }

}