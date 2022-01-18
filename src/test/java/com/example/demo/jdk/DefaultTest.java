package com.example.demo.jdk;

/**
 * @author banyue
 * date 2020-07-07
 */
public interface DefaultTest {

    boolean checkData(int a);

    default boolean isNull(int b){
        System.out.println(b);
        return false;
    }

}
