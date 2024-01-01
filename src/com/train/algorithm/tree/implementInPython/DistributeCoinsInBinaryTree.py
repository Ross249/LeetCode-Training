class TreeNode(object):
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right
class Solution(object):
    def distributeCoins(self, root):
        self.moves = 0
        def helper(node):
            if not node: return 0
            left = helper(node.left)
            right = helper(node.right)

            cur = left + right + node.val - 1
            need = abs(left) + abs(right)

            self.moves += need
            return cur

        helper(root)
        return self.moves