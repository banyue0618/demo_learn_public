package com.example.demo.leetcode;

/**
 * @ClassName Test4
 * @Description TODO
 * @Author: zhangsp
 * @date 2021/12/28 16:34
 * @Version 1.0
 */
public class Test4 {

    public static void main(String[] args) {
        System.out.println(isPalindrome(12121));
    }

    public static boolean isPalindrome(int x) {
        StringBuilder sb = new StringBuilder(x + "");
        sb.reverse();
        if (x == 0) return true;
        if (x < 0 || x % 10 == 0) return false;
        int reversed = 0;
        int i = x;
        while (x != 0) {
            reversed = reversed * 10 + x % 10;
            System.out.println(reversed);
            x /= 10;
            System.out.println("x:"+x);
        }
        System.out.println("reversed：" + reversed);
        return i == reversed;
    }

}
