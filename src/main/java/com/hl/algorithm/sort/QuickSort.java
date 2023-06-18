package com.hl.algorithm.sort;

import java.util.Arrays;

/**
 * 快速排序
 *
 * @author huanglin
 * @date 2021/6/19 下午4:34
 */
public class QuickSort {
    public static void main(String[] args) {
        int[] arr = {1, 4, 3, 9, 11, 5, 3};
        System.out.println("排序前 ==> " + Arrays.toString(arr));
        sort(arr, 0, arr.length - 1);
        System.out.println("排序后 ==> " + Arrays.toString(arr));
    }

    public static void sort(int[] arr, int left, int right) {
        if(left < right) {
            int pivot = getPivot(arr, left, right);
            sort(arr, left, pivot - 1);
            sort(arr, pivot + 1, right);
        }
    }

    public static int getPivot(int[] arr, int left, int right) {
        int index = left + 1;
        for(int i = index; i <= right; i++) {
            if(arr[i] < arr[left]) {
                SortUtils.swap(arr, i, index);
                index++;
            }
        }

        SortUtils.swap(arr, left, index -1);

        return index - 1;
    }
}
