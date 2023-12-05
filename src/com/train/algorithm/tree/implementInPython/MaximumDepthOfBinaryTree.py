class TreeNode(object):
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right
class Solution(object):
    def maxDepth(self, root):
        if not root: return 0
        l = self.maxDepth(root.left)
        r = self.maxDepth(root.right)
        return max(l, r) + 1