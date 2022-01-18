package com.example.demo.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName Test1
 * @Description 两数之和
 * @Author: zhangsp
 * @date 2021/12/24 11:15
 * @Version 1.0
 */
public class Test1 {



    public int[] twoSum(int[] nums, int target) {
        Map <Integer, Integer> map = new HashMap <Integer, Integer>();
        for(int i = 0; i < nums.length ; i++){
            if(map.containsKey(target - nums[i])){
                return new int[]{map.get(target - nums[i]), i};
            }
            map.put(nums[i], i);
        }
        return new int[0];
    }

}
