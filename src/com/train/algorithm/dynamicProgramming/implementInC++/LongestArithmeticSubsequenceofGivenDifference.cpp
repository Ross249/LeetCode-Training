//
// Created by 罗斯 on 24/11/2022.
//
#include <vector>
#include <algorithm>
#include <unordered_map>

using namespace std;

class Solution {
public:
    int longestSubsequence(vector<int>& arr, int difference) {
        unordered_map<int, int> dp;
        int res = 0;
        for (int a: arr) {
            res = max(res, dp[a] = dp[a - difference] + 1);
        }
        return res;
    }
};