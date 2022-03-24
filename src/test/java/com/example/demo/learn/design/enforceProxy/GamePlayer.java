package com.example.demo.learn.design.enforceProxy;

/**
 * @ClassName GamePlayer
 * @Description TODO
 * @Author: zhangsp
 * @date 2022/3/22 20:48
 * @Version 1.0
 */
public class GamePlayer implements IGamePlayer {

    private IGamePlayer proxy;

    private String name;

    public GamePlayer(String name) {
        this.name = name;
    }

    @Override
    public void login(String name, String password) {
        if(isProxy()){
            this.name = name;
            System.out.println(name + "登录成功！");
            return;
        }
        reject();
    }

    @Override
    public void killBoss() {
        if(isProxy()){
            System.out.println(name + "开始打怪...");
            return;
        }
        reject();
    }

    @Override
    public void upgrade() {
        if(isProxy()){
            System.out.println("恭喜" + name + "，你经过打怪，升级了！");
            return;
        }
        reject();
    }

    @Override
    public IGamePlayer getProxy() {
        IGamePlayer proxy = new GamePlayerProxy(this);
        this.proxy = proxy;
        return proxy;
    }

    private boolean isProxy(){
        return proxy != null;
    }

    private void reject(){
        System.out.println("对不起，请找我的代理人。。。");
    }
}
