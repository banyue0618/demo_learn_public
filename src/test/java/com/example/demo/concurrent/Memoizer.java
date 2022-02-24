package com.example.demo.concurrent;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName Memorizer
 * @Description TODO
 * @Author: zhangsp
 * @date 2022/2/24 10:27
 * @Version 1.0
 */
public class Memoizer<A, V> implements Query<A, V> {

    private final Query<A, V> c;

    private final Map <A, V> cache = new HashMap <A, V>();

    public Memoizer(Query<A, V> c) {
        this.c = c;
    }

    @Override
    public synchronized V compute(A arg) throws Exception {
        V result = cache.get(arg);
        if(null == result){
            result = c.compute(arg);
            cache.put(arg, result);
        }
        return result;
    }
}
