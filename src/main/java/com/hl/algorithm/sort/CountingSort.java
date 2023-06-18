package com.hl.algorithm.sort;

import java.util.Arrays;

/**
 * 计数排序
 *
 * @author huanglin
 * @date 2021/6/20 上午11:54
 */
public class CountingSort {
    public static void main(String[] args) {
        int[] arr = {1, 2, 13, 6, 13, 4, 2};
        System.out.println("排序前 ==> " + Arrays.toString(arr));
        int[] sortArr = sort(arr);
        System.out.println("排序后 ==> " + Arrays.toString(sortArr));
    }

    public static int[] sort(int[] arr) {
        int[] minAndMax = SortUtils.getMinAndMax(arr);
        int[] index = new int[minAndMax[1] - minAndMax[0] + 1];
        for(int value : arr) {
            index[value - minAndMax[0]]++;
        }

        int[] ret = new int[arr.length];
        int indexCount = 0;
        for(int i = 0; i < index.length; i++) {
            while(index[i] > 0) {
                ret[indexCount] = i + minAndMax[0];
                index[i]--;
                indexCount++;
            }
        }

        return ret;
    }


}
