package com.example.demo.learn.map;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.common.support.StrKit;

import java.util.HashMap;
import java.util.Map;

/**
 * @author banyue
 * date 2020-06-22
 */
public class MapSizeTest {

    public static void main(String[] args) {
        HashMap<String, Boolean> map = new HashMap<>(10000);

        long start = 0;
        long end = 0;

        System.gc();
        start = Runtime.getRuntime().freeMemory();

        for(int i = 0 ; i< 10000 ; i++){
            map.put(StrKit.getRandomUUID(), false);
        }
        // 快要计算的时,再清理一次
        System.gc();
        end = Runtime.getRuntime().freeMemory();
        System.out.println("一个HashMap对象占内存:" + (end - start));
//
//        String key = "treatment:grant:person:death:idCard:" + StrKit.getRandomUUID();
    }

}
