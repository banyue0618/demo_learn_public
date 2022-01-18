package com.example.demo.learn.lock;

/**
 * @author banyue
 * date 2020-07-07
 *
 * 生产者消费者模式 生产者
 */
public class Producer implements Runnable {

    //商品
    private Stock stock;

    public Producer (Stock stock){
        this.stock = stock;
    }

    public Producer() {

    }


    @Override
    public void run() {
        //一直生产商品
        while (true) {
            String good = "巧克力" + System.currentTimeMillis() % 100 ;
            stock.put(good);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
