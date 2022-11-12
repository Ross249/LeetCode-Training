//
// Created by 罗斯 on 13/11/2022.
//
#include <algorithm>
#include <unordered_map>

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
    int rob(TreeNode* root) {
//        if (root == nullptr) return 0;
//        if (m_.count(root)) return m_[root];
//        int val = root->val;
//        int ll = root->left ? rob(root->left->left) : 0;
//        int lr = root->left ? rob(root->left->right) : 0;
//        int rl = root->right ? rob(root->right->left) : 0;
//        int rr = root->right ? rob(root->right->right) : 0;
//        return m_[root] = max(val + ll + lr + rl + rr, rob(root->left) + rob(root->right));

        int l = 0;
        int r = 0;
        return rob(root, l, r);
    }
private:
    unordered_map<TreeNode* ,int> m_;

    int rob(TreeNode* root, int& l, int& r) {
        if (root == nullptr) return 0;
        int ll = 0;
        int lr = 0;
        int rl = 0;
        int rr = 0;
        l = rob(root->left, ll, lr);
        r = rob(root->right, rl, rr);
        return max(root->val + ll + lr + rl + rr, l + r);
    }
};