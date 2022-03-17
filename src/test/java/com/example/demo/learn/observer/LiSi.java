package com.example.demo.learn.observer;

import java.util.Observable;
import java.util.Observer;

/**
 * @ClassName LiSi
 * @Description TODO
 * @Author: zhangsp
 * @date 2022/3/16 21:30
 * @Version 1.0
 */
public class LiSi implements Observer {
    @Override
    public void update(Observable o, Object arg) {
        System.out.println("李斯开始汇报");
        this.reportToQinShiHuang(arg.toString());
        System.out.println("李斯汇报完毕");
    }


    private void reportToQinShiHuang(String context){
        System.out.println(context);
    }
}
