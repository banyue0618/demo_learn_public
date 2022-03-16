package com.example.demo.learn.clone;

import java.text.DecimalFormat;

/**
 * @ClassName CloneTest
 * @Description 深浅克隆
 *  浅克隆不会克隆原对象中的引用类型，仅仅拷贝了引用类型的指向（克隆出来引用类型还是指向旧地址）。
 *  深克隆则拷贝了所有。也就是说深克隆能够做到原对象和新对象之间完全没有影响。
 *
 * 而深克隆的实现就是在引用类型所在的类实现Cloneable接口，并使用public访问修饰符重写clone方法。
 *
 * 深克隆的另一种实现方式就是序列化，使用流的形式copy数据。
 *
 * @Author: zhangsp
 * @date 2022/2/24 17:23
 * @Version 1.0
 */
public class CloneTest {

    public static void main(String[] args) throws Exception{

        int i = 5;

        int j = 6;
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        System.out.println(decimalFormat.format((float)i/(float)j));


        Address address = new Address("南昌" , "江西");

        User user = new User("张三", "小三", 10, address);

        System.out.println("User city:" + user.getAddress().getCity());

        User clone = user.clone();

        System.out.println("clone city:" + clone.getAddress().getCity());

        clone.getAddress().setCity("抚州");

        System.out.println("User2 city:" + user.getAddress().getCity());

    }
}
