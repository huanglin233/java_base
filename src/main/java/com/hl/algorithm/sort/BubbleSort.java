package com.hl.algorithm.sort;

import java.util.Arrays;

/**
 *  冒泡排序
 * @author huanglin by 2021/5/18
 */
public class BubbleSort {

    private static void sort(int[] array) {
        // 交换数据的临时变量
        int     tmp;
        // 最后一次交换的位置
        int     lastExchangeIndex = 0;
        // 比较的次数，初始化为
        int     exchangeCount     = array.length - 1;
        // 是否有新的数据进行了交换
        boolean isNotExchange;

        for(int i = 0; i < array.length; i++) {
            isNotExchange = true;
            for(int j = 0; j < exchangeCount; j++) {
                if(array[j] > array[j + 1]) {
                    tmp               = array[j];
                    array[j]          = array[j + 1];
                    array[j + 1]      = tmp;
                    // 数据还在进行交换
                    isNotExchange     = false;
                    // 交换数据的位置
                    lastExchangeIndex = j;
                }
            }

            // 赋值本次循环最后一次交换数据位置
            exchangeCount = lastExchangeIndex;
            if(isNotExchange) {
                // 如果本次没有交换数据，说明排序已经完成，无需在进行后续多余的遍历
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
