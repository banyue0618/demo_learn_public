package com.example.demo.learn.design.enforceProxy;

/**
 * @ClassName IGamePlayer
 * @Description TODO
 * @Author: zhangsp
 * @date 2022/3/22 20:47
 * @Version 1.0
 */
public interface IGamePlayer {

    void login(String name, String password);

    void killBoss();

    void upgrade();

    // 相比普通代理，增加了一个获取代理人的角色
    IGamePlayer getProxy();
}
