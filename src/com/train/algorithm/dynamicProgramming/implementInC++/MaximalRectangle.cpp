//
// Created by 罗斯 on 4/12/2022.
//
#include <vector>
#include <algorithm>

using namespace std;

class Solution {
public:
    int maximalRectangle(vector<vector<char>>& matrix) {
        int r = matrix.size();
        if (r == 0) return 0;
        int c = matrix[0].size();

        vector<vector<int>> dp(r, vector<int>(c));

        for (int i = 0; i < r; ++i) {
            for (int j = 0; j < c; ++j) {
                dp[i][j] = (matrix[i][j] == '1') ? (j == 0 ? 1 : dp[i][j - 1] + 1) : 0;
            }
        }
        int res = 0;
        for (int i = 0; i < r; ++i) {
            for (int j = 0; j < c; ++j) {
                int len = INT_MAX;
                for (int k = i; k < r; ++k) {
                    len = min(len, dp[k][j]);
                    if (len == 0) break;
                    res = max(res, len * (k - i + 1));
                }
            }
        }
        return res;
    }
};