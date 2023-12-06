class TreeNode(object):
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right
class Solution(object):
    def isBalanced(self, root):
        self.balanced = True
        def height(root):
            if not root or not self.balanced: return -1
            l = height(root.left)
            r = height(root.right)
            if abs(l - r) > 1:
                self.balanced = False
                return -1
            return max(l, r) + 1
        height(root)
        return self.balanced