//
// Created by 罗斯 on 25/10/2022.
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
    vector<vector<int>> levelOrderBottom(TreeNode* root) {
        vector<vector<int>> res;
        dfs(root, 0, res);
        reverse(res.begin(), res.end());
        return res;
    }
private:
    void dfs(TreeNode *root, int depth, vector<vector<int>>& res){
        if (!root)
            return;

        while (res.size() <= depth)
            res.push_back({});
        res[depth].push_back(root->val);
        dfs(root->left, depth + 1, res);
        dfs(root->right, depth + 1, res);
    }
};