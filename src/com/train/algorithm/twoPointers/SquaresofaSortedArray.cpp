//
// Created by 罗斯 on 1/9/2022.
//
#include <vector>

using namespace std;
class Solution {
public:
    vector<int> sortedSquares(vector<int>& nums) {
        vector<int> res(nums.size());
        int l = 0,r = nums.size() - 1;
        for(int i = nums.size() - 1; i >= 0;--i){
            if(abs(nums[r]) > abs(nums[l])) res[i] = nums[r] * nums[r--];
            else res[i] = nums[l] * nums[l++];
        }
        return res;
    }
};