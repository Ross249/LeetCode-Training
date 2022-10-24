//
// Created by 罗斯 on 24/10/2022.
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
    vector<vector<int>> levelOrder(TreeNode* root) {
//        if(!root) return {};
//        vector<vector<int>> ans;
//        vector<TreeNode*> curr,next;
//        curr.push_back(root);
//        while(!curr.empty()) {
//            ans.push_back({});
//            for(TreeNode* node : curr) {
//                ans.back().push_back(node->val);
//                if(node->left) next.push_back(node->left);
//                if(node->right) next.push_back(node->right);
//            }
//            curr.swap(next);
//            next.clear();
//        }
//        return ans;

        vector<vector<int>> res;
        dfs(root, 0, res);
        return res;
    }
private:
    void dfs(TreeNode* root, int depth, vector<vector<int>>& res){
        if (!root) return;
        while (res.size() <= depth)
            res.push_back({});
        res[depth].push_back(root->val);
        dfs(root->left, depth + 1, res);
        dfs(root->right, depth + 1, res);
    }
};