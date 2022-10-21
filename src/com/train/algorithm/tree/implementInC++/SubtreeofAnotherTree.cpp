//
// Created by 罗斯 on 21/10/2022.
//
#include <vector>
#include <algorithm>
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
    bool isSubtree(TreeNode* root, TreeNode* subRoot) {
        if (!subRoot && !root) return true;
        if (!subRoot || !root) return false;

        getDepth(root, getDepth(subRoot, -1));
        for (TreeNode* n: nodes) {
            if (identical(n, subRoot))
                return true;
        }
        return false;
    }

private:
    vector<TreeNode*> nodes;

    int getDepth(TreeNode* r, int d){
        if (!r)
            return -1;
        int depth = max(getDepth(r->left, d), getDepth(r->right, d)) + 1;
        if (depth == d)
            nodes.push_back(r);
        return depth;
    }

    bool identical(TreeNode* a, TreeNode* b){
        if (!a && !b) return true;
        if (!a || !b || a->val != b->val) return false;

        return identical(a->left, b->left) && identical(a->right, b->right);
    }
};