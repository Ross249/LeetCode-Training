//
// Created by 罗斯 on 27/11/2022.
//
#include <vector>
#include <algorithm>
#include <unordered_map>

using namespace std;

class Solution {
public:
    int uniquePaths(int m, int n) {
//        if (m < 0 || n < 0) return 0;
//        if (m == 1 && n == 1) return 1;
//        if (f[m][n] > 0) return f[m][n];
//        int left = uniquePaths(m - 1, n);
//        int top = uniquePaths(m, n - 1);
//        f[m][n] = left + top;
//        return f[m][n];

        auto f = vector<vector<int>>(n + 1, vector<int>(m + 1, 0));
        f[1][1] = 1;

        for (int y = 1; y <= n; ++y)
            for(int x = 1; x <= m; ++x) {
                if (x == 1 && y == 1) {
                    continue;
                } else {
                    f[y][x] = f[y-1][x] + f[y][x-1];
                }
            }

        return f[n][m];
    }
private:
    unordered_map<int, unordered_map<int, int>> f;
};