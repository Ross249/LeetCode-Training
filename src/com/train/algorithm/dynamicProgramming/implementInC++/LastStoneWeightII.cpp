//
// Created by 罗斯 on 12/1/2023.
//
#include <vector>
#include <algorithm>
#include <numeric>
#include <unordered_set>

using namespace std;

class Solution {
public:
    int lastStoneWeightII(vector<int>& stones) {
        const int n = stones.size();
        const int max_sum = reduce(stones.begin(), stones.end());
        unordered_set<int> sums;
        sums.insert(stones[0]);
        sums.insert(-stones[0]);
        for (int i = 1; i < n; ++i) {
            unordered_set<int> tmp;
            for (int s : sums) {
                tmp.insert(s + stones[i]);
                tmp.insert(s - stones[i]);
            }
            swap(tmp,sums);
        }
        int res = INT_MAX;
        for (int s : sums) {
            res = min(res, abs(s));
        }

        return res;
    }
};