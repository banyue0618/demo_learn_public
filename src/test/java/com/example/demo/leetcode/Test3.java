package com.example.demo.leetcode;

/**
 * @ClassName 返回
 * @Description TODO
 * @Author: zhangsp
 * @date 2021/12/28 14:53
 * @Version 1.0
 */
public class Test3 {

    public static void main(String[] args) {
        System.out.println(searchInsert(new int[]{1,3,5,6}, 2));
    }

    public static int searchInsert(int[] nums, int target) {
        int index = 0;
        int start = 0;
        int end = nums.length - 1;
        if(nums[end] == target){
            return end;
        }
        if(nums[end] < target){
            return end + 1;
        }
        if(nums[start] >= target){
            return 0;
        }
        // target 值介于数组之间
        for(int i = 0; i < nums.length; i++){
            // 取数组中间那个
            index = (start + end)/2;
            if(nums[index] > target){
                end = index;
            }else if(nums[index] < target){
                start = index;
            }else{
                return index;
            }
            if(end - start == 1){
                return end;
            }

        }
        return 0;
    }

}
