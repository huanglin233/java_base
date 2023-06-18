package com.hl.algorithm.sort;

import java.util.Arrays;

/**
 * @author huanglin
 * @date 2021/6/19 下午5:45
 */
public class SortUtils {
    public static void swap(int[] arr, int index1, int index2) {
            int tmp = arr[index2];
            arr[index2] = arr[index1];
            arr[index1] = tmp;
    }

    public static int[] getMinAndMax(int[] arr) {
        int min = arr[0];
        int max = arr[0];

        for(int val : arr) {
            if(val < min) {
                min = val;
            }

            if(val > max) {
                max = val;
            }
        }

        return new int[]{min, max};
    }

    public static int[] arrAppend(int[] arr, int value) {
        arr = Arrays.copyOf(arr, arr.length + 1);
        arr[arr.length - 1] = value;

        return arr;
    }
}
