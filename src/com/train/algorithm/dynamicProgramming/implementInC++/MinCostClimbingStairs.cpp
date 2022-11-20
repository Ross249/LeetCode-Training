//
// Created by 罗斯 on 21/11/2022.
//
#include <vector>
#include <algorithm>

using namespace std;

class Solution {
public:
    int minCostClimbingStairs(vector<int>& cost) {
//        vector<int> m(cost.size() + 1);
//        return dp(cost, m, cost.size());


        const int n = cost.size();
        vector<int> dp(n, INT_MAX);
        dp[0] = cost[0];
        dp[1] = cost[1];
        for (int i = 2; i < n; ++i) {
            dp[i] = min(dp[i - 1], dp[i - 2]) + cost[i];
        }
        return min(dp[n - 1], dp[n - 2]);
    }
private:
    int dp(vector<int>& cost, vector<int>& m, int i) {
        if (i <= 1) return 0;
        if (m[i] > 0) return m[i];
        return m[i] = min(dp(cost, m, i - 1) + cost[i - 1],
                          dp(cost, m, i - 2) + cost[i - 2]);
    }
};