package com.example.demo.learn.jvm;

/**
 * @author banyue
 * date 2020-04-27
 *
 * 测试一个栈深度
 *
 */
public class JavaVMStackSOF {

    private int stackLength = -1;

    public static void main(String[] args){
        JavaVMStackSOF oom = new JavaVMStackSOF();
        try {
            oom.initConstant();
            System.gc();
//            oom.stackLeak();
        } catch (Throwable  e) {
            System.out.println("Stack length:" + oom.stackLength);
            e.printStackTrace();
        }
    }

    public void stackLeak(){
        stackLength++;
        stackLeak();
    }

    public void initConstant(){
        for (int i = 0; i < 1<<31 - 1; i++) {
            int l = i;
        }
    }


}
