package com.example.demo.stack;

import java.util.Queue;

public class MyStackTest {

    public static void main(String[] args) {

        MyStack obj = new MyStack();
        obj.push(1);
        obj.push(2);
        obj.push(3);
        obj.push(4);

//        System.out.println(obj.pop());
//        obj.list.forEach(System.out::println);

        System.out.println(obj.top());
        obj.list.forEach(System.out::println);

        StringBuilder sb = new StringBuilder();
        sb.append("1");
        sb.append(",");
        sb.append("1");
        sb.append(",");
        sb.append("1");
        sb.append(",");

        System.out.println(sb.substring(0, sb.length()-1));

    }
}
