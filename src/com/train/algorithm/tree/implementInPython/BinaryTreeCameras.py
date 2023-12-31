class TreeNode(object):
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right
class Solution(object):
    ans = 0
    def minCameraCover(self, root):
        def dfs(node):
            if not node: return 0
            val = dfs(node.left) + dfs(node.right)
            if val == 0: return 3
            if val < 3: return 0
            self.ans += 1
            return 1

        return self.ans + 1 if dfs(root) > 2 else self.ans