//
// Created by 罗斯 on 31/10/2022.
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
    vector<vector<int>> pathSum(TreeNode* root, int targetSum) {
        vector<vector<int>> res;
        vector<int> cur;
        pathSum(root, targetSum, res, cur);
        return res;
    }
private:
    void pathSum(TreeNode* root, int sum, vector<vector<int>>& res, vector<int>& cur){
        if (root == nullptr) return;
        if (root->left == nullptr && root->right == nullptr){
            if (root->val == sum){
                res.push_back(cur);
                res.back().push_back(root->val);
            }
            return;
        }

        cur.push_back(root->val);
        int n_sum = sum - root->val;
        pathSum(root->left, n_sum, res, cur);
        pathSum(root->right, n_sum, res, cur);
        cur.pop_back();
    }
};