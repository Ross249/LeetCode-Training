//
// Created by 罗斯 on 12/10/2022.
//
#include <vector>
#include <queue>

using namespace std;

class Node {
public:
    int val;
    vector<Node*> children;

    Node() {}

    Node(int _val) {
        val = _val;
    }

    Node(int _val, vector<Node*> _children) {
        val = _val;
        children = _children;
    }
};

class Solution {
public:
    vector<vector<int>> levelOrder(Node* root) {
//        if (!root) return {};
//        vector<vector<int>> ans;
//        queue<Node*> q;
//        q.push(root);
//        int depth = 0;
//        while (!q.empty()) {
//            int size = q.size();
//            ans.push_back({});
//            while (size--) {
//                Node* n = q.front(); q.pop();
//                ans[depth].push_back(n->val);
//                for (auto child : n->children)
//                    q.push(child);
//            }
//            ++depth;
//        }
//        return ans;

        vector<vector<int>> res;
        preorder(root, 0, res);
        return res;
    }
private:
    void preorder(Node* root, int d, vector<vector<int>>& res){
        if (root == nullptr)
            return;
        while (res.size() <= d)
            res.push_back({});
        res[d].push_back(root->val);
        for (auto child: root->children) {
            preorder(child, d + 1, res);
        }
    }
};