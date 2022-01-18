package com.example.demo.thread;

/**
 * @author banyue
 * date 2020-07-28
 */
public class TicketTest implements Runnable{

    public static int ticket = 500;

    @Override
    public void run() {
        while (true) {
            synchronized(" "){
                if(ticket == 0){
                    break;
                }
                System.out.println(Thread.currentThread().getName() + "车站已卖出第" + (501-ticket) + "张车票");
                ticket--;
                try {
                    Thread.sleep(100);
                    if(ticket == 450){
                        Thread.currentThread().wait();
                    }
                    if(ticket == 250){
                        Thread.currentThread().notifyAll();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
