class TreeNode(object):
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right
class Solution(object):
    def deepestLeavesSum(self, root):
        sums = []
        def dfs(node, level):
            if level == len(sums): sums.append(node.val)
            else: sums[level] += node.val
            if node.left: dfs(node.left, level + 1)
            if node.right: dfs(node.right, level + 1)
        dfs(root, 0)
        return sums[-1]