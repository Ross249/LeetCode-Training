//
// Created by 罗斯 on 15/9/2022.
//
#include <vector>
#include <string>
using namespace std;


class Solution {
public:
    vector<string> generateParenthesis(int n) {
        vector<string> res;
        string cur;
        if(n > 0) dfs(n,n,cur,res);
        return res;
    }

private:
    void dfs(int l,int r,string& cur,vector<string>& res){
        if (l + r == 0){
            res.push_back(cur);
            return;
        }
        if(r < l) return;
        if(l > 0){
            dfs(l - 1,r,cur += "(",res);
            cur.pop_back();
        }
        if(r > 0){
            dfs(l,r-1,cur+=")",res);
            cur.pop_back();
        }
    }
};