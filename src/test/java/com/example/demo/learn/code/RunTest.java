package com.example.demo.learn.code;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author banyue
 * date 2020-04-28
 *
 * 涉及到cpu cache命中率的问题,横向遍历的命中率大于纵向遍历
 *
 *
 *
 */
public class RunTest {

    static long[][] arr;

    public static void main(String[] args) {

        try(FileInputStream fileInputStream = new FileInputStream(new File(""))){
            fileInputStream.read();
        }catch (IOException e){
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        arr = new long[1024 * 1024][8];

        int sum = 0;
        // 横向遍历
        long marked = System.currentTimeMillis();
        for (int i = 0; i < 1024 * 1024; i += 1) {
            for (int j = 0; j < 8; j++) {
                sum += arr[i][j];
            }
        }
        System.out.println("Loop times:" + (System.currentTimeMillis() - marked) + "ms");

        marked = System.currentTimeMillis();
        // 纵向遍历
        for (int i = 0; i < 8; i += 1) {
            for (int j = 0; j < 1024 * 1024; j++) {
                sum += arr[j][i];
            }
        }
        System.out.println("Loop times:" + (System.currentTimeMillis() - marked) + "ms");
    }

}
