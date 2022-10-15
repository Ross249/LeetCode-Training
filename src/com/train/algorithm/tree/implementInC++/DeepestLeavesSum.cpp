//
// Created by 罗斯 on 15/10/2022.
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
    int deepestLeavesSum(TreeNode* root) {
        int sum = 0;
        int max_depth = 0;
        function<void(TreeNode* , int)> dfs = [&](TreeNode* n, int d){
            if (!n) return;
            if (d > max_depth){
                max_depth = d;
                sum = 0;
            }
            if (d == max_depth) sum += n->val;
            dfs(n->left, d + 1);
            dfs(n->right, d + 1);
        };
        dfs(root, 0);
        return sum;
    }
};