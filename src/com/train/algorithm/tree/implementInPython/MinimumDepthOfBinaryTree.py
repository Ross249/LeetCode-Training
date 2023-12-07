class TreeNode(object):
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right
class Solution(object):
    def minDepth(self, root):
        if not root: return 0
        if not root.left and not root.right: return 1
        l = self.minDepth(root.left)
        r = self.minDepth(root.right)
        if not root.left: return r + 1
        if not root.right: return l + 1
        return 1 + min(l, r)