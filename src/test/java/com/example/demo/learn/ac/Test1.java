package com.example.demo.learn.ac;

import com.example.demo.learn.InterfaceTest;

import java.util.function.Predicate;

public class Test1 {

    public static void show(InterfaceTest interfaceTest){
        interfaceTest.methodTest();
    }


    public static void main(String[] args) {
        show(()->{
            System.out.println("重写方法...");
            System.out.println("重写方法...");
        });

    }

}
class DemoPredicateOr{
    public static void method(Predicate <String> one, Predicate<String> two) {
        boolean isValid = one.and(two).test("HelloWorld");
        System.out.println("字符串很长吗 :" + isValid);
    }
    public static void main(String[] args){
        method(s -> s.contains("H"),s -> s.contains("s"));
    }
}


