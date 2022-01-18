package com.example.demo.thread.communication;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author banyue
 * date 2020-07-29
 */
public class ThreadCommunication {

    private int i;

    private final ReentrantLock lock = new ReentrantLock();

    private final Condition condition =lock.newCondition();

    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newFixedThreadPool(10);
        ThreadCommunication ss = new ThreadCommunication();

        threadPool.submit(new IncrThread(ss));
        threadPool.submit(new DecrThread(ss));
        threadPool.submit(new IncrThread(ss));
        threadPool.submit(new DecrThread(ss));
        threadPool.shutdown();
        try {
            threadPool.awaitTermination(300, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }




    public void incr(){
        try {
            //上锁
            lock.lock();
            while(i!=0){
                //叫停，等待唤醒的信号
                condition.await();
            }
            //说明已经得到可以用的信号
            i++;
            System.out.println( "递增:"+i);
            //给其他添加信号
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally{
            //解锁
            lock.unlock();
        }
    }

    public void decr(){
        try {
            //上锁
            lock.lock();
            while(i==0){
                //叫停， 等待递增那边的信号
                condition.await();
            }
            //i !=0  拿到那边的信号
            i--;
            System.out.println( "递减:"+i);
            //给其他添加信号
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally{
            //解锁
            lock.unlock();
        }
    }


}
class IncrThread extends Thread{
    private ThreadCommunication resource;

    public IncrThread(ThreadCommunication resource) {
        this.resource = resource;
    }

    @Override
    public void run() {
        for (int i = 0; i <20; i++) {
            //每次停顿一秒
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            resource.incr();
        }
    }
}

class DecrThread extends Thread{
    private ThreadCommunication resource;

    public DecrThread(ThreadCommunication resource) {
        this.resource = resource;
    }

    @Override
    public void run() {
        for (int i = 0; i <20; i++) {
            //每次停顿一秒
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            resource.decr();
        }
    }
}
