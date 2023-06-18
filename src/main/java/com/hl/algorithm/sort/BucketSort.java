package com.hl.algorithm.sort;

import java.util.Arrays;

/**
 * 桶排序
 *
 * @author huanglin
 * @date 2021/6/20 下午1:32
 */
public class BucketSort {
    public static void main(String[] args) {
        int[] arr = {1, 4, 13, 2, 11, 5, 1};
        System.out.println("排序前 ==> " + Arrays.toString(arr));
        sort(arr);
        System.out.println("排序后 ==> " + Arrays.toString(arr));
    }

    public static void sort(int[] arr) {
        int[] minAndMax = SortUtils.getMinAndMax(arr);
        int bucketNum = (minAndMax[1] -minAndMax[0]) / arr.length + 1;

        int[][] buckets = new int[bucketNum][0];

        for(int val : arr) {
            int dIndex = (val - minAndMax[0]) / arr.length;
            int[] ints = SortUtils.arrAppend(buckets[dIndex], val);
            buckets[dIndex] = ints;
        }

        int arrIndex = 0;
        for(int[] bucket : buckets) {
            if (bucket.length <= 0) {
                continue;
            }

            bucket = InsertSort.sort(bucket);
            for(int value: bucket) {
                arr[arrIndex++] = value;
            }
        }
    }
}
