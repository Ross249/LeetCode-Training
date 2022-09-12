//
// Created by 罗斯 on 12/9/2022.
//
#include <vector>
#include <string>
using namespace std;


class Solution {
public:
    vector<string> letterCasePermutation(string s) {
        vector<string> res;
        dfs(s,0,res);
        return res;
    }

private:
    void dfs(string& s,int i,vector<string>& res){
        if(i == s.length()){
            res.push_back(s);
            return;
        }

        dfs(s,i+1,res);
        if(!isalpha(s[i])) return;
        s[i] ^= (1 << 5);
        dfs(s,i+1,res);
        s[i] ^= (1 << 5);
    }
};