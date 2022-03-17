package com.example.demo.learn.observer;

import java.util.Observable;

/**
 * @ClassName HanFeiZi
 * @Description TODO
 * @Author: zhangsp
 * @date 2022/3/16 21:25
 * @Version 1.0
 */
public class HanFeiZi extends Observable implements IHanFeiZi  {

    @Override
    public void haveBreakFast() {
        System.out.println("韩非子开始吃饭");
        setChanged();
        super.notifyObservers("韩非子正在吃饭。。。");
    }

    @Override
    public void haveFun() {
        super.notifyObservers("韩非子开始玩耍。。。");
        super.setChanged();
        super.notifyObservers("韩非子正在玩耍。。。");
    }

    @Override
    protected synchronized void setChanged() {
        super.setChanged();
        // 打印出观察者
        int observers = super.countObservers();
        System.out.println("观察者数量：" + observers);

    }
}
