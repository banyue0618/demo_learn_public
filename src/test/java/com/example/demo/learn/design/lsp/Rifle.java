package com.example.demo.learn.design.lsp;

/**
 * @ClassName Rifle
 * @Description TODO
 * @Author: zhangsp
 * @date 2022/3/21 21:25
 * @Version 1.0
 */
public class Rifle extends AbstractGun {
    @Override
    void shoot() {
        System.out.println("步枪射击...");
    }
}
