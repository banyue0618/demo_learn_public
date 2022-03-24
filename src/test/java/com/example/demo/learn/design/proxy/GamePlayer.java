package com.example.demo.learn.design.proxy;

import com.example.demo.learn.design.proxy.base.IGamePlayer;

/**
 * @ClassName GamePlayer
 * @Description 普通代理类设计，隐藏被代理方
 * @Author: zhangsp
 * @date 2022/3/22 20:25
 * @Version 1.0
 */
public class GamePlayer implements IGamePlayer {

    private String name;

    public GamePlayer(IGamePlayer gamePlayer, String name) {
        if(null == gamePlayer){
            throw new RuntimeException("不能创建真实角色");
        }
        this.name = name;
    }

    @Override
    public void login(String name, String password) {
        System.out.println(name + "登录成功！");
    }

    @Override
    public void killBoss() {
        System.out.println(name + "开始打怪...");
    }

    @Override
    public void upgrade() {
        System.out.println("恭喜" + name + "，你经过打怪，升级了！");
    }
}
