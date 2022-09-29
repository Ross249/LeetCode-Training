//
// Created by 罗斯 on 29/9/2022.
//
#include <vector>
#include <numeric>

using namespace std;

class Solution {
public:
    bool canPartitionKSubsets(vector<int>& nums, int K) {
        int N = nums.size();
        if (K == 1) return true;
        if (N < K) return false;
        int sum = 0;
        for (int i = 0; i < N; i++) sum += nums[i];
        if (sum % K != 0) return false;

        int subset = sum / K;
        int subsetSum[K];
        bool taken[N];

        for (int i = 0; i < K; i++) subsetSum[i] = 0;
        for (int i = 0; i < N; i++) taken[i] = false;

        subsetSum[0] = nums[N - 1];
        taken[N - 1] = true;

        return canPartitionKSubsets(nums, subsetSum, taken, subset, K, N, 0, N - 1);
    }


private:
    bool canPartitionKSubsets(vector<int>& nums, int subsetSum[], bool taken[], int subset, int K, int N, int curIdx, int limitIdx) {
        if (subsetSum[curIdx] == subset) {
            if (curIdx == K - 2) return true;
            return canPartitionKSubsets(nums, subsetSum, taken, subset, K, N, curIdx + 1, N - 1);
        }

        for (int i = limitIdx; i >= 0; i--) {
            if (taken[i]) continue;
            int tmp = subsetSum[curIdx] + nums[i];

            if (tmp <= subset) {
                taken[i] = true;
                subsetSum[curIdx] += nums[i];
                bool nxt = canPartitionKSubsets(nums, subsetSum, taken, subset, K, N, curIdx, i - 1);
                taken[i] = false;
                subsetSum[curIdx] -= nums[i];
                if (nxt) return true;
            }
        }
        return false;
    }
};