class Node(object):
    def __init__(self, val=None, children=None):
        self.val = val
        self.children = children

class Solution(object):
    def postorder(self, root):
        if not root: return []
        stack = [root]
        res = []
        while len(stack):
            top = stack.pop()
            res.append(top.val)
            stack.extend(top.children or [])
        return res[::-1]