package com.example.demo.concurrent.concurentQuery;

import com.example.demo.concurrent.Query;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName SocreQuery
 * @Description TODO
 * @Author: zhangsp
 * @date 2022/2/24 10:25
 * @Version 1.0
 */
public class ScoreQuery implements Query<String, Integer> {
    @Override
    public Integer compute(String userName) throws Exception {
        System.out.println("开始查询userName=" + userName + "的分数");
        //模拟耗时
        Thread.yield();
//        TimeUnit.SECONDS.sleep(5);
        return ThreadLocalRandom.current().nextInt(380, 420);
    }
}
