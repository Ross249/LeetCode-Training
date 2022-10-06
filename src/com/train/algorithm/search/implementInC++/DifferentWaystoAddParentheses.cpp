//
// Created by 罗斯 on 6/10/2022.
//
#include <vector>
#include <string>
#include <unordered_map>

using namespace std;

class Solution {
public:
    vector<int> diffWaysToCompute(string expression) {
        unordered_map<string, vector<int>> dp;
        return computeDP(expression, dp);
    }
private:
    vector<int> computeDP(string input, unordered_map<string, vector<int>>& dp){
        vector<int> res;
        int size = input.size();
        for (int i = 0; i < size; ++i) {
            char cur = input[i];
            if (cur == '+' || cur == '-' || cur == '*'){
                vector<int> res1, res2;
                string sub = input.substr(0, i);
                if (dp.find(sub) != dp.end())
                    res1 = dp[sub];
                else
                    res1 = computeDP(sub, dp);

                sub = input.substr(i + 1);
                if (dp.find(sub) != dp.end())
                    res2 = dp[sub];
                else
                    res2 = computeDP(sub, dp);
                for (auto n1 : res1) {
                    for (auto n2 : res2) {
                        if (cur == '+' )
                            res.push_back(n1 + n2);
                        else if (cur == '-')
                            res.push_back(n1 - n2);
                        else
                            res.push_back(n1 * n2);
                    }
                }
            }
        }
        if (res.empty())
            res.push_back(atoi(input.c_str()));
        dp[input] = res;
        return res;
    }
};