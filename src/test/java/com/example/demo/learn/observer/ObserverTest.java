package com.example.demo.learn.observer;

/**
 * @ClassName OberverTest
 * @Description TODO
 * @Author: zhangsp
 * @date 2022/3/16 21:33
 * @Version 1.0
 */
public class ObserverTest {

    public static void main(String[] args) {
        HanFeiZi feiZi = new HanFeiZi();

        LiSi liSi = new LiSi();
        feiZi.addObserver(liSi);

        feiZi.haveBreakFast();
    }
}
