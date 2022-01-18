package com.example.demo.thread.test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * @author banyue
 * date 2020-07-29
 */
public class ThreadGroupTest {

    private Object object = new Object();

    MyThread2 thread1 = new MyThread2("A");
    MyThread2 thread2 = new MyThread2("B");

    public static void main(String[] args) {
        int num = 10;
        ThreadGroup threadGroup = new ThreadGroup("test-group") {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                System.out.println("线程组中剩余活跃线程数->" + t.getThreadGroup().activeCount());
                System.out.println("ThreadGroup捕获到线程异常 - " + e.getMessage());
            }
        };

        List<Thread> threadList = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            Thread thread = new Thread(threadGroup, new MyThread(), "threadname-" + i);
            threadList.add(thread);
        }

        System.out.println("运行前线程组中活跃线程数 -> " + threadGroup.activeCount());
        System.out.println("开始运行所有线程...");
        for (Thread t : threadList) {
            t.start();
        }
        //获取线程组中所有[活动]线程
        Thread[] threads = new Thread[num];
        threadGroup.enumerate(threads);
        for (Thread t : threads) {
            System.out.println("[活动]线程----->线程名->" + t.getName());
        }
        System.out.println("所有线程运行后,线程组中活跃线程数->" + threadGroup.activeCount());
        //不断的查看线程组中活跃的线程数
        Thread thread = new Thread(() -> {
            int num1;
            try {
                while ((num1 = threadGroup.activeCount()) >= 0) {
                    System.out.println("当前线程组活跃线程数为 -> " + num1);
                    TimeUnit.SECONDS.sleep(1);
                    if(num1 == 0){
                        break;
                    }
                }
                System.out.println("All Thread HAS FINISHED");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread.start();
    }
    class MyThread2 extends Thread{
        int i = 0;
        public MyThread2(String name) {
            super.setName(name);
        }
        @Override
        public void run() {
            synchronized (object) {
                System.out.println(Thread.currentThread().getName()+":"+i++);
                try {
                    System.out.println("线程"+Thread.currentThread().getName()+"进入睡眠状态");
                    Thread.currentThread().sleep(10000);
                } catch (InterruptedException e) {
                    System.out.println("你被中断了");
                }
                System.out.println("线程"+Thread.currentThread().getName()+"被唤醒");
                System.out.println(Thread.currentThread().getName()+":"+i);
            }
        }
    }
}

class MyThread implements Runnable {
        @Override
        public void run() {
            try {
                synchronized(" "){
                    System.out.println(Thread.currentThread().getName() + " -> start");
                    TimeUnit.SECONDS.sleep(3);
                    //随机发生异常
                    if (ThreadLocalRandom.current().nextInt(10) > 5) {
                        throw new RuntimeException(Thread.currentThread().getName() + "发生异常");
                    }
                    System.out.println(Thread.currentThread().getName() + " -> end");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

}

