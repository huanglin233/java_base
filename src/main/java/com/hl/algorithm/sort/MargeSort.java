package com.hl.algorithm.sort;

import java.util.Arrays;

/**
 * 归并排序
 *
 * @author huanglin
 * @date 2021/6/19 下午2:12
 */
public class MargeSort {
    public static void main(String[] args) {
        int[] arr = {1, 4, 13, 2, 11, 5, 1};
        System.out.println("排序前 ==> " + Arrays.toString(arr));
        int[] sort = sort(arr);
        System.out.println("排序后 ==> " + Arrays.toString(sort));
    }

    public static int[] sort(int[] arr) {
        int midden = arr.length / 2;
        if(midden < 1) {
            return arr;
        }

        int[] arr1 = new int[midden];
        System.arraycopy(arr, 0, arr1, 0, midden);
        int[] arr2 = new int[arr.length - midden];
        System.arraycopy(arr, midden, arr2, 0, arr.length - midden);
        int[] sort = sort(arr1);
        int[] sort1 = sort(arr2);

        return marge(sort, sort.length, sort1, sort1.length);
    }

    /**
     * 合并排序数组
     * @param arr1    前一个数组
     * @param arrLen1 前一个数组的长度
     * @param arr2    后一个数组的起始索引
     * @param arrLen2 后一个数组
     */
    public static int[] marge(int[] arr1, int arrLen1, int[] arr2, int arrLen2) {
        int[] tempArr = new int[arrLen1 + arrLen2];
        int i = 0;
        int j = 0;
        int index = 0;
        while(arrLen1 > 0 && arrLen2 > 0) {
            for(int k = j; k < arr2.length; k++) {
                if(arr1[i] > arr2[k]) {
                   tempArr[index] = arr2[k];
                   index++;
                   j++;
                   arrLen2--;
                } else {
                    tempArr[index] = arr1[i];
                    index++;
                    arrLen1--;
                    break;
                }
            }
            i++;
        }


        if(arrLen1 > 0) {
            System.arraycopy(arr1, arr1.length - arrLen1, tempArr, tempArr.length - arrLen1, arrLen1);
        }

        if(arrLen2 > 0) {
            System.arraycopy(arr2, arr2.length - arrLen2, tempArr, tempArr.length - arrLen2, arrLen2);
        }

        return tempArr;
    }
}