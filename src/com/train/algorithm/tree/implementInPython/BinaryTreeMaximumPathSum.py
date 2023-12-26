import sys


class TreeNode(object):
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right
class Solution(object):
    def _max(self, root):
        if not root: return -sys.maxint
        l = max(0, self._max(root.left))
        r = max(0, self._max(root.right))
        self.res = max(self.res, root.val + l + r)
        return root.val + max(l, r)
    def maxPathSum(self, root):
        self.res = -sys.maxint
        self._max(root)
        return self.res