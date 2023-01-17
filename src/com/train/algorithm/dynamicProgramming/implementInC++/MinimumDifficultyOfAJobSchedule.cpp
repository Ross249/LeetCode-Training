//
// Created by 罗斯 on 17/1/2023.
//
#include <vector>
#include <algorithm>

using namespace std;

class Solution {
public:
    int minDifficulty(vector<int>& jobDifficulty, int d) {
        const int n = jobDifficulty.size();
        if (d > n) return -1;
        vector<vector<int>> dp(n + 1, vector<int>(d + 1, INT_MAX / 2));

        dp[0][0] = 0;
        for (int i = 1; i <= n; ++i) {
            for (int j = i - 1, md = 0; j >= 0 ; --j) {
                md = max(md, jobDifficulty[j]);
                for (int k = 1; k <= min(i, d); ++k) {
                    dp[i][k] = min(dp[i][k], dp[j][k - 1] + md);
                }
            }
        }

        return dp[n][d];
    }
};