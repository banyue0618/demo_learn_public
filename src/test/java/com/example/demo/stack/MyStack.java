package com.example.demo.stack;

import java.util.LinkedList;
import java.util.Queue;

public class MyStack {

    Queue <Integer> list;

    public MyStack() {
        this.list = new LinkedList <>();
    }

    public void push(int x){
        list.add(x);
    }

    public int pop(){
        for (int i = 0; i < list.size() - 1; i++) {
            list.add(list.poll());
        }
        return list.poll();
    }

    public int top(){
        for (int i = 0; i < list.size() - 1; i++) {
            list.add(list.poll());
        }
        int res = list.poll();
        list.add(res);
        return res;
    }

    public boolean empty(){
        return list.peek() == null;
    }
}
