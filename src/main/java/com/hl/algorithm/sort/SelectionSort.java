package com.hl.algorithm.sort;

import java.util.Arrays;

/**
 * 选择排序
 *
 * @author huanglin
 * @date 2021/6/16 下午10:23
 */
public class SelectionSort {
    public static void main(String[] args) {
        int[] arr = {1, 4, 13, 2, 11, 5, 1};
        System.out.println("排序前 ==> " + Arrays.toString(arr));
        sort(arr);
        System.out.println("排序后 ==> " + Arrays.toString(arr));
    }

    public static void sort(int[] array) {
        int minIndex;

        for(int i = 0; i < array.length - 1; i++) {
            minIndex = i;
            for(int j = i + 1; j < array.length; j++) {
                minIndex = array[minIndex] > array[j] ? j : minIndex;
            }

            if(minIndex != i) {
                SortUtils.swap(array, i, minIndex);
            }
        }
    }
}