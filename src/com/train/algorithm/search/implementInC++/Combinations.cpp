//
// Created by 罗斯 on 6/9/2022.
//
#include <vector>
using namespace std;

class Solution {
public:
    vector<vector<int>> combine(int n, int k) {
        vector<vector<int>> res;
        vector<int> cur;
        dfs(res,cur,0,n,k);
        return res;
    }
private:
    void dfs(vector<vector<int>>& res,vector<int>& cur,int s,int n,int k){
        if(cur.size() == k){
            res.push_back(cur);
            return;
        }
        for (int i = s; i < n; ++i) {
            cur.push_back(i+1);
            dfs(res,cur,i+1,n,k);
            cur.pop_back();
        }
    }
};