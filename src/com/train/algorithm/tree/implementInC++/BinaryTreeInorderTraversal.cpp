//
// Created by 罗斯 on 9/10/2022.
//
#include <vector>

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
    vector<int> inorderTraversal(TreeNode* root) {
//        if (root == nullptr) return {};
//        vector<int> ans;
//        stack<TreeNode*> s;
//        TreeNode* curr = root;
//        while (curr != nullptr || !s.empty()) {
//            while (curr != nullptr) {
//                s.push(curr);
//                curr = curr->left;
//            }
//            curr = s.top(); s.pop();
//            ans.push_back(curr->val);
//            curr = curr->right;
//        }
//        return ans;
        vector<int> res;
        inorderTraversal(root, res);
        return res;
    }
private:
    void inorderTraversal(TreeNode* root, vector<int>& res){
        if (root == nullptr)
            return;
        inorderTraversal(root->left, res);
        res.push_back(root->val);
        inorderTraversal(root->right, res);
    }
};