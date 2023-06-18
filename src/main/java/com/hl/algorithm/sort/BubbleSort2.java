package com.hl.algorithm.sort;

import java.util.Arrays;

/**
 *  鸡尾酒冒泡排序
 *
 * @author huanglin by 2021/5/18
 */
public class BubbleSort2 {

    private static void sort(int[] array) {
        // 交换变量
        int tmp;
        // 是否发生数据交换
        boolean isNotExchange;
        // 排序遍历的次数
        int loop = array.length / 2;

        for(int i = 0; i < loop; i++) {
            isNotExchange = true;

            for(int j = i; j < array.length - i - 1; j ++) {
                if(array[j] > array[j + 1]) {
                    tmp           = array[j];
                    array[j]      = array[j + 1];
                    array[j + 1]  = tmp;
                    isNotExchange = false;
                }
            }

            for(int j = array.length - i - 2; j > i; j--) {
                if(array[j - 1] > array[j]) {
                    tmp           = array[j];
                    array[j]      = array[j - 1];
                    array[j - 1]  = tmp;
                    isNotExchange = false;
                }
            }

            if(isNotExchange) {
                break;
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {1, 4, 13, 2, 11, 5, 1};
        sort(arr);

        System.out.println(Arrays.toString(arr));
    }
}
