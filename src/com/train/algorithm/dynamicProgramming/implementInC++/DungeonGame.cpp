//
// Created by 罗斯 on 1/12/2022.
//
#include <vector>
#include <algorithm>

using namespace std;

class Solution {
public:
    int calculateMinimumHP(vector<vector<int>>& dungeon) {
        const int m = dungeon.size();
        const int n = dungeon[0].size();
//        vector<vector<int>> hp(m + 1,vector<int>(n + 1, INT_MAX));
//
//        hp[m][n - 1] = hp[m - 1][n] = 1;
//
//        for (int y = m - 1; y >= 0; --y) {
//            for (int x = n - 1; x >= 0 ; --x) {
//                hp[y][x] = max(1, min(hp[y + 1][x], hp[y][x + 1]) - dungeon[y][x]);
//            }
//        }
//        return hp[0][0];

        // hp[y][x]: min hp required to reach bottom right (P).
        vector<int> hp(n + 1, INT_MAX);
        hp[n - 1] = 1;

        for (int y = m - 1; y >= 0; --y)
            for (int x = n - 1; x >= 0; --x)
                hp[x] = max(1, min(hp[x], hp[x + 1]) - dungeon[y][x]);

        return hp[0];
    }
};