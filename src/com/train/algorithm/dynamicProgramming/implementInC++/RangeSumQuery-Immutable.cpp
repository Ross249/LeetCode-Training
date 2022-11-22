//
// Created by 罗斯 on 23/11/2022.
//
#include <vector>
#include <algorithm>

using namespace std;

class NumArray {
public:
    NumArray(vector<int>& nums) : sums_(nums){
        if (nums.empty()) return;
        for (int i = 1; i < nums.size(); ++i) {
            sums_[i] += sums_[i - 1];
        }
    }

    int sumRange(int left, int right) {
        if (left == 0) return sums_[right];
        return sums_[right] - sums_[left - 1];
    }
private:
    vector<int> sums_;
};