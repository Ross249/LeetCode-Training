//
// Created by 罗斯 on 13/10/2022.
//
#include <vector>
#include <stack>

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
    vector<int> preorder(Node* root) {
//        if (!root) return {};
//        vector<int> ans;
//        stack<Node*> s;
//        s.push(root);
//        while (!s.empty()) {
//            const Node* node = s.top(); s.pop();
//            ans.push_back(node->val);
//            for (auto it = node->children.rbegin(); it != node->children.rend(); ++it)
//                s.push(*it);
//        }
//        return ans;

        vector<int> res;
        preorder(root, res);
        return res;
    }
private:
    void preorder(Node* root, vector<int>& res){
        if (!root)
            return;
        res.push_back(root->val);
        for (const auto& child:root->children) {
            preorder(child,res);
        }
    }
};