//
// Created by 罗斯 on 10/9/2022.
//

#include <vector>
using namespace std;

class Solution {
public:
    vector<vector<int>> permute(vector<int>& nums) {
        const int n = nums.size();
        vector<vector<int>> res;
        vector<int> used(n);
        vector<int> cur;
        dfs(res,used,cur,0,n,nums);
        return res;
    }
private:
    void dfs(vector<vector<int>>& res,vector<int>& used,vector<int>& cur,int s,int n,vector<int>& nums){
        if(s == n){
            res.push_back(cur);
            return;
        }
        for(int i = 0;i < n;++i){
            if(used[i]) continue;
            used[i] = 1;
            cur.push_back(nums[i]);
            dfs(res,used,cur,s+1,n,nums);
            cur.pop_back();
            used[i] = 0;
        }
    }
};