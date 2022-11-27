//
// Created by 罗斯 on 28/11/2022.
//
#include <vector>
#include <algorithm>
#include <unordered_map>

using namespace std;


class Solution {
public:
    int uniquePathsWithObstacles(vector<vector<int>>& obstacleGrid) {
        int n = obstacleGrid.size();
        if (n == 0)
            return 0;
        int m = obstacleGrid[0].size();
        f = vector<vector<int>>(n + 1, vector<int>(m + 1, INT_MIN));
        return paths(m, n, obstacleGrid);
    }
private:
    vector<vector<int>> f;
    int paths(int x, int y, const vector<vector<int>>& o){
        if (x <=0 || y <= 0) return 0;
        if (x == 1 && y == 1) return 1 - o[0][0];
        if (f[y][x] != INT_MIN) return f[y][x];
        if (o[y - 1][x - 1] == 1)
            f[y][x] = 0;
        else
            f[y][x] = paths(x - 1, y, o) + paths(x, y - 1, o);

        return f[y][x];
    }
};