//
// Created by 罗斯 on 31/8/2022.
//
#include <vector>

using namespace std;

class Solution {
public:
    int threeSumClosest(vector<int>& nums, int target) {
        int n = nums.size();
        int d = INT_MAX;
        int res = target;
        sort(begin(nums), end(nums));

        for(int i = 0;i < n - 2;++i){
            int s = i + 1,t = n - 1;
            while (s < t){
                int sum = nums[i] + nums[s] + nums[t];
                if(sum == target){
                    return target;
                }
                int diff = abs(target - sum);
                if(diff < d){
                    d = diff;
                    res = sum;
                }
                if(sum > target) --t;
                else ++s;
            }
        }
        return res;
    }
};