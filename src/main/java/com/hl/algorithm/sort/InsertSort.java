package com.hl.algorithm.sort;

import java.util.Arrays;

/**
 * 插入排序
 *
 * @author huanglin
 * @date 2021/6/16 下午10:39
 */
public class InsertSort {
    public static void main(String[] args) {
        int[] arr = {1, 4, 13, 2, 11, 5, 1};
        System.out.println("排序前 ==> " + Arrays.toString(arr));
        int[] sortArr = sort(arr);
        System.out.println("排序后 ==> " + Arrays.toString(sortArr));
    }

    public static int[] sort(int[] array) {
        int[] newArray = new int[array.length];
        newArray[0] = array[0];
        int len = 1;
        for(; len < array.length; len++) {
            int insertIndex = len;
            for(int j = len - 1; j >= 0; j--) {
                if(newArray[j] > array[len]) {
                   newArray[insertIndex] = newArray[insertIndex - 1];
                   insertIndex--;
                }
            }

            newArray[insertIndex] = array[len];
        }

        return newArray;
    }
}