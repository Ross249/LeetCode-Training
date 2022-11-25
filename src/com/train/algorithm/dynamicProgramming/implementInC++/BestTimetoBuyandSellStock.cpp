//
// Created by 罗斯 on 26/11/2022.
//
#include <vector>
#include <algorithm>

using namespace std;

class Solution {
public:
    int maxProfit(vector<int>& prices) {
        int n = prices.size();
        if (n < 2) return 0;
        vector<int> gains(n - 1, 0);
        for (int i = 1; i < n; ++i)
            gains[i - 1] = prices[i] - prices[i - 1];
        return max(0, maxSubArray(gains));

//        const int n = prices.size();
//        if (n < 1) return 0;
//        vector<int> min_prices(n);
//        vector<int> max_profit(n);
//        min_prices[0] = prices[0];
//        max_profit[0] = 0;
//        for (int i = 1; i < n; ++i) {
//            min_prices[i] = min(min_prices[i - 1], prices[i]);
//
//            max_profit[i] = max(max_profit[i - 1], prices[i] - min_prices[i - 1]);
//        }
//        return max_profit[n - 1];
    }
private:
    // From LC 53. Maximum Subarray
    int maxSubArray(vector<int>& nums) {
        vector<int> f(nums.size());
        f[0] = nums[0];

        for (int i = 1; i < nums.size(); ++i)
            f[i] = max(f[i - 1] + nums[i], nums[i]);

        return *std::max_element(f.begin(), f.end());
    }
};