package com.example.demo.learn.common;

/**
 * @author banyue
 * date 2020-07-24
 */
public class Operator {

    public static void main(String[] args) {
        long time = System.currentTimeMillis();
        for (int i = 1; i < 1000000000; i++) {
            float a = 9000%i;
        }
        System.out.println(System.currentTimeMillis() - time);


        long time2 = System.currentTimeMillis();

        for (int i = 1; i < 1000000000; i++) {
            float a = 8000&i;
        }


        System.out.println(System.currentTimeMillis() - time2);
    }

}
