package cn.itliam.test;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "c.Test17")
public class Test17 {
    public static void main(String[] args) throws InterruptedException {
        Room room = new Room();
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 5000; i++) {
                room.increment();
            }
        }, "t1");

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 2500; i++) {
                room.decrement();
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            for (int i = 0; i < 2500; i++) {
                room.decrement();
            }
        }, "t2");

        t1.start();
        t2.start();
        Thread.sleep(500);
        log.debug("{}", room.getCounter());
        t1.join();
        t2.join();
        log.debug("{}", room.getCounter());
    }
}

class Room {
    private int counter = 0;

    public void increment() {
        synchronized (this){
            counter++;
        }
    }

    public void decrement() {
        synchronized (this) {
            counter--;
        }
    }

    public synchronized int getCounter() {
        return counter;
    }
}
