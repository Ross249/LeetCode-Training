//
// Created by 罗斯 on 1/1/2023.
//
#include <string>
#include <vector>
#include <deque>
#include <algorithm>

using namespace std;

class Solution {
public:
    string shortestCommonSupersequence(string str1, string str2) {
        int l1 = str1.length();
        int l2 = str2.length();
        vector<vector<int>> dp (l1 + 1, vector<int>(l2 + 1));
        for (int i = 1; i <= l1; ++i) {
            for (int j = 1; j <= l2; ++j) {
                dp[i][j] = str1[i - 1] == str2[j - 1] ? 1 + dp[i - 1][j - 1] : max(dp[i - 1][j], dp[i][j - 1]);
            }
        }
        deque<char> res;
        while (l1 || l2){
            char c;

            if (l1 == 0) c = str2[--l2];
            else if (l2 == 0) c = str1[--l1];
            else if (str1[l1 - 1] == str2[l2 - 1]) c = str1[--l1] = str2[--l2];
            else if (dp[l1 - 1][l2] == dp[l1][l2]) c = str1[--l1];
            else if (dp[l1][l2 - 1] == dp[l1][l2]) c = str2[--l2];

            res.push_front(c);
        }
        return {begin(res), end(res)};
    }
};