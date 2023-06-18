package com.hl.algorithm.sort;

import java.util.Arrays;

/**
 * 堆排序
 *
 * @author huanglin
 * @date 2021/6/19 下午8:58
 */
public class HeapHost {
    public static void main(String[] args) {
        int[] arr = {1, 4, 3, 9, 11, 5, 3};
        System.out.println("排序前 ==> " + Arrays.toString(arr));
        sort(arr);
        System.out.println("排序后 ==> " + Arrays.toString(arr));
    }

    public static void sort(int[] arr) {
        int len = arr.length;
        buildMaxHeap(arr, len);

        for(int i = len - 1; i > 0; i--) {
            SortUtils.swap(arr, i, 0);
            len--;
            heapIfy(arr, 0, len);
        }
    }

    public static void buildMaxHeap(int[] arr, int len) {
        for(int i = len / 2; i >= 0; i--) {
            heapIfy(arr, i, len);
        }
    }

    public static void heapIfy(int[] arr, int i, int len) {
        // 左孩子
        int left = i * 2 + 1;
        // 右孩子
        int right = i * 2 + 2;
        // 本树的节点索引
        int lastIndex = i;

        if(left < len && arr[left] > arr[lastIndex]) {
            lastIndex = left;
        }

        if(right < len && arr[right] > arr[lastIndex]) {
            lastIndex = right;
        }

        if(lastIndex != i) {
            SortUtils.swap(arr, lastIndex, i);
            heapIfy(arr, lastIndex, len);
        }
    }
}