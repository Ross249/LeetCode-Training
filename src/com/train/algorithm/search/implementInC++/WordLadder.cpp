//
// Created by 罗斯 on 22/9/2022.
//
#include <vector>
#include <string>
#include <unordered_set>
#include <queue>
using namespace std;

class Solution {
public:
    int ladderLength(string beginWord, string endWord, vector<string>& wordList) {
        unordered_set<string> dict(wordList.begin(),wordList.end());
        if (!dict.count(endWord)) return 0;

        queue<string> q;
        q.push(beginWord);

        int l = beginWord.length();
        int step = 0;

        while (!q.empty()){
            ++step;
            for (int size = q.size(); size > 0 ; --size) {
                string w = q.front();
                q.pop();
                for (int i = 0; i < l; ++i) {
                    char ch = w[i];
                    for (int j = 'a'; j <= 'z'; ++j) {
                        w[i] = j;
                        if (w == endWord) return step + 1;
                        if (!dict.count(w)) continue;
                        dict.erase(w);
                        q.push(w);
                    }
                    w[i] = ch;
                }
            }
        }
        return 0;
    }
};