package com.example.demo.learn.design.lsp;

/**
 * @ClassName Soldier
 * @Description TODO
 * @Author: zhangsp
 * @date 2022/3/21 21:26
 * @Version 1.0
 */
public class Soldier {

    private AbstractGun gun;

    public void setGun(AbstractGun gun) {
        this.gun = gun;
    }

    public void killEnemy(){
        System.out.println("士兵开始杀敌人...");
        gun.shoot();
    }
}
