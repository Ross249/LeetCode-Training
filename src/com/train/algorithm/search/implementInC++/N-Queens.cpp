//
// Created by 罗斯 on 18/9/2022.
//
#include <vector>
#include <string>
using namespace std;

class Solution {
public:
    vector<vector<string>> solveNQueens(int n) {
        sols_.clear();
        board_ = vector<string>(n,string(n,'.'));

        cols_ = vector<int>(n,0);
        diag1_ = vector<int>(2 * n - 1, 0);
        diag2_ = vector<int>(2 * n - 1, 0);

        nQueens(n,0);
        return sols_;
    }
private:
    vector<string> board_;
    vector<int> cols_;
    vector<int> diag1_;
    vector<int> diag2_;
    vector<vector<string>> sols_;

    bool available(int x,int y,int n){
        return !cols_[x] && !diag1_[x + y] && !diag2_[x - y + n - 1];
    }

    void updateBoard(int x,int y,int n,bool is_put){
        cols_[x] = is_put;
        diag1_[x + y] = is_put;
        diag2_[x - y + n - 1] = is_put;
        board_[y][x] = is_put ? 'Q':'.';
    }

    void nQueens(const int n,const int y){
        if(y == n){
            sols_.push_back(board_);
            return;
        }

        for (int x = 0; x < n; ++x) {
            if(!available(x,y,n)) continue;
            updateBoard(x,y,n, true);
            nQueens(n,y+1);
            updateBoard(x,y,n,false);
        }
    }
};