//
// Created by 罗斯 on 24/9/2022.
//
#include <vector>
#include <string>
#include <unordered_set>
#include <unordered_map>
#include <queue>
using namespace std;

class Solution {
public:
    int openLock(vector<string>& deadends, string target) {
        const string start = "0000";
        unordered_set<string> dead(deadends.begin(),deadends.end());
        if (dead.count(start)) return -1;
        if (target == start) return 0;

        queue<string> q1;
        queue<string> q2;
        unordered_set<string> v1{start};
        unordered_set<string> v2{target};

        int s1 = 0;
        int s2 = 0;
        q1.push(start);
        q2.push(target);
        while (!q1.empty() && !q2.empty()){
            if (!q1.empty()) ++s1;
            const int size = q1.size();
            for (int i = 0; i < size; ++i) {
                const string cur = q1.front();
                q1.pop();
                for (int j = 0; j < 4; ++j) {
                    for (int k = -1; k <= 1; k+=2) {
                        string next = cur;
                        next[j] = (next[j] - '0' + k + 10) % 10 + '0';
                        if (v2.count(next)) return s1 + s2;
                        if (dead.count(next) || v1.count(next)) continue;
                        q1.push(next);
                        v1.insert(next);
                    }
                }
            }
            swap(q1, q2);
            swap(v1, v2);
            swap(s1, s2);
        }
        return -1;
    }
};