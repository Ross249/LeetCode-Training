//
// Created by 罗斯 on 26/10/2022.
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
    bool leafSimilar(TreeNode* root1, TreeNode* root2) {
        vector<int> leaves1;
        vector<int> leaves2;
        getLeaves(root1, leaves1);
        getLeaves(root2, leaves2);
        return leaves1 == leaves2;
    }
private:
    void getLeaves(TreeNode* root, vector<int>& leaves){
        if (root == nullptr)
            return;
        if (root->left == nullptr && root->right == nullptr)
            leaves.push_back(root->val);
        getLeaves(root->left, leaves);
        getLeaves(root->right, leaves);
    }
};