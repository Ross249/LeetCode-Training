//
// Created by 罗斯 on 19/1/2023.
//
#include <vector>
#include <algorithm>

using namespace std;

class Solution {
public:
    int splitArray(vector<int>& nums, int k) {
        const int n = nums.size();
        vector<int> sums(n);
        vector<vector<int>> dp(k + 1, vector<int>(n, INT_MAX));
        sums[0] = nums[0];
        for (int i = 1; i < n; ++i) {
            sums[i] = sums[i - 1] + nums[i];
        }
        for (int i = 0; i < n; ++i) {
            dp[1][i] = sums[i];
        }

        for (int i = 2; i <= k; ++i) {
            for (int j = i - 1; j < n; ++j) {
                for (int l = 0; l < j; ++l) {
                    dp[i][j] = min(dp[i][j], max(dp[i - 1][l], sums[j] - sums[l]));
                }
            }
        }

        return dp[k][n - 1];
    }
};