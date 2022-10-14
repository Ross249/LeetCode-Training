//
// Created by 罗斯 on 15/10/2022.
//
#include <vector>
#include <map>
#include <set>
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
    vector<vector<int>> verticalTraversal(TreeNode* root) {
        if (!root)
            return {};
        int min_x = INT_MAX;
        int max_x = INT_MIN;
        map<pair<int, int>, multiset<int>> h;
        traverse(root, 0, 0, h, min_x, max_x);
        vector<vector<int>> res(max_x - min_x + 1);
        for (const auto& m:h) {
            int x = m.first.second - min_x;
            res[x].insert(end(res[x]), begin(m.second), end(m.second));
        }
        return res;
    }
private:
    void traverse(TreeNode* root, int x, int y,
                  map<pair<int, int>, multiset<int>>& h,
                  int& min_x,
                  int& max_x){
        if (!root)
            return;
        min_x = min(min_x, x);
        max_x = max(max_x, x);
        h[{y, x}].insert(root->val);
        traverse(root->left, x - 1, y + 1, h, min_x, max_x);
        traverse(root->right, x + 1, y + 1, h, min_x, max_x);
    }
};