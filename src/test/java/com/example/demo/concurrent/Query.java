package com.example.demo.concurrent;

/**
 * @ClassName Query
 * @Description TODO
 * @Author: zhangsp
 * @date 2022/2/24 10:23
 * @Version 1.0
 */
public interface Query<A, V> {

    V compute(A arg) throws Exception;

}
