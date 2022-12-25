//
// Created by 罗斯 on 25/12/2022.
//
#include <string>
#include <algorithm>

using namespace std;


class Solution {
public:
    bool isMatch(string s, string p) {
        int l_p = p.length(), l_s = s.length(), i, j, k, cur, prev;
        if (!l_p) return l_s == 0;
        bool dp[2][l_s + 1];
        fill_n(&dp[0][0], 2 * (l_s + 1), false);
        dp[0][0] = true;

        for (int i = 1; i <= l_p; ++i) {
            cur = i % 2, prev = 1 - cur;
            dp[cur][0] = dp[prev][0] && p[i - 1] == '*';
            if (p[i - 1] == '*')
                for (int j = 1; j <= l_s; ++j) {
                    dp[cur][j] = dp[cur][j - 1] || dp[prev][j];
                }
            else
                for (int j = 1; j <= l_s; ++j) {
                    dp[cur][j] = dp[prev][j - 1] && (p[i - 1] == '?' || p[i - 1] == s[j - 1]);
                }
        }
        return dp[cur][l_s];
    }
};