package com.example.demo.learn.lock;

/**
 * @author banyue
 * date 2020-07-07
 */
public class Starter {

    public static void main(String[] args) {

        Stock stock = new ReentrantLockStock();

        Producer thread1 = new Producer(stock);
        new Thread(thread1, "Producer").start();


        Consumer thread2 = new Consumer(stock);
        new Thread(thread2, "Consumer").start();




    }
}
