package com.hl.algorithm.sort;

import java.util.Arrays;

/**
 * 希尔排序
 *
 * @author huanglin
 * @date 2021/6/18 下午11:13
 */
public class ShellSort {
    public static void main(String[] args) {
        int[] arr = {1, 4, 13, 4, 13, 4, 1};
        System.out.println("排序前 ==> " + Arrays.toString(arr));
        sort(arr);
        System.out.println("排序后 ==> " + Arrays.toString(arr));
    }

    public static void sort(int[] array) {
        int increment = array.length / 2;

        while(increment >= 1) {
            for(int i = 0; i < array.length - increment; i++) {
                int next = i + increment;
                while(next < array.length) {
                    if(array[i] > array[next]) {
                        SortUtils.swap(array,i, next);
                    }
                    next += increment;
                }
            }

            increment = increment / 2;
        }
    }
}
