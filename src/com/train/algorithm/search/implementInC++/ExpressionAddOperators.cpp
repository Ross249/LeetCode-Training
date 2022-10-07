//
// Created by 罗斯 on 7/10/2022.
//
#include <vector>
#include <string>
#include <unordered_map>

using namespace std;

class Solution {
public:
    vector<string> addOperators(string num, int target) {
        vector<string> res;
        dfs(num, target, 0, "", 0, 0, &res);
        return res;
    }
private:
    void dfs(const string& num, const int target,  // input
             int pos, const string& exp, long prev, long curr, // state
             vector<string>* res){
        if (pos == num.length()){
            if (curr == target)
                res->push_back(exp);
            return;
        }

        for (int l = 1; l <= num.size() - pos; ++l) {
            string t = num.substr(pos, l);
            if (t[0] == '0' && t.length() > 1) break;
            long n = stol(t);
            if (n > INT_MAX) break;
            if (pos == 0){
                dfs(num, target, l, t, n, n, res);
                continue;
            }
            dfs(num, target, pos + l, exp + '+' + t, n, curr + n, res);
            dfs(num, target, pos + l, exp + '-' + t, -n, curr - n, res);
            dfs(num, target, pos + l, exp + '*' + t, prev * n, curr - prev + prev * n, res);
        }
    }
};