package com.example.demo.common.component;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

/**
 * @author banyue
 * date 2020-09-03
 */
@EnableAsync
@Component
public class AsyncTask {

    @Async
    public void task1() throws InterruptedException {
        System.out.println(Thread.currentThread().getName());
        long currentTimeMillis = System.currentTimeMillis();
        Thread.sleep(3000);
        System.out.println("task1任务耗时" + (System.currentTimeMillis() - currentTimeMillis));
    }
    @Async
    public void task2() throws InterruptedException {
        System.out.println(Thread.currentThread().getName());
        long currentTimeMillis = System.currentTimeMillis();
        Thread.sleep(5000);
        System.out.println("task2任务耗时" + (System.currentTimeMillis() - currentTimeMillis));
    }
    @Async
    public void task3() throws InterruptedException {
        System.out.println(Thread.currentThread().getName());
        long currentTimeMillis = System.currentTimeMillis();
        Thread.sleep(2000);
        System.out.println("task3任务耗时" + (System.currentTimeMillis() - currentTimeMillis));
    }

}
