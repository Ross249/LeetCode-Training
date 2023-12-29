class TreeNode(object):
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right
class Solution(object):
    def diameterOfBinaryTree(self, root):
        if not root: return 0
        d = {None:-1}
        s = [root]
        res = 0
        while s:
            node = s[-1]
            if node.left in d and node.right in d:
                s.pop()
                l = d[node.left]+1
                r = d[node.right]+1
                res = max(res, l+r)
                d[node] = max(l, r)
            else:
                if node.left: s.append(node.left)
                if node.right: s.append(node.right)
        return res