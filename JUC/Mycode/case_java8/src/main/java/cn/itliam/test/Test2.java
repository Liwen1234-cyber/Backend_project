package cn.itliam.test;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

@Slf4j(topic = "c.Test2")
public class Test2 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask futureTask = new FutureTask<>( new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                log.info("call running");
                Thread.sleep(3000);
                return 100;
            }
        });

        Thread thread = new Thread(futureTask, "futureTask-thread");
        thread.start();

        log.info("{}", futureTask.get());

    }
}
