//
// Created by 罗斯 on 21/10/2022.
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
    int minDepth(TreeNode* root) {
        if (!root) return 0;
        if (!root->left && !root->right) return 1;
        int l = root->left ? minDepth(root->left) : INT_MAX;
        int r = root->right ? minDepth(root->right) : INT_MAX;
        return min(l, r) + 1;
    }
};