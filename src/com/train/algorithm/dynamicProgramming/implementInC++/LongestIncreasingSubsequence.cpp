//
// Created by 罗斯 on 17/12/2022.
//
#include <vector>
#include <algorithm>

using namespace std;

class Solution {
public:
    int lengthOfLIS(vector<int>& nums) {
        if (nums.empty()) return 0;
        const int n = nums.size();
//        auto f = vector<int>(n, 1);
//        for (int i = 1; i < n; ++i) {
//            for (int j = 0; j < i; ++j) {
//                if (nums[i] > nums[j])
//                    f[i] = max(f[i], f[j] + 1);
//            }
//        }
//        return *max_element(f.begin(), f.end());

        // better tc
        vector<int> dp;
        for (int i = 0; i < n; ++i) {
            auto it = lower_bound(begin(dp), end(dp), nums[i]);
            if (it == end(dp))
                dp.push_back(nums[i]);
            else
                *it = nums[i];
        }
        return dp.size();
    }
};