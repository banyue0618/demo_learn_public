package com.example.demo.thread.test;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * @author banyue
 * date 2020-07-29
 */
public class MyThread2 implements Runnable {
    @Override
    public void run() {
        try {
            System.out.println(Thread.currentThread().getName() + " -> start");
            TimeUnit.SECONDS.sleep(5);
            //随机发生异常
            if (ThreadLocalRandom.current().nextInt(10) > 5) {
                throw new RuntimeException(Thread.currentThread().getName() + "发生异常");
            }
            System.out.println(Thread.currentThread().getName() + " -> end");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
