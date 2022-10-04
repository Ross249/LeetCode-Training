//
// Created by 罗斯 on 4/10/2022.
//
#include <vector>
#include <string>

using namespace std;

class Solution {
public:
    vector<string> restoreIpAddresses(string s) {
        vector<string> res;
        string ip;
        dfs(0, s, ip, res);
        return res;
    }

private:
    void dfs(int d, string s, string ip, vector<string> &res){
        int l = s.length();
        if (d == 4){
            if (l == 0)
                res.push_back(ip);
            return;
        }

        for (int i = 1; i <= min(3, s[0] == '0' ? 1 : l); ++i) {
            string ss = s.substr(0, i);
            if (i == 3 && stoi(ss) > 255) return;
            dfs(d + 1, s.substr(i), ip + (d == 0 ? "" : ".") + ss, res);
        }
    }
};