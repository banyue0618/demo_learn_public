package com.example.demo.guava;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import java.util.Collection;
import java.util.Map;

public class GuavaTest {

    public static void main(String[] args) {
        Multimap <String, Integer> map = ArrayListMultimap.create();
        map.put("key", 1);
        map.put("key", 2);
        Collection <Integer> values = map.get("key");
        System.out.println(map); // 输出 {"key":[1,2]}
// 还能返回你以前使用的臃肿的Map
        Map <String, Collection<Integer>> collectionMap = map.asMap();
    }

}
