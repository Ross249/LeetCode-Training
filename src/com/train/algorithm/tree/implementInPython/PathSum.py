class TreeNode(object):
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right
class Solution(object):
    def hasPathSum(self, root, targetSum):
        if not root: return False
        if not root.left and not root.right: return root.val == targetSum
        l_sum = self.hasPathSum(root.left,targetSum - root.val)
        r_sum = self.hasPathSum(root.right, targetSum - root.val)
        return  l_sum or r_sum