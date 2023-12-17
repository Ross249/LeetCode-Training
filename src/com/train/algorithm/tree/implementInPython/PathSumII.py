class TreeNode(object):
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right
class Solution(object):
    def dfs(self, root, path, res, remain_sum):
        if not root: return
        path.append(root.val)
        if not root.left and not root.right and remain_sum == root.val:
            res.append(list(path))
        self.dfs(root.left, path, res, remain_sum - root.val)
        self.dfs(root.right, path, res, remain_sum - root.val)
        path.pop()
    def pathSum(self, root, targetSum):
        res = []
        self.dfs(root, [], res, targetSum)
        return res
