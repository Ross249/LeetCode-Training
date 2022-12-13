//
// Created by 罗斯 on 14/12/2022.
//
#include <vector>
#include <string>
#include <unordered_map>
#include <algorithm>

using namespace std;

class Solution {
public:
    bool wordBreak(string s, vector<string>& wordDict) {
        for (const string& word : wordDict){
            mem_.emplace(word, true);
        }
        return wordBreak(s);
    }

    bool wordBreak(const string& word){
        if (mem_.count(word)) return mem_[word];
        for (int i = 1; i < word.length(); ++i) {
            auto item = mem_.find(word.substr(i));
            if (item != mem_.end() && item->second && wordBreak(word.substr(0, i)))
                return mem_[word] = true;
        }
        return mem_[word] = false;
    }
private:
    unordered_map<string, bool> mem_;
};