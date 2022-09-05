//
// Created by 罗斯 on 5/9/2022.
//
#include <vector>
using namespace std;

class Solution {
public:
    vector<vector<int>> combinationSum2(vector<int>& candidates, int target) {
        vector<vector<int>> res;
        sort(candidates.begin(),candidates.end());
        vector<int> cur;
        dfs(candidates,target,0,cur,res);
        return res;
    }
private:
    void dfs(vector<int>& candidates, int target,int s,vector<int>& cur,vector<vector<int>>& res){
        if(target == 0){
            res.push_back(cur);
            return;
        }
        for(int i = s ;i < candidates.size();++i){
            int num = candidates[i];
            if(num > target) return;
            if(i > s && candidates[i] == candidates[i - 1]) continue;
            cur.push_back(num);
            dfs(candidates,target - num,i + 1,cur,res);
            cur.pop_back();
        }
    }
};