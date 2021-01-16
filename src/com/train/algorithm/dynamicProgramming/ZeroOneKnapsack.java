package com.train.algorithm.dynamicProgramming;

// 0/1背包
public class ZeroOneKnapsack {
    // leetcode-416
    public boolean canPartition(int[] nums) {
        int len  = nums.length;
        if (len == 1) {
            return false;
        }
        int sum = 0;
        for (int num:nums ) {
            sum += num;
        }
        if (sum % 2 == 1) {
            return false;
        }

        int capacity = sum / 2;
        int[] w = nums;

        boolean[] dp = new boolean[capacity + 1];
        for (int c = 0;c <= capacity ; c++) {
            dp[c] = w[0] == c ;
        }
        for (int k = 1;k < len ; k++) {
            for (int c = capacity;c >= w[k] ;c-- ) {
                dp[c] = dp[c] || dp[c-w[k]];
            }
        }
        return dp[capacity];
    }

    // leetcode-523
    public boolean checkSubarraySum(int[] nums, int k) {
        int[] dp = new int[10010];
        if (nums.length < 2) {
            return false;
        }
        if (k == 0) {
            for (int i = 0;i < nums.length ;i++ ) {
                for (int j = 0;j < nums.length - i ;j++ ) {
                    dp[j] = dp[j] + nums[j + i];
                    if (i != 0 && dp[j] == 0) {
                        return true;
                    }
                }
            }
            return false;
        }

        for (int i = 0;i < nums.length ;i++ ) {
            for (int j = 0;j < nums.length - i ;j++ ) {
                dp[j] = (dp[j] + nums[j+i]) % k;
                if (i != 0 && dp[j] == 0) {
                    return true;
                }
            }
        }
        return false;
    }
}
