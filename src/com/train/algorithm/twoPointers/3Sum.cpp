//
// Created by 罗斯 on 30/8/2022.
//
#include <vector>
#include <unordered_map>
using namespace std;

class Solution {
public:
    vector<vector<int>> threeSum(vector<int>& nums) {
//        sort(begin(nums), end(nums));
//        const int n = nums.size();
//        unordered_map<int,int> c;
//        for(int x : nums) ++c[x];
//        vector<vector<int>> res;
//        for(int i = 0;i < n;++i){
//            if(i && nums[i] == nums[i - 1]) continue;
//            for(int j = i + 1;j < n;++j) {
//                if (j - 1 != i && nums[j] == nums[j - 1]) continue;
//                const int t = 0 - nums[i] - nums[j];
//                if (t < nums[j]) continue;
//                if (!c.count(t)) continue;
//                if (c[t] >= 1 + (nums[i] == t) + (nums[j] == t))
//                    res.push_back({nums[i], nums[j], t});
//            }
//        }
//        return res;

        vector<vector<int>> res;
        sort(nums.begin(),nums.end());
        const int n = nums.size();
        for(int i = 0;i < n - 2;++i){
            if(nums[i] > 0) break;
            if(i > 0 && nums[i] == nums[i - 1]) continue;
            int l = i + 1;
            int r = n - 1;
            while (l < r){
                if(nums[i] + nums[l] + nums[r] == 0){
                    res.push_back({nums[i],nums[l++],nums[r--]});
                    while(l < r && nums[l] == nums[l - 1]) ++l;
                    while(l < r && nums[r] == nums[r + 1]) --r;
                }else if(nums[i] + nums[l] + nums[r] < 0){
                    ++l;
                }else{
                    --r;
                }
            }
        }
        return res;
    }
};