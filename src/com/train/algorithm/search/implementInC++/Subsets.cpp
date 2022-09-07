//
// Created by 罗斯 on 7/9/2022.
//
#include <vector>
using namespace std;

class Solution {
public:
    vector<vector<int>> subsets(vector<int>& nums) {
//        vector<vector<int>> res;
//        vector<int> cur;
//        for (int i = 0; i <= nums.size(); ++i) {
//            dfs(nums,i,0,res,cur);
//        }
//        return res;
        const int n = nums.size();
        vector<vector<int>> res;
        for (int i = 0; i < 1 << n ; ++i) {
            vector<int> cur;
            for (int j = 0; j < n; ++j) {
                if (i & (1 << j)) cur.push_back(nums[j]);
            }
            res.push_back(cur);
        }
        return res;
    }
private:
    void dfs(vector<int>& nums,int n,int s,vector<vector<int>>& res,vector<int>& cur){
        if(n == cur.size()){
            res.push_back(cur);
            return;
        }

        for (int i = s; i < nums.size(); ++i) {
            cur.push_back(nums[i]);
            dfs(nums,n,i+1,res,cur);
            cur.pop_back();
        }
    }
};