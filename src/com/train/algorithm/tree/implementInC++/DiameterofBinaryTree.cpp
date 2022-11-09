//
// Created by 罗斯 on 10/11/2022.
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
    int diameterOfBinaryTree(TreeNode* root) {
        res = 0;
        diameter(root);
        return res;

//        if (!root) return 0;
//        edges_.clear();
//        int n = 0;
//        buildGraph(root, n, -1);
//        vector<vector<int>> d(n, vector<int>(n, n));
//        for (int i = 0; i < n; ++i) d[i][i] = 0;
//        for (const auto& pair : edges_)
//            d[pair.first][pair.second] = 1;
//
//        for (int k = 0; k < n; ++k)
//            for (int i = 0; i < n; ++i)
//                for (int j = 0; j < n; ++j)
//                    d[i][j] = min(d[i][j], d[i][k] + d[k][j]);
//
//        int ans = INT_MIN;
//        for (int i = 0; i < n; ++i)
//            for (int j = 0; j < n; ++j) {
//                if (d[i][j] == n) continue;
//                ans = max(ans, d[i][j]);
//            }
//        return ans;
    }
private:
    int res;
    int diameter(TreeNode* root){
        if (!root) return -1;
        int l = diameter(root->left) + 1;
        int r = diameter(root->right) + 1;
        res = max(res, l + r);
        return max(l , r);
    }

//    void buildGraph(TreeNode* node, int& id, int pid) {
//        if (!node) return;
//        int node_id = id++;
//        if (pid >= 0) {
//            edges_.emplace_back(node_id, pid);
//            edges_.emplace_back(pid, node_id);
//        }
//        buildGraph(node->left, id, node_id);
//        buildGraph(node->right, id, node_id);
//    }
//    vector<pair<int,int>> edges_;
};