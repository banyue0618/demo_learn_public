package com.example.demo.learn.design.lsp;

/**
 * @ClassName LSPTest
 * @Description 里氏替换原则主类 java
 * 类可以向上转型，但是不能向下转型，否则会抛出，ClassCaseException
 * @Author: zhangsp
 * @date 2022/3/21 21:28
 * @Version 1.0
 */
public class LSPTest {

    public static void main(String[] args) {

        Soldier soldier = new Soldier();

        soldier.setGun(new Rifle());

        soldier.killEnemy();
    }


}
