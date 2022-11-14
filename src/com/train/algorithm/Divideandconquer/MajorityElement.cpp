//
// Created by 罗斯 on 15/11/2022.
//
#include <vector>
#include <unordered_map>

using namespace std;
class Solution {
public:
    int majorityElement(vector<int>& nums) {
//        unordered_map<int, int> count;
//        const int n = nums.size();
//        for (const int num : nums)
//            if (++count[num] > n / 2) return num;
//        return -1;


//        nth_element(nums.begin(), nums.begin() + nums.size() / 2, nums.end());
//        return nums[nums.size() / 2];

        int majority = nums.front();
        int count = 0;

        for (const int num : nums) {
            if (num == majority) ++count;
            else if (--count == 0) {
                count = 1;
                majority = num;
            }
        }

        return majority;
    }
};