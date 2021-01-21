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

    // leetcode-801
    public int minSwap(int[] A, int[] B) {
        int n1 = 0,s1 = 1;
        for (int i = 1;i < A.length ;i++ ) {
            int n2 = Integer.MAX_VALUE,s2 = Integer.MAX_VALUE;
            if (A[i-1] < A[i] && B[i-1] < B[i]) {
                n2 = Math.min(n2,n1);
                s2 = Math.min(s2,s1 + 1);
            }
            if (A[i-1]<B[i] && B[i-1] < A[i]) {
                n2 = Math.min(n2,s1);
                s2 = Math.min(s2,n1+1);
            }
            n1 = n2;
            s1 = s2;
        }
        return Math.min(n1,s1);
    }

    // leetcode-1027
    public int longestArithSeqLength(int[] A) {
        int res = 0;
        int len = A.length;
        int[][] dp = new int[len][20002];
        int offset = 10000;
        for (int i = 0;i < dp.length ;i++ ) {
            for (int j = 0;j < dp[0].length ;j++ ) {
                dp[i][j] = 1;
            }
        }

        for (int i = 1;i < len ;i++ ) {
            for (int j = 0;j < i ;j++ ) {
                int sub = A[j]-A[i];
                dp[i][sub + offset] = Math.max(dp[i][sub + offset],dp[j][sub + offset] + 1);
                res = Math.max(res,dp[i][sub + offset]);
            }
        }
        return res;
    }

    // leetcode-72
    public int minDistance(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();

        if (n * m == 0) {
            return n + m;
        }
        int[][] dp = new int[n+1][m+1];
        for (int i = 0;i < n+1 ;i++ ) {
            dp[i][0] = i;
        }
        for (int j = 0;j < m+1 ;j++ ) {
            dp[0][j] = j;
        }

        for (int i = 1;i < n+1 ;i++ ) {
            for (int j = 1;j < m+1 ;j++ ) {
                int left = dp[i - 1][j]+1;
                int right = dp[i][j - 1]+1;
                int left_down = dp[i - 1][j - 1];
                if (word1.charAt(i - 1) != word2.charAt(j - 1)) {
                    left_down += 1;
                }
                dp[i][j] = Math.min(left,Math.min(right,left_down));
            }
        }
        return dp[n][m];
    }

    // leetcode-97
    public boolean isInterleave(String s1, String s2, String s3) {
        int n = s1.length(),m = s2.length(),t = s3.length();
        if (n + m != t) {
            return false;
        }
        boolean[][] dp = new boolean[n+1][m+1];
        dp[0][0] = true;
        for (int i = 0;i <= n ;i++ ) {
            for (int j = 0;j <= m ;j++ ) {
                int p = i + j - 1;
                if (i > 0) {
                    dp[i][j] = dp[i][j] || (dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(p));
                }
                if (j > 0) {
                    dp[i][j] = dp[i][j] || (dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(p));
                }
            }
        }
        return dp[n][m];
    }
}
