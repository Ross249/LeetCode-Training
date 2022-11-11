//
// Created by 罗斯 on 12/11/2022.
//
#include <algorithm>

using namespace std;
struct TreeNode {
    int val;
    TreeNode *left;
    TreeNode *right;
    TreeNode() : val(0), left(nullptr), right(nullptr) {}
    TreeNode(int x) : val(x), left(nullptr), right(nullptr) {}
    TreeNode(int x, TreeNode *left, TreeNode *right) : val(x), left(left), right(right) {}
};

class Solution {
public:
    int minCameraCover(TreeNode* root) {
        if (dfs(root) == State::NONE) ++res;
        return res;
    }
private:
    enum class State { NONE = 0, COVERED = 1, CAMERA = 2 };
    int res = 0;
    State dfs(TreeNode* root){
        if (!root) return State::COVERED;
        State l = dfs(root->left);
        State r = dfs(root->right);
        if (l == State::NONE || r == State::NONE){
            ++res;
            return State::CAMERA;
        }
        if (l == State::CAMERA || r == State::CAMERA){
            return State::COVERED;
        }
        return State::NONE;
    }
};