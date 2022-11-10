//
// Created by 罗斯 on 9/11/2022.
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
    int maxPathSum(TreeNode* root) {
        if (!root) return 0;
        int res = INT_MIN;
        maxPathSum(root, res);
        return res;
    }
private:
    int maxPathSum(TreeNode* root, int& res) {
        if (!root) return 0;
        int l = max(maxPathSum(root->left, res), 0);
        int r = max(maxPathSum(root->right, res), 0);
        int sum = l + r + root->val;
        res = max(sum, res);
        return max(l, r) + root->val;
    }
};