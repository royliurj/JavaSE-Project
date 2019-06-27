package com.roy.javase.algorithm.sort;

/**
 * @Author: Roy
 * @Date: 2019/6/27 11:11
 */
public class QuickSort {
    public static void main(String[] args) {

        int[] arr = new int[]{13,10,4,22,31,12,30};

        System.out.println("排序前");
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println("");

        sort(arr, 0, arr.length-1);

        System.out.println("排序后");
        for (int num : arr) {
            System.out.print(num + " ");
        }
    }

    public static void sort(int[] a, int low, int high) {
        //已经排完
        if (low >= high) {
            return;
        }
        int left = low;
        int right = high;

        //保存基准值
        int pivot = a[left];
        while (left < right) {
            //从后向前找到比基准小的元素
            while (left < right && a[right] >= pivot)
                right--;
            a[left] = a[right];
            //从前往后找到比基准大的元素
            while (left < right && a[left] <= pivot)
                left++;
            a[right] = a[left];
        }
        // 放置基准值，准备分治递归快排
        a[left] = pivot;
        sort(a, low, left - 1);
        sort(a, left + 1, high);
    }
}
