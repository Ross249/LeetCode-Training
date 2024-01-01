class TreeNode(object):
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right
class Solution(object):
    def rob(self, root):
        def rob(root):
            if not root: return 0, 0, 0
            l, ll, lr = rob(root.left)
            r, rl, rr = rob(root.right)
            return max(root.val + ll + lr + rl + rr, l + r), l, r

        return rob(root)[0]