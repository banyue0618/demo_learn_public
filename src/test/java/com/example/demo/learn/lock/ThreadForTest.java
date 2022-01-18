package com.example.demo.learn.lock;

/**
 * @author banyue
 * date 2020-07-07
 */
public class ThreadForTest {

    static byte[] lock = new byte[1];
    /**
     * @param args
     */
    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<50;i++){
                    sunThread(i);
                }
            }
        }).start();
        for(int i=0;i<50;i++){
            mainThread(i);
        }
    }

    static boolean shouldSub = false;

    static void mainThread(int j){
        System.out.println("---主线程---第几次循环----"+j);
        synchronized (lock) {
            if(shouldSub){
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            for(int i=0;i<10;i++){
                System.out.println("这是主线程"+Thread.currentThread().getName()+"第------"+i+"------次循环");
            }
            shouldSub = true;
            lock.notify();
        }
    }

    static void sunThread(int j){
        System.out.println("--子线程----第几次循环----"+j);
        synchronized (lock) {
            if(!shouldSub){
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            for(int i=0;i<10;i++){
                System.out.println("这是子线程"+Thread.currentThread().getName()+"第------"+i+"------次循环");
            }
            shouldSub = false;
            lock.notify();
        }

    }

}
