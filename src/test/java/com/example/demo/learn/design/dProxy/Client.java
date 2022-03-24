package com.example.demo.learn.design.dProxy;

import com.example.demo.learn.design.enforceProxy.GamePlayer;
import com.example.demo.learn.design.enforceProxy.IGamePlayer;

/**
 * @ClassName Client
 * @Description TODO
 * @Author: zhangsp
 * @date 2022/3/22 22:20
 * @Version 1.0
 */
public class Client {

    public static void main(String[] args) {
        GamePlayer player = new GamePlayer("x");

        DynamicProxy proxy = new DynamicProxy();

        IGamePlayer newProxy = (IGamePlayer)proxy.newProxy(player);

        newProxy.login("lucy", "123456");

        newProxy.killBoss();

        newProxy.upgrade();
    }


}
