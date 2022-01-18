package com.example.demo.learn.lock;

/**
 * @author banyue
 * date 2020-07-07
 * 生产者消费者模式 消费者
 *
 */
public class Consumer implements Runnable{

    private Stock stock;

    public Consumer(Stock stock){
        this.stock = stock;
    }

    public Consumer() {

    }


    @Override
    public void run() {
        while (true) {
            stock.take();

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
