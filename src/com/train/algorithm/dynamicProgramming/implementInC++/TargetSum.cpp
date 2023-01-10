//
// Created by 罗斯 on 10/1/2023.
//
#include <vector>
#include <algorithm>
#include <numeric>

using namespace std;

class Solution {
public:
    int findTargetSumWays(vector<int>& nums, int target) {
        const int n = nums.size();
        const int sum = reduce(nums.begin(), nums.end());
        if (sum < abs(target)) return 0;
        const int kOffset = sum;
        const int kMaxN = sum * 2 + 1;
        vector<int> ways(kMaxN, 0);
        ways[kOffset] = 1;
        for (int num : nums) {
            vector<int> tmp(kMaxN, 0);
            for (int i = num; i < kMaxN - num; ++i)
                if (ways[i]) {
                    tmp[i + num] += ways[i];
                    tmp[i - num] += ways[i];
                }
            swap(ways, tmp);
        }
        return ways[target + kOffset];
    }
};