//
// Created by 罗斯 on 27/8/2022.
//
#include <vector>

using namespace std;

class Solution {
public:
    vector<vector<int>> intervalIntersection(vector<vector<int>>& firstList, vector<vector<int>>& secondList) {
        size_t i = 0;
        size_t j = 0;
        vector<vector<int>> res;
        while(i < firstList.size() && j < secondList.size()){
            const int s = max(firstList[i][0],secondList[j][0]);
            const int e = min(firstList[i][1],secondList[j][1]);
            if(s <= e) res.push_back({s,e});
            if(firstList[i][1] < secondList[j][1])
                ++i;
            else
                ++j;
        }
        return res;
    }
};