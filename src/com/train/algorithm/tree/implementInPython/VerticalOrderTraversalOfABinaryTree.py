class TreeNode(object):
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right
class Solution(object):
    def verticalTraversal(self, root):
        if not root: return []
        vals = []
        def preorder(root, x, y):
            if not root: return
            vals.append((x, y, root.val))
            preorder(root.left, x-1, y+1)
            preorder(root.right, x+1, y+1)
        preorder(root, 0, 0)
        res = []
        last_x = -1000
        for x,y, val in sorted(vals):
            if x!=last_x:
                res.append([])
                last_x = x
            res[-1].append(val)
        return res