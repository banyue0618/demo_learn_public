package com.example.demo.learn.lock;

/**
 * @author banyue
 * date 2020-07-07
 */
public interface Stock {
    //定义最大容量
    int MAX = 10;
    //取出商品
    String take();
    //生产商品
    void put(String good);


}
