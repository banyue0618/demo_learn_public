package com.example.demo.leetcode;

/**
 * @ClassName Test5
 * @Description 最长公共前缀
 * @Author: zhangsp
 * @date 2021/12/29 17:29
 * @Version 1.0
 */
public class Test5 {

    public static void main(String[] args) {
        longestCommonPrefix(new String[]{"flower","flow","flight"});
    }


    // 排序 拿到最短字符串 截取 循环匹配 startsWith

    public static String longestCommonPrefix(String[] strs) {

        if(strs.length == 0){
            return "";
        }
        String o = strs[0];
        for(String s : strs){
            while(!s.startsWith(o)){
                if(o.length() == 0){
                    return "";
                }
                o = o.substring(0, o.length() -1);
            }
        }
        return o;
    }
}
