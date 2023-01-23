//
// Created by 罗斯 on 22/1/2023.
//
#include <vector>
#include <algorithm>

using namespace std;

class Solution {
public:
    int videoStitching(vector<vector<int>>& clips, int time) {
        constexpr int kInf = 101;
        vector<vector<int>> dp(time + 1, vector<int>(time + 1, kInf));
        for (const auto& c : clips) {
            int s = c[0];
            int e = c[1];
            for (int l = 1; l <= time ; ++l) {
                for (int i = 0; i <= time - l ; ++i) {
                    int j = i + l;
                    if (s > j || e < i) continue;
                    if (s <= i && e >= j) dp[i][j] = 1;
                    else if (e >= j) dp[i][j] = min(dp[i][j], dp[i][s] + 1);
                    else if (s <= i) dp[i][j] = min(dp[i][j], dp[e][j] + 1);
                    else dp[i][j] = min(dp[i][j], dp[i][s] + 1 + dp[e][j]);
                }
            }
        }
        return dp[0][time] == kInf ? -1 : dp[0][time];
    }
};