//
// Created by 罗斯 on 14/9/2022.
//
#include <vector>
#include <cmath>
using namespace std;

class Solution {
public:
    int numSquarefulPerms(vector<int>& nums) {
        sort(begin(nums), end(nums));
        vector<int> cur;
        vector<int> used(nums.size());
        int res = 0;
        dfs(nums,cur,used,res);
        return res;
    }
private:
    bool squareful(int x,int y){
        int s = sqrt(x + y);
        return s * s == x + y;
    }

    void dfs(const vector<int>& nums,vector<int>& cur,vector<int>& used,int& res){
        if(cur.size() == nums.size()){
            ++res;
            return;
        }

        for(int i = 0;i < nums.size();++i){
            if(used[i]) continue;
            if(i > 0 && !used[i-1] && nums[i] == nums[i-1]) continue;

            if(!cur.empty() && !squareful(cur.back(),nums[i])) continue;

            cur.push_back(nums[i]);
            used[i] = 1;
            dfs(nums,cur,used,res);
            used[i] = 0;
            cur.pop_back();
        }
    }
};