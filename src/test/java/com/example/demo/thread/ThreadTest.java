package com.example.demo.thread;

/**
 * @author banyue
 * date 2020-07-28
 */
public class ThreadTest {

    public static void main(String[] args) throws InterruptedException {

//        Thread thread1 = new Thread(new TicketTest());
//        thread1.start();

//        thread1.join();
        Thread thread12 = new Thread(new TicketTest());

        thread12.start();

//        thread12.join();

        Thread thread13 = new Thread(new TicketTest());
        thread13.start();
//        System.out.println("“13 没得票卖");
        thread12.join();
        Thread thread14 = new Thread(new TicketTest());
        thread14.start();

//        System.out.println("“14 没得票卖");


//        thread1.join();
//
//        thread12.join();
//
//        thread13.join();
//
        thread14.join();
    }

}
