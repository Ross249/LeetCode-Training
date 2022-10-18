//
// Created by 罗斯 on 18/10/2022.
//
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
    bool isSymmetric(TreeNode* root) {
        return isMirror(root, root);
    }
private:
    bool isMirror(TreeNode* root1, TreeNode* root2){
        if (!root1 && !root2) return true;
        if (!root1 || !root2) return false;
        return root1->val == root2->val && isMirror(root1->left, root2->right) && isMirror(root1->right, root2->left);
    }
};