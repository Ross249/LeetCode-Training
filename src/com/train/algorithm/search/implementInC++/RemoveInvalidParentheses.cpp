//
// Created by 罗斯 on 16/9/2022.
//
#include <vector>
#include <string>
using namespace std;

class Solution {
public:
    vector<string> removeInvalidParentheses(string s) {
        int l = 0,r = 0;
        for(const char c : s){
            l += (c == '(');
            if(l == 0)
                r += (c == ')');
            else
                l -= (c == ')');
        }

        vector<string> res;
        dfs(s,0,l,r,res);
        return res;
    }
private:
    bool isValid(const string& s){
        int count = 0;
        for (const char c : s){
            if(c == '(') ++count;
            if(c == ')') --count;
            if(count < 0) return false;
        }
        return count == 0;
    }

    void dfs(const string& s,int start,int l,int r,vector<string>& res){
        if(l == 0 && r == 0){
            if (isValid(s)) res.push_back(s);
            return;
        }

        for(int i = start;i < s.length();++i){
            if(i != start && s[i] == s[i-1]) continue;
            if(s[i] == '(' || s[i] == ')'){
                string cur = s;
                cur.erase(i,1);
                if(r > 0 && s[i] == ')')
                    dfs(cur,i,l,r-1,res);
                else if (l > 0 && s[i] == '(')
                    dfs(cur,i,l-1,r,res);
            }
        }
    }
};