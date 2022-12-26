//
// Created by 罗斯 on 27/12/2022.
//
#include <string>
#include <vector>
#include <algorithm>

using namespace std;

class Solution {
public:
    int numDistinct(string s, string t) {
        int l_s = s.length(), l_t = t.length();
        vector<vector<int>> dp(l_t + 1, vector<int>(l_s + 1));
        fill(begin(dp[0]), end(dp[0]), 1);

        for (int i = 1; i <= l_t; ++i) {
            for (int j = 1; j <= l_s; ++j) {
                dp[i][j] = (dp[i][j - 1] + (t[i - 1] == s[j - 1] ? dp[i - 1][j - 1] : 0) )%1000000007;
            }
        }
        return dp[l_t][l_s];
    }
};