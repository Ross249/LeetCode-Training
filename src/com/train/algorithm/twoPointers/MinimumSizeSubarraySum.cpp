//
// Created by 罗斯 on 23/8/2022.
//
#include <vector>

using namespace std;
class Solution {
public:
    int minSubArrayLen(int target, vector<int>& nums) {
        int l = 0,t= 0,r = 0;
        int res = INT_MAX;
        while(l < nums.size()){
            while(t < target && r < nums.size()) t += nums[r++];
            if(t < target) break;
            res = min(res,r - l);
            t -= nums[l++];
        }
        return res == INT_MAX ? 0 : res;
    }
};