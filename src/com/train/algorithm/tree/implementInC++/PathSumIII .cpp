//
// Created by 罗斯 on 1/11/2022.
//
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
    int pathSum(TreeNode* root, int targetSum) {
//        if (!root) return 0;
//        return numberOfPaths(root, sum) + pathSum(root->left, sum) + pathSum(root->right, sum);

        countPathSum(root, targetSum, 0);
        return count;
    }
private:
    unordered_map<long, int> map;
    int count = 0;

    int numberOfPaths(TreeNode* root, int left) {
        if (!root) return 0;
        left -= root->val;
        return (left == 0 ? 1 : 0) + numberOfPaths(root->left, left) + numberOfPaths(root->right, left);
    }

    void countPathSum(TreeNode* root, int target, long sum){
        if(!root)
            return;
        sum += root->val;
        if(sum == target)
            count++;
        if(map.find(sum - target) != map.end())
            count += map[sum - target];
        map[sum]++;
        countPathSum(root->left, target, sum);
        countPathSum(root->right, target, sum);
        map[sum]--;
    }
};