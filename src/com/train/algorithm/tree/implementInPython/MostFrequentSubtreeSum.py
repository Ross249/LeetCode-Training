import collections


class TreeNode(object):
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right
class Solution(object):
    def findFrequentTreeSum(self, root):
        if not root: return []
        D = collections.defaultdict(int)
        def cal_sum(node):
            if not node: return 0
            rv = node.val + cal_sum(node.left) + cal_sum(node.right)
            D[rv] += 1
            return rv
        cal_sum(root)
        mx = max(D.values())
        return [k for k,v in D.items() if v==mx]