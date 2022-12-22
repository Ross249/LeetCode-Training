//
// Created by 罗斯 on 23/12/2022.
//
#include <vector>
#include <string>
#include <algorithm>

using namespace std;

class Solution {
public:
    int minDistance(string word1, string word2) {
        int l1 = word1.length();
        int l2 = word2.length();

        vector<vector<int>> dp(l1 + 1,vector<int>(l2 + 1, 0));
        for (int i = 0; i <= l1; ++i) {
            dp[i][0] = i;
        }
        for (int i = 0; i <= l2; ++i) {
            dp[0][i] = i;
        }

        for (int i = 1; i <= l1; ++i) {
            for (int j = 1; j <= l2; ++j) {
                int c = (word1[i - 1] == word2[j - 1]) ? 0 : 1;
                dp[i][j] = min(dp[i - 1][j - 1] + c, min(dp[i][j - 1],dp[i - 1][j]) + 1);
            }
        }
        return dp[l1][l2];

        // recursion solution from Huahua(https://zxi.mytechroad.com/blog/dynamic-programming/leetcode-72-edit-distance/)
//        d_ = vector<vector<int>>(l1 + 1, vector<int>(l2 + 1, -1));
//        return minDistance(word1, word2, l1, l2);
    }
private:
    // recursion solution from Huahua(https://zxi.mytechroad.com/blog/dynamic-programming/leetcode-72-edit-distance/)
    vector<vector<int>> d_;
    // minDistance from word1[0:l1-1] to word2[0:l2-1]
    int minDistance(const string& word1, const string& word2, int l1, int l2) {
        if (l1 == 0) return l2;
        if (l2 == 0) return l1;
        if (d_[l1][l2] >= 0) return d_[l1][l2];

        int ans;
        if (word1[l1 - 1] == word2[l2 - 1])
            ans = minDistance(word1, word2, l1 - 1, l2 - 1);
        else
            ans = min(minDistance(word1, word2, l1 - 1, l2 - 1),
                      min(minDistance(word1, word2, l1 - 1, l2),
                          minDistance(word1, word2, l1, l2 - 1))) + 1;

        return d_[l1][l2] = ans;
    }
};