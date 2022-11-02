//
// Created by 罗斯 on 3/11/2022.
//

#include <vector>
#include <string>
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
    vector<string> binaryTreePaths(TreeNode* root) {
        vector<string> res;
        string s;
        function<void(TreeNode*, int)> preorder = [&](TreeNode* node, int l){
            if (!node) return;
            s += (l > 0 ? "->" : "") + to_string(node->val);
            if (!node->left && !node->right)
                res.push_back(s);
            preorder(node->left, s.size());
            preorder(node->right, s.size());
            while (s.size() != l)
                s.pop_back();
        };
        preorder(root, 0);
        return res;
    }
};