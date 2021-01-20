package com.train.algorithm.dynamicProgramming;

import java.util.Arrays;

// 最长子字符串系列
public class LongestCommonSubstring {
    // leetcode-1143
    public int longestCommonSubsequence(String text1, String text2) {
        if (text1 == null || text2 == null || text1.length() <= 0 || text2.length() <= 0) {
            return 0;
        }

        int len1 = text1.length(),len2 = text2.length();
        int[][] dp = new int[len1+1][len2+1];

        char[] c1 = text1.toCharArray();
        char[] c2 = text2.toCharArray();
        for (int i = 1;i <= len1 ;i++ ) {
            for (int j = 1;j <= len2 ;j++ ) {
                if (c1[i-1] == c2[j-1]) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                }else{
                    dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1]);
                }
            }
        }
        return dp[len1][len2];
    }

    // leetcode-400
    public int lengthOfLIS(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int[] dp = new int[nums.length];
        int res = 0;
        Arrays.fill(dp,1);
        for (int i = 0;i < nums.length ;i++ ) {
            for (int j = 0;j < i ;j++ ) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i],dp[j]+1);
                }
            }
            res = Math.max(res,dp[i]);
        }
        return res;
    }

    // 剑指offer-42
    public int maxSubArray(int[] nums) {
        int res = nums[0];
        for (int i = 1;i < nums.length ;i++ ) {
            nums[i] += Math.max(nums[i-1],0);
            res = Math.max(res,nums[i]);
        }
        return res;
    }

    // leetcode-1092
    public String shortestCommonSupersequence(String str1, String str2) {
        StringBuilder[] record = new StringBuilder[str2.length()+1];
        record[0] = new StringBuilder();
        for (int i = 1;i <= str2.length() ;i++ ) {
            record[i] = new StringBuilder(str2.substring(0,i));
        }
        for (int i = 1;i <= str1.length();i++ ) {
            StringBuilder[] temp = new StringBuilder[str2.length()+1];
            temp[0] = new StringBuilder(str1.substring(0,i));
            for (int j = 1;j <= str2.length() ;j++ ) {
                char c1 = str1.charAt(i - 1);
                char c2 = str2.charAt(j - 1);
                if (c1 == c2) {
                    temp[j] = new StringBuilder(record[j-1]).append(c1);
                }else{
                    StringBuilder b1 = new StringBuilder(record[j]).append(c1);
                    StringBuilder b2 = new StringBuilder(temp[j-1].toString()).append(c2);
                    temp[j] = b1.length() <= b2.length() ? b1 : b2;
                }
            }
            record = temp;
        }
        return record[str2.length()].toString();
    }
}
