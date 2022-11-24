//
// Created by 罗斯 on 25/11/2022.
//
#include <vector>
#include <algorithm>

using namespace std;

class Solution {
public:
    int maxSubArray(vector<int>& nums) {
//        vector<int> dp(nums.size());
//        dp[0] = nums[0];
//        for (int i = 1; i < nums.size(); ++i) {
//            dp[i] = max(dp[i - 1] + nums[i], nums[i]);
//        }
//        return *max_element(dp.begin(), dp.end());

        int preSum = nums[0], sum;
        int ret = nums[0];

        //每个dp[i]的结果只会被dp[i + 1]使用，所以可以进行空间优化
        for (int i = 1; i < nums.size(); i++){
            sum = nums[i] + max(0, preSum);
            preSum = sum;
            ret = max(sum, ret);
        }

        return ret;
    }
};