package com.example.demo.common.component;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

public class MultiThreadTransaction {

    private static ExecutorService executorService =  Executors.newCachedThreadPool();


    public static void main(String[] args) {
        try {
            new MultiThreadTransaction().taskExecute();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void taskExecute() throws InterruptedException {

        CountDownLatch mainLatch = new CountDownLatch(10);
        CountDownLatch rollBackLatch = new CountDownLatch(1);

        AtomicBoolean rollBackFlag = new AtomicBoolean(false);


        // 主线程业务执行完毕 如果其他线程也执行完毕 且没有报异常 正在阻塞状态中 唤醒其他线程 提交所有的事务
        for (int i = 0; i < 10; i++) {
            executorService.execute(new ThreadTask(i, mainLatch, rollBackLatch, rollBackFlag));
        }

        // 如果其他线程或者主线程报错 则不会进入if 会触发回滚
        if(!rollBackFlag.get()){
            mainLatch.await();
            rollBackLatch.countDown();;
        }


    }



    class ThreadTask implements Runnable{

        private int id;

        private CountDownLatch mainLatch;

        private CountDownLatch rollBackLatch;

        private AtomicBoolean rollBackFlag;

        public ThreadTask(int id, CountDownLatch mainLatch, CountDownLatch rollBackLatch, AtomicBoolean rollBackFlag) {
            this.id = id;
            this.mainLatch = mainLatch;
            this.rollBackLatch = rollBackLatch;
            this.rollBackFlag = rollBackFlag;
        }

        @Override
        public void run() {

            if(rollBackFlag.get()){
                return;
            }
            try {
                //执行业务代码

                mainLatch.countDown();

                //当前任务执行完毕
                rollBackLatch.await();


                if(rollBackFlag.get()){
                    //回滚
                    System.out.println("线程" + id + "回滚事务");

                }else{
                    //提交
                    System.out.println("线程" + id + "提交事务");

                }

            } catch (InterruptedException e) {
                //出错 放开锁 通知其他线程回滚 并且本地线程进行回滚
                rollBackFlag.set(true);

                rollBackLatch.countDown();
                mainLatch.countDown();

                //本地线程回滚

                System.out.println("线程" + id + "回滚事务");

            }


        }
    }




}
