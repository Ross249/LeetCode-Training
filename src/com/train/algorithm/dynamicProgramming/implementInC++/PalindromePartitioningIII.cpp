//
// Created by 罗斯 on 17/1/2023.
//

#include <vector>
#include <algorithm>
#include <string>

using namespace std;

class Solution {
public:
    int palindromePartition(string s, int k) {
        const int n = s.length();
        vector<vector<int>> cost(n, vector<int>(n));
        for (int l = 2; l <= n; ++l) {
            for (int i = 0, j = l - 1; j < n ; ++i, ++j) {
                cost[i][j] = (s[i] != s[j]) + cost[i + 1][j - 1];
            }
        }

        vector<vector<int>> dp(n, vector<int>(k + 1, INT_MAX / 2));
        for (int i = 0; i < n; ++i) {
            dp[i][1] = cost[0][i];
            for (int K = 2; K <= k; ++K) {
                for (int j = 0; j < i; ++j) {
                    dp[i][K] = min(dp[i][K], dp[j][K - 1] + cost[j + 1][i]);
                }
            }
        }
        return dp[n - 1][k];
    }
};