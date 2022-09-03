//
// Created by 罗斯 on 3/9/2022.
//
#include <vector>
#include <string>
using namespace std;
class Solution {
public:
    vector<string> letterCombinations(string digits) {
        if(digits.empty()) return {};
        vector<vector<char>> d(10);
        d[0] = {' '};
        d[1] = {};
        d[2] = {'a','b','c'};
        d[3] = {'d','e','f'};
        d[4] = {'g','h','i'};
        d[5] = {'j','k','l'};
        d[6] = {'m','n','o'};
        d[7] = {'p','q','r','s'};
        d[8] = {'t','u','v'};
        d[9] = {'w','x','y','z'};

//        string cur;
//        vector<string> res;
//        dfs(digits,d,0,cur,res);
        vector<string> res{""};
        for(char digit:digits){
            vector<string> tmp;
            for(const string& s : res)
                for(char c : d[digit - '0'])
                    tmp.push_back(s + c);
            res.swap(tmp);
        }

        return res;
    }
private:
    void dfs(const string& digits,const vector<vector<char>>& d,int l,string& cur,vector<string>& res){
        if(l == digits.length()){
            res.push_back(cur);
            return;
        }

        for(const char c : d[digits[l] - '0']){
            cur.push_back(c);
            dfs(digits,d,l+1,cur,res);
            cur.pop_back();
        }
    }
};