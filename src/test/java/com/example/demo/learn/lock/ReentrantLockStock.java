package com.example.demo.learn.lock;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author banyue
 * date 2020-07-07
 * ReentrantLock 生产者消费者模式
 */
public class ReentrantLockStock implements Stock{

    private LinkedList<String> stock = new LinkedList();

    private ReentrantLock reentrantLock = new ReentrantLock();

    private Condition noEmpty = reentrantLock.newCondition();

    private Condition noFull = reentrantLock.newCondition();


    @Override
    public String take() {

        String good = null;
        try{
            //获取锁
            reentrantLock.tryLock();
            //判断是否有商品
            while (stock.isEmpty()){
                System.out.println("暂无商品");
                try {
                    noEmpty.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //消耗商品
            good = stock.pop();

            System.out.println("消耗商品" + good);
            //通知生产
            noFull.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //释放锁
            reentrantLock.unlock();
        }
        return good;
    }

    @Override
    public void put(String good) {
        try{
            //获取锁
            reentrantLock.tryLock();
            //判断库存是否已满
            while (stock.size() == MAX){
                System.out.println("仓库已满");
                try {
                    noFull.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //生产商品
            stock.push(good);
            System.out.println("生产商品" + good);
            noEmpty.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            reentrantLock.unlock();
        }
    }





}
