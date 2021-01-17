package com.train.algorithm.dynamicProgramming;

import java.util.Arrays;

// 无限背包
public class UnboundedKnapsack {
    // leetcode-1547
    public int minCost(int n, int[] cuts) {
        int len = cuts.length;
        Arrays.sort(cuts);
        int[] newCuts = new int[len + 2];
        newCuts[0] = 0;
        for (int i = 1;i <= len ;i++ ) {
            newCuts[i] = cuts[i - 1];
        }
        newCuts[len + 1] = n;
        int[][] dp = new int[len + 2][len + 2];
        for (int i = len;i >= 1 ;i-- ) {
            for (int j = i;j <= len ;j++ ) {
                dp[i][j] = i == j ? 0 : Integer.MAX_VALUE;
                for (int k = i;k <= j ;++k ) {
                    dp[i][j] = Math.min(dp[i][j] , dp[i][k - 1] + dp[k + 1][j]);
                }
                dp[i][j] += newCuts[j + 1] - newCuts[i - 1];
            }
        }
        return dp[1][len];
    }

    // leetcode-322
    public int coinChange(int[] coins, int amount) {
        int max = amount + 1;
        int[] dp = new int[max];
        Arrays.fill(dp,max);
        dp[0] = 0;
        for (int i = 1;i <= amount;i++ ) {
            for (int j = 0;j < coins.length ;j++ ) {
                if (coins[j] <= i) {
                    dp[i] = Math.min(dp[i],dp[i - coins[j]] + 1);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }

    // leetcode-518
    public int change(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for (int coin : coins) {
            for (int i = coin;i <= amount ;i++ ) {
                dp[i] += dp[i - coin];
            }
        }
        return dp[amount];
    }
}
