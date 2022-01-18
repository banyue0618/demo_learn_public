package com.example.demo.learn;

import com.example.demo.learn.designPattern.Singleton;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Pattern;

/**
 * @author banyue
 * date 2020-04-22
 */
public class SingletonTest {

    public static void main(String[] args){
        String badStr = "'|and|exec|execute|insert|select|delete|update|count|drop|%|chr|mid|master|truncate|" +
                "char|declare|sitename|net user|xp_cmdshell|;|or|,|like'|and|exec|execute|insert|create|drop|" +
                "table|from|grant|use|group_concat|column_name|" +
                "information_schema.columns|table_schema|union|where|select|delete|update|order|by|count|" +
                "chr|mid|master|truncate|char|declare|or|;|-|--|,|like|//|/|%|#";
        System.out.println(Pattern.matches(badStr,"/"));
//        createCachedThreadPool();

    }

    /**
     * 创建无边界大小的线程池
     */
    public static void createCachedThreadPool() {
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        final CountDownLatch countDownLatch = new CountDownLatch(10);
        for (int i = 0; i < 10; i++) {
            final int currentIndex = i;
            cachedThreadPool.execute(() -> {
                Singleton singleton = Singleton.getInstance();

                System.out.println(Thread.currentThread().getName() + ", currentIndex is : " + currentIndex);
                System.out.println(singleton);

                countDownLatch.countDown();
            });
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("全部线程执行完毕");
    }



}
