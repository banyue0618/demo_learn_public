package com.example.demo.learn.design.run;

import com.example.demo.learn.design.dProxy.DynamicProxy;
import com.example.demo.learn.design.enforceProxy.GamePlayer;
import com.example.demo.learn.design.enforceProxy.IGamePlayer;
import com.example.demo.learn.design.proxy.GamePlayerProxy;
import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @ClassName Client
 * @Description TODO
 * @Author: zhangsp
 * @date 2022/3/22 20:23
 * @Version 1.0
 */
public class Client {

    /**
     * 普通代理, 隐藏真实对象
     */
    @Test
    public void execute(){

        GamePlayerProxy proxy = new GamePlayerProxy("xiaoli");

        proxy.login("lucy", "123456");

        proxy.killBoss();

        proxy.upgrade();

    }

    /**
     * 强制代理  重点是提供一个真实对象，但是无法直接调用真实对象进行任何操作，同样需要获取指定代理对象才能进行相应操作，（也就是代理对象被必须是真实对象返回）。最后必须获取真实对象的代理对象才能进行访问.
     */
    @Test
    public void execute2(){
        GamePlayer gamePlayer = new GamePlayer("Lucy");
//        gamePlayer.login("lucy", "123456");
//        gamePlayer.killBoss();
//        gamePlayer.upgrade();

//        com.example.demo.learn.design.enforceProxy.GamePlayerProxy proxy = new com.example.demo.learn.design.enforceProxy.GamePlayerProxy(gamePlayer);
//        proxy.login("lucy", "123456");
//        proxy.killBoss();
//        proxy.upgrade();

        IGamePlayer proxy = gamePlayer.getProxy();
        proxy.login("lucy", "123456");
        proxy.killBoss();
        proxy.upgrade();
    }

    /**
     * 动态代理，上面两种代理都是静态代理。
     */
    @Test
    public void execute3(){
        com.example.demo.learn.design.proxy.base.IGamePlayer gamePlayer = new com.example.demo.learn.design.proxy.GamePlayer(new GamePlayerProxy("meng"), "meng");

        InvocationHandler handler = new DynamicProxy();
        // 获得 gamePlayer 类加载器
        ClassLoader loader = gamePlayer.getClass().getClassLoader();
        // 动态产生一个代理类
        com.example.demo.learn.design.proxy.base.IGamePlayer proxy = (com.example.demo.learn.design.proxy.base.IGamePlayer)Proxy.newProxyInstance(loader, new Class[]{com.example.demo.learn.design.proxy.base.IGamePlayer.class}, handler);

        proxy.login("lucy", "123456");
        proxy.killBoss();
        proxy.upgrade();
    }
}
