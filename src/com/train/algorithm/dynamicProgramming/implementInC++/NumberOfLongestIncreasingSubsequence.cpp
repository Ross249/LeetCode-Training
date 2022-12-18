//
// Created by 罗斯 on 18/12/2022.
//
#include <vector>
#include <algorithm>

using namespace std;

class Solution {
public:
    int findNumberOfLIS(vector<int>& nums) {

        int n = nums.size();
        if (n == 0) return 0;

        c_ = vector<int>(n, 0);
        l_ = vector<int>(n, 0);

        // LIS.
        int max_len = 0;
        for (int i = 0; i < n; ++i)
            max_len = max(max_len, len(nums, i));

        int ans = 0;
        for (int i = 0; i < n; ++i)
            if (len(nums, i) == max_len)
                ans += count(nums, i);

        return ans;

        // another solution (better TC)
//        int n = nums.size();
//        if (n == 0) return 0;
//
//        vector<int> c(n, 1);
//        vector<int> l(n, 1);
//
//        for (int i = 1; i < n; ++i)
//            for(int j = 0; j < i; ++j)
//                if (nums[i] > nums[j]) {
//                    if (l[j] + 1 > l[i]) {
//                        l[i] = l[j] + 1;
//                        c[i] = c[j];
//                    } else if (l[j] + 1 == l[i]) {
//                        c[i] += c[j];
//                    }
//                }
//
//        int max_len = *max_element(l.begin(), l.end());
//
//        int ans = 0;
//        for (int i = 0; i < n; ++i)
//            if (l[i] == max_len)
//                ans += c[i];
//
//        return ans;
    }
private:
    vector<int> c_;// NLIS
    vector<int> l_;// LIS

    int count(const vector<int>& nums, int n){
        if (n == 0) return 1;
        if (c_[n] > 0) return c_[n];

        int total_count = 0;
        int l = len(nums, n);
        for (int i = 0; i < n; ++i)
            if (nums[n] > nums[i] && len(nums, i) == l-1)
                total_count += count(nums, i);

        if (total_count == 0)
            total_count = 1;

        return c_[n] = total_count;
    }

    int len(const vector<int>& nums, int n){
        if (n == 0) return 1;
        if (l_[n] > 0) return l_[n];

        int max_len = 1;

        for (int i = 0; i < n; ++i)
            if (nums[n] > nums[i])
                max_len = max(max_len, len(nums, i) + 1);

        return l_[n] = max_len;
    }
};