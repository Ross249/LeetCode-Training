//
// Created by 罗斯 on 28/12/2022.
//
#include <string>
#include <vector>
#include <algorithm>

using namespace std;

class Solution {
public:
    int minDistance(string word1, string word2) {
        int m = word1.length(), n = word2.length();
        vector<vector<int>> dp(m + 1, vector<int>(n + 1, 0));
        for (int i = m; i >= 0 ; --i) {
            for (int j = n; j >= 0; --j) {
                if (i < m || j < n)
                    dp[i][j] = i < m && j < n && word1[i] == word2[j] ?
                            dp[i + 1][j + 1] : 1 + min((i < m ? dp[i + 1][j] : INT_MAX), (j < n ? dp[i][j + 1] : INT_MAX));
            }
        }
        return dp[0][0];
    }
};