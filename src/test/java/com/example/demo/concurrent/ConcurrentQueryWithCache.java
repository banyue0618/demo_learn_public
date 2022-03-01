package com.example.demo.concurrent;

import com.example.demo.concurrent.concurentQuery.ScoreQuery;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassName ConcurrentQueryWithCache
 * @Description TODO
 * @Author: zhangsp
 * @date 2022/2/24 10:22
 * @Version 1.0
 */
public class ConcurrentQueryWithCache {

    public static void main(String[] args) {

        int count = 5;

        ExecutorService executorService = Executors.newFixedThreadPool(count);

        Memoizer2 memoizer = new Memoizer2(new ScoreQuery());

        for (int i = 0; i < count; i++) {
            executorService.execute(()->{
                try {
                    Integer score = (Integer)memoizer.compute("banyue");
                    System.out.println("score:" + score);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
        executorService.shutdown();

    }


}
