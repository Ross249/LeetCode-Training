//
// Created by 罗斯 on 8/1/2023.
//
#include <vector>
#include <algorithm>

using namespace std;

class Solution {
public:
    int combinationSum4(vector<int>& nums, int target) {
        int n = nums.size();
        vector<int> dp(target + 1, -1);
        return check(nums, target, dp, n);
    }
private:
    int check(vector<int>& nums, int target, vector<int>& dp, int n){
        if (target == 0) return 1;
        if (target < 0 || n == 0) return 0;
        if (dp[target] != -1) return dp[target];
        if (nums[n - 1] <= target){
            return dp[target] = check(nums, target - nums[n - 1], dp, nums.size()) + check(nums, target, dp, n - 1);
        }else{
            return dp[target] = check(nums, target, dp, n - 1);
        }
    }
};