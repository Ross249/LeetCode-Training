//
// Created by 罗斯 on 30/11/2022.
//
#include <vector>
#include <algorithm>

using namespace std;

class Solution {
public:
    int minimumTotal(vector<vector<int>>& triangle) {
        int n = triangle.size();
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j <= i; ++j) {
                if (i == 0 && j == 0) continue;
                if (j == 0) triangle[i][j] += triangle[i - 1][j];
                else if (i == j) triangle[i][j] += triangle[i - 1][j - 1];
                else triangle[i][j] += min(triangle[i - 1][j], triangle[i - 1][j - 1]);
            }
        }
        return *min_element(begin(triangle.back()), end(triangle.back()));
    }
};