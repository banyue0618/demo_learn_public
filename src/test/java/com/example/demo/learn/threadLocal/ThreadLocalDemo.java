package com.example.demo.learn.threadLocal;

import java.lang.reflect.Field;

/**
 * @author banyue
 * date 2020-08-13
 */
public class ThreadLocalDemo {

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(()->test("abc",false));
        t.start();
        t.join();
        System.out.println("--gc后--");
        Thread t2 = new Thread(() -> test("def", true));
        t2.start();
        t2.join();
    }

    public static void test(String s,boolean isGC){
        try{
            ThreadLocal<String>  threadLocal = new ThreadLocal<>();
            threadLocal.set(s);
            if (isGC) {
                System.gc();
            }
            Thread thread = Thread.currentThread();
            Class<? extends Thread> clazz = thread.getClass();
            Field field = clazz.getDeclaredField("threadLocals");
            field.setAccessible(true);
            Object threadLocalMap = field.get(thread);
            Class<?> tlmClass = threadLocalMap.getClass();
            Field tableField = tlmClass.getDeclaredField("table");
            tableField.setAccessible(true);
            Object[] arr = (Object[]) tableField.get(threadLocalMap);
            for (Object o : arr) {
                if (o != null) {
                    Class<?> entryClass = o.getClass();
                    Field valueField = entryClass.getDeclaredField("value");
                    Field referenceField = entryClass.getSuperclass().getSuperclass().getDeclaredField("referent");
                    valueField.setAccessible(true);
                    referenceField.setAccessible(true);
                    System.out.println(String.format("弱引用key:%s,值:%s", referenceField.get(o), valueField.get(o)));
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }



    }

}
