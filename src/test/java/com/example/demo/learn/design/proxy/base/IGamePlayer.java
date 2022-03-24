package com.example.demo.learn.design.proxy.base;

/**
 * @ClassName IGamePlayer
 * @Description TODO
 * @Author: zhangsp
 * @date 2022/3/22 20:22
 * @Version 1.0
 */
public interface IGamePlayer {

    void login(String name, String password);

    void killBoss();

    void upgrade();

}
