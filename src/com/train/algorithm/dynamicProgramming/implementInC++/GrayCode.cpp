//
// Created by 罗斯 on 22/12/2022.
//
#include <vector>
#include <algorithm>

using namespace std;

class Solution {
public:
    vector<int> grayCode(int n) {
        vector<vector<int>> dp(n + 1);
        dp[0] = {0};
        for (int i = 1; i <= n; ++i) {
            dp[i] = dp[i - 1];
            for (int j = dp[i - 1].size() - 1; j >= 0 ; --j) {
                dp[i].push_back(dp[i - 1][j] | (1 << (i - 1)));
            }
        }
        return  dp[n];
    }
};