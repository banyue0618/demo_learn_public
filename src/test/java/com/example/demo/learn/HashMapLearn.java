package com.example.demo.learn;

import java.util.HashMap;
import java.util.Map;

/**
 * @author banyue
 * date 2020-06-11
 */
public class HashMapLearn {

    public static void main(String[] args) {

        Map  map = new HashMap<String, String>();
        map.put("1","1");
        map.put(null, null);
        for (Object o : map.keySet()) {
            System.out.println(map.get(o));
        }


//        int[] arr = new int[]{1,3,9,20,2,30,13,24,14,25,5,6,16,86};
//        mergeSort(arr);

//        sort1();
        // 小和问题
        int[] arr = new int[]{1,3,4,2,5,6,3};
//        minAdd(arr);
//        minAdd2(arr);

        mergeSort3(arr, 3);


    }

    /**
     * 找出单一的数量
     */
    private static void sort1() {
        int[] arr = new int[]{1,1,1,2,2,3,3,4,4,5,5,6,6,6};
        int b = 0 ;
        for (int i : arr) {
            b ^= i;
        }
        int rightOne = b & (~b + 1);

        int a = 0;
        for (int i : arr) {
            if( (rightOne & i) == 1){
                a ^= i;
            }
        }


        System.out.println(a + " " + (a^b));
    }

    /**
     * 归并排序
     * @param arr
     */
    private static void mergeSort(int[] arr){
        process(arr, 0, arr.length - 1);
    }

    private static void process(int[] arr, int l, int r){
        if(l == r){
            return;
        }
        int mid = l + ((r - l) >> 1);
        process(arr, l, mid);
        process(arr, mid + 1, r);
        merge(arr, l, mid, r);
        for (int i : arr) {
            System.out.print(i + ",");
        }
        System.out.println();

    }

    private static void merge(int[] arr, int l, int m, int r){
        int[] help = new int[r -l +1];
        int i = 0;
        int p1 = l;
        int p2 = m+1;
        while (p1 <= m && p2 <= r){
            help[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= m){
            help[i++] = arr[p1++];
        }
        while (p2 <= r){
            help[i++] = arr[p2++];
        }
        for (i = 0; i < help.length; i++) {
            arr[l + i] = help[i];
        }

    }

    /**
     * 小和问题 暴力求解
     * @param arr
     */
    private static void minAdd(int[] arr){
        int r = 0;
        for (int i = arr.length -1; i >=0 ; i--) {
            for(int j = 0 ; j <= i ; j++){
                if(arr[i] > arr[j]){
                    r += arr[j];
                }
            }
        }
        System.out.println(r);
    }

    /**
     * 小和问题 归并排序求解
     */
    private static void minAdd2(int[] arr){

        int res = process2(arr, 0, arr.length -1);
        System.out.println(res);
    }
    private static int process2(int[] arr, int l, int r){
        if(l == r){
            return 0;
        }
        int min = l + ((r - l) >> 1);
        return process2(arr, l, min) +
        process2(arr, min+1, r) +
        mergeSort2(arr, l, min, r);
    }

    private static int mergeSort2(int[] arr, int l, int min, int r){
        int[] help = new int[r - l + 1];
        int res = 0;
        int i = 0;
        int p1 = l;
        int p2 = min +1;
        while(p1 <= min && p2 <= r){
            res += arr[p1] < arr[p2] ? (r- p2 + 1) * arr[p1] : 0;
            help[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= min){
            help[i++] = arr[p1++];
        }
        while (p2 <= r){
            help[i++] = arr[p2++];
        }
        for (i = 0; i < help.length; i++) {
            arr[l + i] = help[i];
        }
        return res;
    }

    private static void mergeSort3(int[] arr, int num){
        int k = 0;
        int i = 0;
        int j = arr.length - 1;
        while (i != j){
            if(arr[i] < num){
                arr[k++] = arr[i];
                i++;
            }
            if(arr[i] > num){

                arr[j--] = arr[i];
            }
            if(arr[i] == num){
                i++;
            }

        }

        for (int a : arr) {
            System.out.print(a + ",");
        }
    }

}
