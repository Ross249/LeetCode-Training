//
// Created by 罗斯 on 19/9/2022.
//
#include <vector>
#include <string>
using namespace std;

class Solution {
public:
    int totalNQueens(int n) {
        vector<int> cols(n);
        vector<int> diag1(2 * n - 1);
        vector<int> diag2(2 * n - 1);
        int res = 0;
        dfs(cols,diag1,diag2,res,0,n);
        return res;
    }

private:
    void dfs(vector<int>& cols,vector<int>& diag1,vector<int>& diag2,int res,int r,int n){
        if(r == n){
            ++res;
            return;
        }

        for (int i = 0; i < n; ++i) {
            int& c = cols[i];
            int& d1 = diag1[r + i];
            int& d2 = diag2[r - i + n - 1];
            if(c || d1 || d2) continue;
            c = d1 = d2 = 1;
            dfs(cols,diag1,diag2,res,r + 1,n);
            c = d1 = d2 = 0;
        }
    }
};