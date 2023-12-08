class TreeNode(object):
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right
class Solution(object):
    def isSame(self, p, q):
        if p and q:
            return p.val == q.val and self.isSame(p.left, q.left) and self.isSame(p.right, q.right)
        return p is q
    def isSubtree(self, root, subRoot):
        if not root:
            return False
        if self.isSame(root, subRoot):
            return True
        return self.isSubtree(root.left, subRoot) or self.isSubtree(root.right, subRoot)
