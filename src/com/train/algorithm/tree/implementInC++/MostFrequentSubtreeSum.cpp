//
// Created by 罗斯 on 8/11/2022.
//
#include <vector>
#include <unordered_map>

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
    vector<int> findFrequentTreeSum(TreeNode* root) {
        unordered_map<int, int> freq;
        int max_freq = -1;
        vector<int> res;
        (void)treeSum(root, freq, max_freq, res);
        return res;
    }
private:
    int treeSum(TreeNode* root, unordered_map<int, int>& freq, int& max_freq, vector<int>& res){
        if (!root) return 0;
        int sum = root->val + treeSum(root->left, freq, max_freq, res) + treeSum(root->right, freq, max_freq, res);
        int f = ++freq[sum];
        if (f > max_freq){
            max_freq = f;
            res.clear();
        }
        if (f == max_freq){
            res.push_back(sum);
        }
        return sum;
    }
};