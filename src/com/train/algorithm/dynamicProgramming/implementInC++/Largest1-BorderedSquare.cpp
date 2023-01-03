//
// Created by 罗斯 on 3/1/2023.
//
#include <vector>
#include <algorithm>

using namespace std;

class Solution {
public:
    int largest1BorderedSquare(vector<vector<int>>& grid) {
        int n = grid.size();
        int m = grid[0].size();
        vector<vector<int>> dp(n + 1, vector<int>(m + 1));
        for (int i = 1; i <= n; ++i) {
            for (int j = 1; j <= m; ++j) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1] - dp[i - 1][j - 1] + grid[i - 1][j - 1];
            }
        }

        for (int len = min(n, m); len > 0; --len){
            for (int x1 = 1, x2 = x1 + len - 1; x2 <= m ; ++x1, ++x2) {
                for (int y1 = 1, y2 = y1 + len - 1; y2 <= n ; ++y1, ++y2) {
                    if (getArea(x1, y1, x2, y1, dp) == len
                    && getArea(x1, y1, x1, y2, dp) == len
                    && getArea(x1, y2, x2, y2, dp) == len
                    && getArea(x2, y1, x2, y2, dp) == len)
                        return len * len;
                }
            }
        }
        return 0;
    }
private:
    int getArea(int x1, int y1, int x2, int y2, const vector<vector<int>>& dp){
        return dp[y2][x2] - dp[y2][x1 - 1] - dp[y1 - 1][x2] + dp[y1 - 1][x1 - 1];
    }
};