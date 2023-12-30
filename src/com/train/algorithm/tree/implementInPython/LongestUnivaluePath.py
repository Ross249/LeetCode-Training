class TreeNode(object):
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right
class Solution(object):
    def longestUnivaluePath(self, root):
        self.res = 0
        def helper(node, parent_val):
            if not node:
                return 0
            left = helper(node.left, node.val)
            right = helper(node.right, node.val)
            self.res = max(self.res, left + right)
            return 1 + max(left, right) if node.val == parent_val else 0

        helper(root, None)

        return self.res
