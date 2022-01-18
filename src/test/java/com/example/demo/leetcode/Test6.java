package com.example.demo.leetcode;

/**
 * @ClassName Test6
 * @Description TODO
 * @Author: zhangsp
 * @date 2021/12/29 18:29
 * @Version 1.0
 */
public class Test6 {


    public static void main(String[] args) {
        System.out.println(isPalindrome("race a car"));
    }


    public static boolean isPalindrome(String s) {
        char[] cnt = s.toLowerCase().toCharArray();
        StringBuilder sb = new StringBuilder();
        for (char c : cnt) {
            if ((c >= 'a' && c <= 'z') || (c >= '0' && c <= '9')) {
                sb.append(c);
            }
        }
        String s2 = sb.toString();
        String s1 = sb.reverse().toString();
        return s1.equals(s2);
    }
}
