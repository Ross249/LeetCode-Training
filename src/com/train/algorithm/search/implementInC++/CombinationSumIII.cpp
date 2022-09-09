//
// Created by 罗斯 on 9/9/2022.
//
#include <vector>
using namespace std;

class Solution {
public:
    vector<vector<int>> combinationSum3(int k, int n) {
        vector<vector<int>> res;
//        vector<int> cur;
//        dfs(k,n,1,cur,res);
//        return res;

        for(int i = 0;i < (1 << 9);++i){
            vector<int> cur;
            int sum = 0;
            for (int j = 1; j <= 9; ++j) {
                if(i & (1 << (j - 1))){
                    sum += j;
                    cur.push_back(j);
                }
            }
            if(sum == n && cur.size() == k){
                res.push_back(cur);
            }
        }
        return res;
    }
private:
    void dfs(int k, int n, int s,vector<int>& cur, vector<vector<int>>& res){
        if(k == 0) {
            if (n == 0)
                res.push_back(cur);
            return;
        }

        for(int i = s;i <= 9;++i){
            if(i > n) break;
            cur.push_back(i);
            dfs(k - 1,n - i,i+1,cur,res);
            cur.pop_back();
        }
    }
};