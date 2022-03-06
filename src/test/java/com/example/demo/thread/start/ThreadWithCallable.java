package com.example.demo.thread.start;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * @ClassName ThreadWithCallable
 * @Description TODO
 * @Author: zhangsp
 * @date 2022/3/6 9:00
 * @Version 1.0
 */
public class ThreadWithCallable implements Callable<Integer> {

    public static void main(String[] args) throws Exception{
        ThreadWithCallable threadWithCallable = new ThreadWithCallable();

        FutureTask futureTask = new FutureTask <>(threadWithCallable);

        Thread thread = new Thread(futureTask, "returnInteger");

        thread.start();

        System.out.println(futureTask.get());
    }

    @Override
    public Integer call() throws Exception {
        System.out.println("调用了call方法...");

        return 100;
    }
}
