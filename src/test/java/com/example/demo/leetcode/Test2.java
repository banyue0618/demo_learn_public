package com.example.demo.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName Test2
 * @Description 罗马转数字
 * @Author: zhangsp
 * @date 2021/12/24 17:45
 * @Version 1.0
 */
public class Test2 {

    public static void main(String[] args) {
        System.out.println(romanToInt3("LVIII"));
    }

    public static int romanToInt2(String s) {
        char[] chars = s.toCharArray();
        int i = 0;
        for(int k = 0; k < chars.length - 1 ; k++){
            if(getValue(chars[k]) < getValue(chars[k+1])){
                i -= getValue(chars[k]);
            }else{
                i += getValue(chars[k]);
            }
        }
        return i + getValue(chars[chars.length - 1]);
    }


    public static int getValue(char c){
        switch (c){
            case 'I' : return 1;
            case 'V' : return 5;
            case 'X' : return 10;
            case 'L' : return 50;
            case 'C' : return 100;
            case 'D' : return 500;
            case 'M' : return 1000;
            default : return 0;
        }
    }


    public static int romanToInt(String s) {
        if(s == null || s.length() == 0){
            return 0;
        }
        System.out.println(s);
        // 先记录好拆分
        Map <String, Integer> dataMap = new HashMap <>();
        dataMap.put("I",1);
        dataMap.put("V",5);
        dataMap.put("X",10);
        dataMap.put("L",50);
        dataMap.put("C",100);
        dataMap.put("D",500);
        dataMap.put("M",1000);
        dataMap.put("IV",4);
        dataMap.put("IX",9);
        dataMap.put("XL",40);
        dataMap.put("XC",90);
        dataMap.put("CD",400);
        dataMap.put("CM",900);
        String[] sp = new String[]{"IV","IX","XL","XC","CD","CM"};
        for(int i = 0; i< sp.length ; i++){
            if(s.contains(sp[i]) && s.length() > 2){
                String[] spi = s.split(sp[i]);
                if(spi.length > 1){
                    return romanToInt(spi[0]) + romanToInt(spi[1]) + + romanToInt(sp[i]);
                }else{
                    return romanToInt(spi[0]) + romanToInt(sp[i]);
                }

            }
        }
        int i = 0;
        if(dataMap.get(s) != null ){
            i += dataMap.get(s);
            return i;
        }
        if(s.length() > 1){
            char[] chars = s.toCharArray();
            for(char c : chars){
                i += dataMap.get(String.valueOf(c));
            }
        }else{
            i += dataMap.get(s);
        }
        return i;
    }

    public static int romanToInt3(String s) {
        int n = s.length();
        int roman_int = 0;
        int num1 = 0; int num2 = 0;
        for(int i=0;i<n;i++){
            switch(s.charAt(i)) {
                case 'I' : num1 = 1;break;
                case 'V' : num1 = 5;break;
                case 'X' : num1 = 10;break;
                case 'L' : num1 = 50;break;
                case 'C' : num1 = 100;break;
                case 'D' : num1 = 500;break;
                case 'M' : num1 = 1000;break;
                default: System.out.println("default");break;
            }
            if(num1 < num2){
                roman_int -= num1 ;
            }else{
                roman_int += num1 ;
            }
            num2 = num1;
        }
        return roman_int;
    }
}
