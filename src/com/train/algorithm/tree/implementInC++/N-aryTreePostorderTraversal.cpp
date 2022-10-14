//
// Created by 罗斯 on 14/10/2022.
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
    vector<int> postorder(Node* root) {
//        if (!root) return {};
//        vector<int> ans;
//        stack<Node*> s;
//        s.push(root);
//        while (!s.empty()) {
//            const Node* node = s.top(); s.pop();
//            ans.push_back(node->val);
//            for (auto child : node->children)
//                s.push(child);
//        }
//        reverse(begin(ans), end(ans));
//        return ans;

        vector<int> res;
        postorder(root, res);
        return res;
    }
private:
    void postorder(Node* root, vector<int>& res){
        if (!root)
            return;
        for (const auto child: root->children) {
            postorder(child, res);
        }
        res.push_back(root->val);
    }
};