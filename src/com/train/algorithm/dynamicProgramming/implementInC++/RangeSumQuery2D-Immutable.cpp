//
// Created by 罗斯 on 5/12/2022.
//
#include <vector>
#include <algorithm>

using namespace std;

class NumMatrix {
public:
    NumMatrix(vector<vector<int>>& matrix) {
        sums.clear();
        if (matrix.empty()) return;
        int m = matrix.size();
        int n = matrix[0].size();

        sums = vector<vector<int>>(m + 1, vector<int>(n + 1, 0));

        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                sums[i][j] = matrix[i - 1][j - 1] + sums[i - 1][j] + sums[i][j - 1] - sums[i - 1][j - 1];
            }
        }
    }

    int sumRegion(int row1, int col1, int row2, int col2) {
        return sums[row2 + 1][col2 + 1]
        - sums[row2 + 1][col1]
        - sums[row1][col2 + 1]
        + sums[row1][col1];
    }
private:
    vector<vector<int>> sums;
};