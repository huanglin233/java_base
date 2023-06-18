package com.hl.algorithm;

/**
 * @author huanglin
 * @date 2021/6/14 上午11:05
 */
public class Test {
    /**
     * 获取数组的中位值
     * @param  nums1 数组1
     * @param  nums2 数组2
     * @param  k     中位值索引
     * @return
     */
    static int getMedianNum(int[] nums1, int[] nums2, int k) {
        int len1   = nums1.length;
        int len2   = nums2.length;
        int index1 = 0;
        int index2 = 0;

        while(true) {
            if(len1 == index1) {
                return nums2[index2 + k - 1];
            }

            if(len2 == index2) {
                return nums1[index1 + k -1];
            }

            if(k == 1) {
                return Math.min(nums1[index1], nums2[index2]);
            }

            int medianVal = k / 2;
            int newIndex1 = Math.min(index1 + medianVal, len1) - 1;
            int newIndex2 = Math.min(index2 + medianVal, len2) - 1;
            int val1 = nums1[newIndex1];
            int val2 = nums2[newIndex2];

            if(val1 > val2) {
                k = k - (newIndex2 - index2 + 1);
                index2 = newIndex2 + 1;
            } else {
                k = k - (newIndex1 - index1 + 1);
                index1 = newIndex1 + 1;
            }
        }
    }

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int length1 = nums1.length;
        int length2 = nums2.length;
        int medianK = length1 + length2;
        if(medianK % 2 == 1) {
            return getMedianNum(nums1, nums2, (medianK / 2) + 1);
        } else {
            return (getMedianNum(nums1, nums2, (medianK / 2)) + getMedianNum(nums1, nums2, (medianK / 2) + 1)) / 2.0;
        }

    }

    public static void main(String[] args) {
        int[] nums1 = {1, 5, 6, 12, 14};
        int[] nums2 = {2, 3, 4, 7};

        System.out.println(findMedianSortedArrays(nums1, nums2));
    }
}