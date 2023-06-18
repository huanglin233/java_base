package com.hl.algorithm.sort;

import java.util.Arrays;

/**
 * 基数排序
 * @author huanglin
 * @date 2021/6/20 下午2:58
 */
public class RadixSort {
    public static void main(String[] args) {
        int[] arr = {1, 4, 13, 2, 11, 5, 1};
        System.out.println("排序前 ==> " + Arrays.toString(arr));
        int[] sort = sort(arr);
        System.out.println("排序后 ==> " + Arrays.toString(sort));
    }

    public static int[] sort(int[] arr) {
        int[] minAndMax = SortUtils.getMinAndMax(arr);
        int max = minAndMax[1];
        int num = 0;
        while(max > 0) {
            max = max / 10;
            num++;
        }

        for(int i = 0; i < num; i++) {
            // 创建十个桶
            int[][] buckets = new int[10][0];
            for (int k : arr) {
                int val = (int) (k / (Math.pow(10, i)) % 10);
                int[] ints = SortUtils.arrAppend(buckets[val], k);
                buckets[val] = ints;
            }

            int index = 0;
            for(int[] bucket: buckets) {
                for(int value : bucket) {
                    arr[index++] = value;
                }
            }
        }

        return arr;
    }
}