from collections import deque


class TreeNode(object):
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right
class Solution(object):
    def levelOrder(self, root):
        if not root:
            return []
        q = deque()
        q.append(root)
        res = []
        while len(q):
            level = []
            for i in range(len(q)):
                e = q.popleft()
                level.append(e.val)
                if e.left:
                    q.append(e.left)
                if e.right:
                    q.append(e.right)
            res.append(level)
        return res