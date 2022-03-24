package com.example.demo.learn.design.proxy;

import com.example.demo.learn.design.proxy.base.IGamePlayer;
import com.example.demo.learn.design.proxy.base.IProxy;

/**
 * @ClassName GamePlayerProxy
 * @Description TODO
 * @Author: zhangsp
 * @date 2022/3/22 20:24
 * @Version 1.0
 */
public class GamePlayerProxy implements IGamePlayer, IProxy {

    private GamePlayer gamePlayer;

    public GamePlayerProxy(String name) {
        this.gamePlayer = new GamePlayer(this, name);
    }

    @Override
    public void login(String name, String password) {
        gamePlayer.login(name, password);
    }

    @Override
    public void killBoss() {
        gamePlayer.killBoss();
    }

    @Override
    public void upgrade() {
        gamePlayer.upgrade();
    }

    @Override
    public void count() {

    }
}
