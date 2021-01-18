package com.train.algorithm.dynamicProgramming;

// 斐波那契数列
public class FibonacciNumbers {
    // leetcode-509
    public int fib(int n) {
        if (n < 2) {
            return n;
        }
        int p = 0,q = 0,r = 1;
        for (int i = 2;i <= n ;i++ ) {
            p = q;
            q = r;
            r = p + q;
        }
        return r;
    }

    // leetcode-70
    public int climbStairs(int n) {
        int p = 0,q = 0,r = 1;
        for (int i = 1;i <= n ;i++ ) {
            p = q;
            q = r;
            r = p + q;
        }
        return r;
    }

    // 剑指offer10
    public int numWays(int n) {
        int p = 0,q = 0, r = 1;
        for (int i = 0;i <n ;i++ ) {
            p = q;
            q = r;
            r = (p + q)%1000000007;
        }
        return r;
    }

    // leetcode-198
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int len = nums.length;
        if (len == 1) {
            return nums[0];
        }
        int[] dp = new int[len];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0],nums[1]);
        for (int i = 2;i < len ;i++ ) {
            dp[i] = Math.max(dp[i - 2] + nums[i],dp[i - 1]);
        }
        return dp[len - 1];
    }
}
