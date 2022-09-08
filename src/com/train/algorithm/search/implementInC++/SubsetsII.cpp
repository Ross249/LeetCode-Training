//
// Created by 罗斯 on 8/9/2022.
//
#include <vector>
using namespace std;

class Solution {
public:
    vector<vector<int>> subsetsWithDup(vector<int>& nums) {
        const int n = nums.size();
        sort(begin(nums), end(nums));
        vector<vector<int>> res;
        vector<int> cur;
        dfs(res,cur,nums,n,0);
        return res;
    }
private:
    void dfs(vector<vector<int>>& res,vector<int>& cur,vector<int>& nums,int n,int s){
        res.push_back(cur);
        if(cur.size() == n) return;
        for(int i = s;i < n;++i){
            if(i > s && nums[i] == nums[i-1]) continue;
            cur.push_back(nums[i]);
            dfs(res,cur,nums,n,i+1);
            cur.pop_back();
        }
    }
};