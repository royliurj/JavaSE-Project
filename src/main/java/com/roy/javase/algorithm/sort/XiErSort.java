package com.roy.javase.algorithm.sort;

/**
 * @Author: Roy
 * @Date: 2019/6/27 13:57
 */
public class XiErSort {
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
        int length = a.length;
        int h = 1;
        while (h < length / 3)
            h = 3 * h + 1;
        for (; h >= 1; h /= 3) {
            for (int i = 0; i < a.length - h; i += h) {
                for (int j = i + h; j > 0; j -= h) {
                    if (a[j] < a[j - h]) {
                        int temp = a[j];
                        a[j] = a[j - h];
                        a[j - h] = temp;
                    }
                }
            }
        }
    }
}
