package com.train.algorithm.dynamicProgramming;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

// 回文子系列
public class PalindromicSubsequence {
    // leetcode-516
    public int longestPalindromeSubseq(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int n = s.length();
        int[][] dp = new int[n][n];
        for (int i = 0;i < n ;i++ ) {
            dp[i][i] = 1;
        }
        for (int i = n - 1;i >= 0 ;i-- ) {
            for (int j = i + 1;j < n ;j++ ) {
                if (s.charAt(i) == s.charAt(j)) {
                    // 增加长度
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                }else {
                    dp[i][j] = Math.max(dp[i+1][j],dp[i][j-1]);
                }
            }
        }
        return dp[0][n - 1];
    }

    // leetcode-5
    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return null;
        }
        int n = s.length();
        int maxLen = 1,begin = 0;

        boolean[][] dp = new boolean[n][n];
        char[] cArray = s.toCharArray();

        for (int i = 0;i < n ;i++ ) {
            dp[i][i] = true;
        }
        for (int j = 1;j < n ;j++ ) {
            for (int i = 0;i < j ;i++ ) {
                if (cArray[i] != cArray[j]) {
                    dp[i][j] = false;
                }else{
                    if (j - i < 3) {
                        dp[i][j] = true;
                    }else{
                        dp[i][j] = dp[i+1][j-1];
                    }
                }
                // 当dp[i][j] == true时，s[i.....j]为子串，记录串长度与起始位置
                if (dp[i][j] && j - i + 1 > maxLen) {
                    maxLen = j - i + 1;
                    begin = i;
                }
            }
        }
        return s.substring(begin,begin + maxLen);
    }

    // leetcode-1312
    public int minInsertions(String s) {
        int n = s.length();
        char[] c = s.toCharArray();
        int[][] dp = new int[n][n];
        for (int j = 1;j < n ;j++ ) {
            dp[j - 1][j] = c[j - 1] == c[j] ? 0 : 1;
            for (int i  = j - 2;i >= 0 ;i-- ) {
                if (c[i] == c[j]) {
                    dp[i][j] = dp[i + 1][j - 1];
                }else{
                    dp[i][j] = Math.min(dp[i + 1][j],dp[i][j - 1]) + 1;
                }
            }
        }
        return dp[0][n - 1];
    }

    // leetcode-131
    public List<List<String>> partition(String s) {
        int len = s.length();
        List<List<String>> res = new ArrayList();
        if (len == 0) {
            return res;
        }

        boolean[][] dp = new boolean[len][len];
        for (int right = 0;right < len ;right++ ) {
            for (int left = 0;left <= right ;left++ ) {
                if (s.charAt(left) == s.charAt(right) && (right - left <= 2 || dp[left + 1][right - 1])) {
                    dp[left][right] = true;
                }
            }
        }

        Deque<String> stack = new ArrayDeque<>();
        track(s,0,len,dp,stack,res);
        return res;
    }
    public void track(String s,int start,int len,boolean[][] dp,Deque<String> path,List<List<String>> res){
        if (start == len) {
            res.add(new ArrayList(path));
            return;
        }

        for (int i = start;i < len;i++ ) {
            if (!dp[start][i]) {
                continue;
            }
            path.addLast(s.substring(start,i + 1));
            track(s,i+1,len,dp,path,res);
            path.removeLast();
        }
    }
}
