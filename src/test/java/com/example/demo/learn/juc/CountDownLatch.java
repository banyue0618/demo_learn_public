package com.example.demo.learn.juc;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class CountDownLatch {

    private final static Random random = new Random();

    public static void main(String[] args) {
        List<Integer> taskCount = Arrays.asList(1,2,3,4,5,6,7);
        java.util.concurrent.CountDownLatch countDownLatch = new java.util.concurrent.CountDownLatch(7);
        for(Integer k : taskCount){
            Thread thread = new Thread(new Task(k, countDownLatch));
            thread.start();
//            thread.run();
//            Task task = new Task(k, countDownLatch);
//            task.run();
        }
        try {
            countDownLatch.await();
            System.out.println("-------------------->");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("龙珠寻找完毕");
    }





    static class Task implements Runnable{

        private int id;
        private java.util.concurrent.CountDownLatch latch;

        public Task(int id, java.util.concurrent.CountDownLatch latch) {
            this.id = id;
            this.latch = latch;
        }

        @Override
        public void run() {
            System.out.println("开始寻找" + id + "号龙珠");
            int seconds = random.nextInt(1000);
            try {
                Thread.sleep(seconds);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("" + id + "号龙珠寻找结束,耗时：" + seconds + "ms。");
            latch.countDown();
        }
    }

}

