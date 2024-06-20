package com.hl.algorithm.datastruct.hashTable;

import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * 哈希表相关
 * @author huanglin
 * @date 2024/06/19 19:59
 */
public class DoMain {

    @Test
    public void towSumTest() {
        int[] nums = {2, 7, 11, 12, 13};
        System.out.println(towSum(nums, 9)[0] + " " + towSum(nums, 9)[1]);
        System.out.println(containsDuplicate(nums));
        System.out.println(findLHS(nums));
        System.out.println(longestConsecutive(nums));
    }

    /**
     * 两数之和
     * @param nums   数组
     * @param target 和
     * @return 满足的两个值的索引
     */
    public static int[] towSum(int[] nums, int target) {
        HashMap<Integer, Integer> indexForNum = new HashMap<>();
        for(int i = 0; i < nums.length - 1; i++) {
            if(indexForNum.containsKey(target - nums[i])) {
                return new int[]{indexForNum.get(target - nums[i]), i};
            } else {
                indexForNum.put(nums[i], i);
            }
        }

        return null;
    }

    /**
     * 判断是否存在重复数组
     * @param nums 判断数组
     * @return 判断结果
     */
    public boolean containsDuplicate(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for(int num : nums) {
            set.add(num);
        }

        return set.size() < nums.length;
    }

    /**
     * 查找最长和谐序列
     * @param nums 数组
     * @return 长度
     */
    public static int findLHS(int[] nums) {
        HashMap<Integer, Integer> countForNum = new HashMap<>();
        for(int num : nums) {
            countForNum.put(num, countForNum.getOrDefault(num, 0) + 1);
        }
        int longestCount = 0;
        for(int num : countForNum.keySet()) {
            if(countForNum.containsKey(num + 1)) {
                longestCount = Math.max(longestCount, countForNum.get(num) + countForNum.get(num + 1));
            }
        }

        return longestCount;
    }

    /**
     * 最长连续序列
     * @param nums 数组
     * @return 最长连续序列的长度
     */
    public static int longestConsecutive(int[] nums) {
        Map<Integer, Integer> countForNum = new HashMap<>();
        for(int num : nums) {
            countForNum.put(num, 1);
        }

        for(int num : nums) {
            forward(countForNum, num);
        }

        return maxCount(countForNum);
    }

    /**
     * 计算一个有序的数长度
     * @param countForNum map
     * @param num         有序开头的第一个数
     * @return 长度
     */
    private static int forward(Map<Integer, Integer> countForNum, int num) {
        if(!countForNum.containsKey(num)) {
            return 0;
        }

        int cnt = countForNum.get(num);
        if(cnt < 1) {
            return cnt;
        }
        cnt = forward(countForNum, num + 1) + 1;
        countForNum.put(num, cnt);

        return cnt;
    }

    /**
     * 查找map中最大的数
     * @param countForNum map
     * @return 最大的数
     */
    private static int maxCount(Map<Integer, Integer> countForNum) {
        int max = 0;
        for(int num : countForNum.keySet()) {
            max = Math.max(max, countForNum.get(num));
        }

        return max;
    }
}
