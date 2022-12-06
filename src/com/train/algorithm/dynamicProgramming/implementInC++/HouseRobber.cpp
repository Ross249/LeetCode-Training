//
// Created by 罗斯 on 7/12/2022.
//
#include <vector>
#include <algorithm>

using namespace std;

class Solution {
public:
    int rob(vector<int>& nums) {
//        const int n = nums.size();
//        m_ = vector<int>(n, -1);
//        return rob(nums, n - 1);

        if (nums.empty()) return 0;
        vector<int> dp(nums.size(), 0);
        for (int i = 0; i < nums.size(); ++i) {
            dp[i] = max((i > 1 ? dp[i - 2] : 0) + nums[i], i > 0 ?dp[i - 1] : 0);
        }
        return dp.back();
    }
private:
    int rob(const vector<int>& nums, int i) {
        if (i < 0) return 0;
        if (m_[i] >= 0) return m_[i];
        return m_[i] = max(rob(nums, i - 2) + nums[i],
                           rob(nums, i - 1));
    }

    vector<int> m_;
};