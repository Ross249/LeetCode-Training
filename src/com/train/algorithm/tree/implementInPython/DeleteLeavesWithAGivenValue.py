class TreeNode(object):
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right
class Solution(object):
    def removeLeafNodes(self, root, target):
        def dfs(node):
            if not node:
                return None
            if node.val == target and not node.left and not node.right:
                return None
            node.left = dfs(node.left)
            node.right = dfs(node.right)

            if node.val == target and not node.left and not node.right:
                return None
            return node
        return dfs(root)