//
// Created by 罗斯 on 23/1/2023.
//
#include <vector>
#include <algorithm>

using namespace std;

class Solution {
public:
    int minScoreTriangulation(vector<int>& values) {
        const int n = values.size();
        vector<vector<int>> dp(n, vector<int>(n));
        for (int l = 3; l <= n ; ++l) {
            for (int i = 0; i + l <= n ; ++i) {
                int j = i + l - 1;
                dp[i][j] = INT_MAX;
                for (int k = i + 1; k <= j - 1; ++k) {
                    dp[i][j] = min(dp[i][j], dp[i][k] + dp[k][j] + values[i] * values[j] * values[k]);
                }
            }
        }
        return dp[0][n - 1];
    }
};