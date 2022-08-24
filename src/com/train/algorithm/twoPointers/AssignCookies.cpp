//
// Created by 罗斯 on 24/8/2022.
//
#include <vector>

using namespace std;

class Solution {
public:
    int findContentChildren(vector<int>& g, vector<int>& s) {
        sort(begin(g), end(g)); // O(glogg)
        sort(begin(s), end(s));

        int res = 0;
        int j = 0;
        for(int i = 0;i < g.size();++i){
            while (j < s.size() && s[j] < g[i]){
                ++j;
            }
            if(j < s.size()) {
                ++res;
                ++j;
                continue;
            }
        }
        return res;
    }
};
