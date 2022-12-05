//
// Created by 罗斯 on 6/12/2022.
//
#include <vector>
#include <algorithm>

using namespace std;

class Solution {
public:
    int countSquares(vector<vector<int>>& matrix) {
        int m = matrix.size();
        int n = matrix[0].size();
        vector<vector<int>> dp(m, vector<int>(n));
        int res = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                dp[i][j] = matrix[i][j];
                if (i && j && dp[i][j])
                    dp[i][j] = min({dp[i - 1][j], dp[i][j - 1], dp[i - 1][j - 1]}) + 1;
                res += dp[i][j];
            }
        }
        return res;
    }
};