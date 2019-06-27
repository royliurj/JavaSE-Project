package com.roy.javase.algorithm.sort;

/**
 * @Author: Roy
 * @Date: 2019/6/27 14:48
 */
public class BubblingSort {
    public static void main(String[] args) {
        int[] arr = new int[]{3,1,4,2,40,22,31};
        System.out.println("排序前");
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println("");

        sort(arr);

        System.out.println("排序后");
        for (int num : arr) {
            System.out.print(num + " ");
        }
    }

    public static void sort(int[] a){
        for (int i = 0; i< a.length - 1; i++){
            for (int j = 0;j < a.length - 1 - i; j++){
                if(a[j] > a[j + 1]){
                    int tmp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = tmp;
                }
            }
        }
    }
}
