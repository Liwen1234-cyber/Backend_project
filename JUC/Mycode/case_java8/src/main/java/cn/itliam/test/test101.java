package cn.itliam.test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author kixuan
 * @version 1.0
 */
public class test101 {
    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        Thread t1 = new Thread(()-> {
            try {
                //判断获取锁是否成功，最多等待1秒
                if(!lock.tryLock(1, TimeUnit.SECONDS)) {
                    System.out.println("获取失败");
                    //获取失败，不再向下执行，直接返回
                    return;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
                //被打断，不再向下执行，直接返回
                return;
            }
            System.out.println("得到了锁");
            lock.unlock();
        });


        lock.lock();
        try{
            t1.start();
            //打断等待
            t1.interrupt();
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}
