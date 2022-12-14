//
// Created by 罗斯 on 15/12/2022.
//
#include <vector>
#include <string>
#include <unordered_map>
#include <unordered_set>
#include <algorithm>

using namespace std;

class Solution {
public:
    vector<string> wordBreak(string s, vector<string>& wordDict) {
        unordered_set<string> dict(wordDict.cbegin(), wordDict.cend());
        return wordBreak(s, dict);
    }
private:
    unordered_map<string, vector<string>> mem_;

    vector<string> append(const vector<string>& prefixes, const string& word){
        vector<string> res;
        for (const auto& prefix : prefixes) {
            res.push_back(prefix + " " + word);
        }
        return res;
    }

    const vector<string>& wordBreak(string s, unordered_set<string>& dict){
        if (mem_.count(s)) return mem_[s];

        vector<string> res;
        if (dict.count(s))
            res.push_back(s);
        for (int i = 1; i < s.length(); ++i) {
            const string& right  = s.substr(i);
            if (!dict.count(right)) continue;

            const string& left = s.substr(0, i);
            const vector<string> left_res = append(wordBreak(left, dict), right);

            res.insert(res.end(), left_res.begin(), left_res.end());
        }
        mem_[s].swap(res);
        return mem_[s];
    }
};