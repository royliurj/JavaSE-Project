package com.roy.javase.algorithm.sort;

/**
 * @Author: Roy
 * @Date: 2019/6/27 14:20
 */
public class SimpleSelectSort {
    public static void main(String[] args) {
        int[] arr = new int[]{13,10,4,22,31,12,30};

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

    public static void sort(int[] a) {
        for (int i = 0; i < a.length; i++) {
            int min = i;
            //选出之后待排序中值最小的位置
            for (int j = i + 1; j < a.length; j++) {
                if (a[j] < a[min]) {
                    min = j;
                }
            }
            //最小值不等于当前值时进行交换
            if (min != i) {
                int temp = a[i];
                a[i] = a[min];
                a[min] = temp;
            }
        }
    }
}
