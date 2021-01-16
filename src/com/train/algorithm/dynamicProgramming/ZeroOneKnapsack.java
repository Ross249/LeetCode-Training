package com.train.algorithm.dynamicProgramming;

// 0/1背包
public class ZeroOneKnapsack {
    // leetcode-416
    public boolean canPartition(int[] nums) {
        // 合法验证
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
        // 初始化
        boolean[] dp = new boolean[capacity + 1];
        for (int c = 0;c <= capacity ; c++) {
            dp[c] = w[0] == c ;
        }
        for (int k = 1;k < len ; k++) {
            // c>=w[k]保证确定能装下
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
        // k==0特殊情况
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
//        当i=k时，dp[j]表示以j为起始下标，nums中连续k+1个整数的和
//        如当i=0时，相当于将nums拷贝到dp
//        i=1时，dp[0]相当于以0为起始下标，nums中2个整数的和，即nums[0]+nums[1]
//        每次计算时都可以用原来的dp进行更新，而不用一个个去加
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
