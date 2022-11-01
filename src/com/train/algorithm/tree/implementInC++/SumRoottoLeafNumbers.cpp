//
// Created by 罗斯 on 2/11/2022.
//
#include <functional>
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
    int sumNumbers(TreeNode* root) {
        int res = 0;
        function<void(TreeNode*, int)> traverse = [&](TreeNode* t, int num){
            if (!t) return ;
            num = num * 10 + t->val;
            if (t->left || t->right){
                traverse(t->left, num);
                traverse(t->right, num);
            }else {
                res += num;
            }
        };

        traverse(root, 0);
        return res;
    }
};