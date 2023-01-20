//
// Created by 罗斯 on 19/1/2023.
//
#include <vector>
#include <algorithm>

using namespace std;

class Solution {
public:
    int dieSimulator(int n, vector<int>& rollMax) {
        const int mod = 1e9 + 7;
        vector<vector<int>> dp(n + 1, vector<int>(7));
        vector<int> sum(n + 1);

        for (int i = 0; i < 6; ++i) {
            sum[1] += dp[1][i] = 1;
        }

        for (int i = 2; i <= n; ++i) {
            for (int j = 0; j < 6; ++j) {
                const int k = i - rollMax[j];
                const int invalid = k <= 1 ? max(k, 0) : sum[k - 1] - dp[k - 1][j];
                dp[i][j] = ((sum[i - 1] - invalid) % mod + mod) % mod;
                sum[i] = (sum[i] + dp[i][j]) % mod;
            }
        }
        return sum[n];
    }
};