//
// Created by 罗斯 on 14/1/2023.
//
#include <vector>
#include <algorithm>

using namespace std;

class Solution {
public:
    int numWays(int steps, int arrLen) {
        const int mod = 1e9 + 7;
        arrLen = min(steps + 1, arrLen);

        vector<int> dp(arrLen);
        dp[0] = 1;
        for (int i = 0; i < steps; ++i) {
            vector<int> tmp(dp.size());
            for (int j = 0; j < arrLen; ++j) {
                tmp[j] = dp[j];
                if (j > 0) tmp[j] = (tmp[j] + dp[j - 1]) % mod;
                if (j < arrLen - 1) tmp[j] = (tmp[j] + dp[j + 1]) % mod;
            }
            swap(tmp, dp);
        }
        return dp[0];
    }
};