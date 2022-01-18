package com.example.demo.learn.designPattern;

/**
 * 饿汉单例模式
 *
 * 当实例中存在其他静态方法  编译器会将实例进行初始化。
 *
 * 采用内部类形式 实例将不会进行初始化
 * @author banyue
 * date 2020-04-12
 */
public class Singleton {

    private Singleton(){
        System.out.println("init");
    }


    private static class SingletonHolder{
        private static Singleton instance=new Singleton();
    }

    public static Singleton getInstance(){
        return SingletonHolder.instance;
    }

    public static void play(){
        System.out.println("I am playing");
    }


}
