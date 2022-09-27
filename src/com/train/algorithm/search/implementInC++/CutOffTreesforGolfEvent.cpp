//
// Created by 罗斯 on 27/9/2022.
//
#include <vector>
#include <queue>

using namespace std;

class Solution {
public:
    int cutOffTree(vector<vector<int>>& forest) {
        m_ = forest.size();
        n_ = forest[0].size();

        // {height, x, y}
        vector<tuple<int,int,int>> trees;
        for (int y = 0; y < m_; ++y)
            for (int x = 0; x < n_; ++x)
                if (forest[y][x] > 1)
                    trees.emplace_back(forest[y][x], x, y);

        // sort trees by height
        sort(trees.begin(), trees.end());

        int sx = 0;
        int sy = 0;

        int total_steps = 0;

        // Move from current position to next tree to cut
        for (int i = 0; i < trees.size(); ++i) {
            int tx = get<1>(trees[i]);
            int ty = get<2>(trees[i]);

            int steps = BFS(forest, sx, sy, tx, ty);
            if (steps == INT_MAX) return -1;

            // Cut the tree, not necessary
            forest[ty][tx] = 1;

            total_steps += steps;

            sx = tx;
            sy = ty;
        }

        return total_steps;
    }
private:
    int m_;
    int n_;

    int BFS(const vector<vector<int>>& forest,
            int sx, int sy,
            int tx, int ty){
        static int dirs[4][2] = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        auto visited = vector<vector<int>>(m_, vector<int>(n_, 0));

        queue<pair<int, int>> q;
        q.emplace(sx, sy);

        int steps = 0;
        while (!q.empty()){
            int new_nodes = q.size();
            while (new_nodes--){
                auto node = q.front();
                q.pop();
                const int cx = node.first;
                const int cy = node.second;

                if (cx == tx && cy == ty)
                    return steps;

                for (int i = 0; i < 4; ++i) {
                    const int x = cx + dirs[i][0];
                    const int y = cy + dirs[i][1];

                    if(x < 0 || y < 0 || x == n_ || y == m_ || !forest[y][x] || visited[y][x]) continue;

                    visited[y][x] = 1;
                    q.emplace(x, y);
                }
            }
            ++steps;
        }
        return INT_MAX;
    }
};