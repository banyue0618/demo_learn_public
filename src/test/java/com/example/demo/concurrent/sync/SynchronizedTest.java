package com.example.demo.concurrent.sync;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName SynchronizedTest
 * @Description TODO
 * @Author: zhangsp
 * @date 2022/2/24 11:44
 * @Version 1.0
 */
public class SynchronizedTest {

    public static void main(String[] args) {

        int i = 5;


        Thread why = new Thread(new TicketConsumer(200), "why");
        Thread mx = new Thread(new TicketConsumer(200), "mx");
        why.start();
        mx.start();
    }
}

class TicketConsumer implements Runnable {

    private volatile static Integer ticket;

    private ConcurrentMap <Integer, Object> locks = new ConcurrentHashMap <>();

    public TicketConsumer(int ticket) {
        this.ticket = ticket;
    }

    @Override
    public void run() {
        while (true) {
            System.out.println(Thread.currentThread().getName() + "开始抢第" + ticket + "张票，对象加锁之前：" + System.identityHashCode(ticket));
            synchronized (getCacheSyncObject(ticket)) {
                System.out.println(Thread.currentThread().getName() + "抢到第" + ticket + "张票，成功锁到的对象：" + System.identityHashCode(ticket));
                if (ticket > 0) {
                    try {
                        //模拟抢票延迟
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    int i = ticket--;
                    System.out.println(Thread.currentThread().getName() + "抢到了第" + getCacheSyncObject(i) + "张票，票数减一");
                } else {
                    return;
                }
            }
        }
    }


    private Object getCacheSyncObject(final Integer id){
        locks.putIfAbsent(id, id);
        return locks.get(id);
    }


}
