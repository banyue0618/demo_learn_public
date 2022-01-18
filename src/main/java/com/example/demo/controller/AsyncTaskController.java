package com.example.demo.controller;

import com.example.demo.common.component.AsyncTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author banyue
 * date 2020-09-03
 */
@RestController
@RequestMapping
public class AsyncTaskController {

    @Autowired
    private AsyncTask asyncTask;


    @RequestMapping
    public void doTask() throws InterruptedException {
        long currentTimeMillis = System.currentTimeMillis();

        asyncTask.task1();
        System.out.println("task01" + (System.currentTimeMillis() - currentTimeMillis));
        asyncTask.task2();
        System.out.println("task02" + (System.currentTimeMillis() - currentTimeMillis));
        asyncTask.task3();
        System.out.println("task03" + (System.currentTimeMillis() - currentTimeMillis));

        System.out.println("task任务总耗时" + (System.currentTimeMillis() - currentTimeMillis));


    }

}
