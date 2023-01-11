//
// Created by 罗斯 on 11/1/2023.
//
#include <vector>
#include <algorithm>

using namespace std;

class Solution {
public:
    int maxSumAfterPartitioning(vector<int>& arr, int k) {
        const int n = arr.size();
        vector<int> dp(n + 1, 0);
        for (int i = 1; i <= n; ++i) {
            int m = INT_MIN;
            for (int j = 1; j <= k && i - j >= 0 ; ++j) {
                m = max(m, arr[i - j]);
                dp[i] = max(dp[i], dp[i - j] + m * j);
            }
        }
        return dp[n];
    }
};