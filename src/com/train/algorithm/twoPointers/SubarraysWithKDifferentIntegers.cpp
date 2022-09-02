//
// Created by 罗斯 on 2/9/2022.
//

#include <vector>

using namespace std;
class Solution {
public:
    int subarraysWithKDistinct(vector<int>& nums, int k) {
        auto subarrays = [&nums](int k){
            vector<int> count(nums.size() + 1);
            int res = 0;
            int i = 0;
            for(int j = 0;j < nums.size();++j){
                if(count[nums[j]]++ == 0)
                    --k;
                while (k < 0)
                    if(--count[nums[i++]] == 0)
                        ++k;
                res += j - i + 1;
            }
            return res;
        };
        return subarrays(k) - subarrays(k - 1);
    }
};