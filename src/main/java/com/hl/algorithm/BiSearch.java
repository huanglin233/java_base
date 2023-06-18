package com.hl.algorithm;

/**
 * @author huanglin by 2021/5/15
 */
public class  BiSearch {

    public static int biSearchVal(int[] array, int val) {
        int lo = 0;
        int hi = array.length;
        int mid;
        while(lo <= array.length -1) {
            mid = (lo + hi) / 2;
            if(array[mid] == val) {
                return mid + 1;
            } else if(array[mid] < val){
                // 向右查找
                lo = mid + 1;
            } else {
                // 向左查找
                hi = mid - 1;
            } 
        }

        return -1;
    }

    public static void main(String[] args) {
        int [] array = new int [] {2, 3, 5, 6, 7, 8, 9};
        System.out.println(biSearchVal(array, 8));
    }
}