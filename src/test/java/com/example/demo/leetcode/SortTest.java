package com.example.demo.leetcode;

/**
 * @ClassName SortTest
 * @Description 选择排序
 * @Author: zhangsp
 * @date 2022/7/19 23:36
 * @Version 1.0
 */
public class SortTest {


    public static void main(String[] args) {
        int[] arr = new int[]{2,5,1,7,10,3,99,3,2,3,4,5,6,11,55,0,-1,9,8,4};
        insertionSort(arr);
    }

    /**
     * 选择排序
     * @param arr
     */
    public static void selectSort(int[] arr){
        if(arr == null || arr.length <2){
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            for(int j = i+1; j < arr.length; j++){
                if(arr[i] > arr[j]){
                    swap(arr, i, j);
                }
            }
        }
        printSort(arr);
    }

    /**
     * 冒泡排序
     * @param arr
     */
    public static void bubbleSort(int[] arr){
        if(arr == null || arr.length <2){
            return;
        }
        for (int i = arr.length; i >= 0; i--) {
            for (int j = 1; j < i; j++) {
                if(arr[j -1] > arr[j]){
                    swap(arr, j -1 , j);
                }
            }
        }
        printSort(arr);
    }

    /**
     * 插入排序
     * @param arr
     */
    public static void insertionSort(int[] arr){
        if(arr == null || arr.length <2){
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j >= 1; j--) {
                if(arr[j -1] > arr[j]){
                    swap2(arr, j -1 , j);
                }
            }
        }
        printSort(arr);

    }

    /**
     * 比较交换
     * @param arr
     * @param i
     * @param j
     */
    public static void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * 异或交换
     * @param arr
     * @param i
     * @param j
     */
    public static void swap2(int[] arr, int i, int j){
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }

    /**
     * 计算加减交换
     * @param arr
     * @param i
     * @param j
     */
    public static void swap3(int[] arr, int i, int j){
        arr[i] = arr[i] + arr[j];
        arr[j] = arr[i] - arr[j];
        arr[i] = arr[i] ^ arr[j];
    }


    public static void printSort(int[] arr){
        for(int k = 0; k< arr.length; k++){
            System.out.print(arr[k] + " ");
        }
    }

}
