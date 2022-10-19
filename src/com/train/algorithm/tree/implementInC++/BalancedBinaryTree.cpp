//
// Created by 罗斯 on 20/10/2022.
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
    bool isBalanced(TreeNode* root) {
//        if(!root) return true;
//        int left_height = height(root->left);
//        int right_height = height(root->right);
//        return (abs(left_height - right_height)<=1) && isBalanced(root->left) && isBalanced(root->right);

        if (!root) return true;
        bool balance = true;
        height(root, &balance);
        return balance;
    }
private:
    int height(TreeNode* root, bool* balance){
        if (!root) return 0;
        int left = height(root->left, balance);
        if (!balance) return -1;
        int right = height(root->right, balance);
        if (!balance) return -1;
        if (abs(left - right) > 1){
            *balance = false;
            return -1;
        }
        return max(left, right) + 1;
    }
    int height(TreeNode* root) {
        if(!root) return 0;
        return max(height(root->left), height(root->right))+1;
    }
};