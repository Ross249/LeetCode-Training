//
// Created by 罗斯 on 11/12/2022.
//
#include <vector>

using namespace std;

class Solution {
public:
    int numTilings(int n) {
        constexpr int mod = 1000000007;
        vector<vector<long>> dp(n + 1, vector<long>(2, 0));
        dp[0][0] = dp[1][0] = 1;
        for (int i = 2; i <= n; ++i) {
            dp[i][0] = (dp[i - 1][0] + dp[i - 2][0] + 2 * dp[i - 1][1]) % mod;
            dp[i][1] = (dp[i - 2][0] + dp[i - 1][1]) % mod;
        }
        return dp[n][0];
    }
};