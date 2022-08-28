//
// Created by 罗斯 on 28/8/2022.
//
#include <vector>

using namespace std;

class Solution {
public:
    int numRescueBoats(vector<int>& people, int limit) {
//        sort(people.rbegin(),people.rend());
//        int i = 0;
//        int j = people.size() - 1;
//        int res = 0;
//        while(i <= j){
//            ++res;
//            if(i == j) break;
//            if(people[i++] + people[j] <= limit) --j;
//        }
//        return res;


        sort(people.begin(), people.end());
        int i = 0;
        int j = people.size() - 1;
        int res = 0;
        while(i <= j){
            if(people[i] + people[j] <= limit){
                ++i;
                --j;
            }else
                --j;

            ++res;
        }
        return res;
    }
};