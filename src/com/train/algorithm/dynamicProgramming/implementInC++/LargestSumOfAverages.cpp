//
// Created by 罗斯 on 16/1/2023.
//
#include <vector>
#include <algorithm>

using namespace std;

class Solution {
public:
    double largestSumOfAverages(vector<int>& nums, int k) {
        const int n = nums.size();
        vector<double> dp(n + 1, 0.0);
        vector<double> sum(n + 1, 0.0);
        for (int i = 1; i <= n; ++i) {
            sum[i] = sum[i - 1] + nums[i - 1];
            dp[i] = static_cast<double>(sum[i]) / i;
        }

        for (int K = 2; K <= k; ++K) {
            vector<double> tmp(n + 1, 0.0);
            for (int i = K; i <= n; ++i) {
                for (int j = K - 1; j < i; ++j) {
                    tmp[i] = max(tmp[i], dp[j] + (sum[i] - sum[j]) / (i - j));
                }
            }
            dp.swap(tmp);
        }
        return dp[n];
    }
};