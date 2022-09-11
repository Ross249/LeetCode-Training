//
// Created by 罗斯 on 11/9/2022.
//
#include <vector>
using namespace std;

class Solution {
public:
    vector<vector<int>> permuteUnique(vector<int>& nums) {
        sort(begin(nums), end(nums));
        vector<vector<int>> res;
        vector<int> used(nums.size());
        vector<int> cur;
        dfs(nums,cur,res,used);
        return res;
    }

private:
    void dfs(const vector<int>& nums,vector<int>& cur,vector<vector<int>>& res,vector<int>& used){
        if (cur.size() == nums.size()){
            res.push_back(cur);
            return;
        }

        for (int i = 0; i < nums.size(); ++i) {
            if(used[i]) continue;
            if(i > 0 && nums[i] == nums[i - 1] && !used[i-1]) continue;
            used[i] = 1;
            cur.push_back(nums[i]);
            dfs(nums,cur,res,used);
            cur.pop_back();
            used[i] = 0;
        }
    }
};