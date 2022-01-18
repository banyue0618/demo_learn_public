package com.example.demo.learn;

/**
 * @author banyue
 * date 2020-07-12
 */
public class LeeCode {

    public static void main(String[] args) {
        System.out.println(reverse(123));
    }


    public static int reverse(int x) {
        int j = 0 ;
        if( x < 0 ){
            j = 1;
        }
        if(x == 0 ){
            return 0;
        }
        String k = String.valueOf(x);
        StringBuilder sb = new StringBuilder();
        for(int i = k.length() - 1 ; i <= j ; i --){
            sb.append(k);
        }
        return j == 1? Integer.valueOf("-" + sb.toString()) : Integer.valueOf(sb.toString());

    }

}
