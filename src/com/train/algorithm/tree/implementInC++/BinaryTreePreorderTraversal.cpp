//
// Created by 罗斯 on 10/10/2022.
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
    vector<int> preorderTraversal(TreeNode* root) {
//        vector<int> ans;
//        stack<TreeNode*> s;
//        if (root) s.push(root);
//        while (!s.empty()) {
//            TreeNode* n = s.top();
//            ans.push_back(n->val);
//            s.pop();
//            if (n->right) s.push(n->right);
//            if (n->left) s.push(n->left);
//        }
//        return ans;
        vector<int> res;
        function<void(TreeNode*)> preorder = [&](TreeNode* n){
            if (!n)
                return;
            res.push_back(n->val);
            preorder(n->left);
            preorder(n->right);
        };
        preorder(root);
        return res;
    }
};