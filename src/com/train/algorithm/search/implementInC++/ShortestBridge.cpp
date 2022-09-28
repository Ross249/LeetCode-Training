//
// Created by 罗斯 on 28/9/2022.
//
#include <vector>
#include <queue>

using namespace std;

class Solution {
public:
    int shortestBridge(vector<vector<int>>& grid) {
        queue<pair<int, int>> q;
        bool found = false;
        for (int i = 0; i < grid.size() && !found; ++i) {
            for (int j = 0; j < grid[0].size() && !found; ++j) {
                if (grid[i][j]){
                    dfs(grid, j, i, q);
                    found = true;
                }
            }
        }

        int steps = 0;
        vector<int> dirs{0, 1, 0, -1, 0};
        while (!q.empty()){
            size_t size = q.size();
            while (size--){
                int x = q.front().first;
                int y = q.front().second;
                q.pop();
                for (int i = 0; i < 4; ++i) {
                    int tx = x + dirs[i];
                    int ty = y + dirs[i + 1];
                    if (tx < 0 || ty < 0 || tx >= grid[0].size() || ty >= grid.size() || grid[ty][tx] == 2) continue;
                    if (grid[ty][tx] == 1)
                        return steps;
                    grid[ty][tx] = 2;
                    q.emplace(tx, ty);
                }
            }
            ++steps;
        }
        return -1;
    }

private:
    void dfs(vector<vector<int>>& A, int x, int y, queue<pair<int, int>>& q){
        if (x < 0 || y < 0 || x >= A[0].size() || y >= A.size() || A[y][x] != 1) return;
        A[y][x] = 2;
        q.emplace(x, y);
        dfs(A, x + 1, y, q);
        dfs(A, x, y + 1, q);
        dfs(A, x - 1, y, q);
        dfs(A, x, y - 1, q);
    }
};