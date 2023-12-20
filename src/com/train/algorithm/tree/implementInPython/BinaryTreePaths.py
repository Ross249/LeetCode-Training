class TreeNode(object):
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right
class Solution(object):
    def binaryTreePaths(self, root):
        def dfs(node, path, res):
            if not node:
                return
            path += str(node.val)
            if not node.left and not node.right:
                res.append(path)
            else:
                dfs(node.left, path + '->', res)
                dfs(node.right, path + '->', res)
        res = []
        dfs(root, '', res)
        return res