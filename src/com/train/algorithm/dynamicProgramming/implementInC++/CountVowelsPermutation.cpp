//
// Created by 罗斯 on 13/1/2023.
//

#include <vector>
#include <algorithm>
#include <numeric>

using namespace std;

class Solution {
public:
    int countVowelPermutation(int n) {
        constexpr int kMod = 1e9 + 7;
        vector<vector<long>> dp(n + 1, vector<long>(5));
        fill(begin(dp[1]), end(dp[1]), 1);

        for (int i = 2; i <= n; ++i) {
            // a -> e
            dp[i][1] += dp[i - 1][0];

            // e -> a
            dp[i][0] += dp[i - 1][1];

            // e -> i
            dp[i][2] += dp[i - 1][1];

            for (int j = 0; j < 5; ++j) {
                if (j == 2) continue;
                // i -> *
                dp[i][j] += dp[i - 1][2];
            }

            // o -> i
            dp[i][2] += dp[i - 1][3];
            // o -> u
            dp[i][4] += dp[i - 1][3];

            // u -> a
            dp[i][0] += dp[i - 1][4];

            for (int j = 0; j < 5; ++j) {
                dp[i][j] %= kMod;
            }
        }

        return reduce(begin(dp[n]), end(dp[n]), 0L) % kMod;
    }
};