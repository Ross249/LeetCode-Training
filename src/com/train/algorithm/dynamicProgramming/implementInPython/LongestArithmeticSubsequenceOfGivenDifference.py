from collections import defaultdict


class Solution(object):
    def longestSubsequence(self, arr, difference):
        dp = {}
        max_len = 1
        for num in arr:
            if num - difference in dp:
                dp[num] = dp[num - difference] + 1
            else:
                dp[num] = 1
            max_len = max(max_len, dp[num])
        return max_len