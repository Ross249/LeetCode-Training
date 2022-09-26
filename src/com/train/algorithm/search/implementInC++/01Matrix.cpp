//
// Created by 罗斯 on 26/9/2022.
//
#include <vector>
#include <queue>
using namespace std;

class Solution {
public:
    vector<vector<int>> updateMatrix(vector<vector<int>>& mat) {
        const int m = mat.size();
        const int n = mat[0].size();
        queue<pair<int, int>> q;
        vector<vector<int>> seen(m, vector<int>(n, 0));
        vector<vector<int>> res(m, vector<int>(n, INT_MAX));

        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (mat[i][j] == 0){
                    q.push({i, j});
                    seen[i][j] = 1;
                }
            }
        }

        vector<int> dirs{0, -1, 0, 1, 0};
        int steps = 0;
        while (!q.empty()){
            int size = q.size();
            while (size--){
                auto pair = q.front();
                q.pop();
                int i = pair.first;
                int j = pair.second;
                res[i][j] = steps;
                for (int k = 0; k < 4; ++k) {
                    int x = j + dirs[k];
                    int y = i + dirs[k + 1];
                    if (x < 0 || x >= n || y < 0 || y >= m || seen[y][x]) continue;
                    seen[y][x] = 1;
                    q.push({y, x});
                }
            }
            ++steps;
        }

        return res;

//        int m = matrix.size();
//        int n = matrix[0].size();
//        vector<vector<int>> ans(m, vector<int>(n, INT_MAX - m * n));
//        for (int i = 0; i < m; ++i)
//            for (int j = 0; j < n; ++j)
//                if (matrix[i][j]) {
//                    if (i > 0) ans[i][j] = min(ans[i][j], ans[i - 1][j] + 1);
//                    if (j > 0) ans[i][j] = min(ans[i][j], ans[i][j - 1] + 1);
//                } else {
//                    ans[i][j] = 0;
//                }
//        for (int i = m - 1; i >= 0; --i)
//            for (int j = n - 1; j >= 0; --j) {
//                if (i < m - 1) ans[i][j] = min(ans[i][j], ans[i + 1][j] + 1);
//                if (j < n - 1) ans[i][j] = min(ans[i][j], ans[i][j + 1] + 1);
//            }
//        return ans;
    }
};