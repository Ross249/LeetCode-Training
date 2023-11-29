class Node(object):
    def __init__(self, val=None, children=None):
        self.val = val
        self.children = children

class Solution(object):
    def traverse(self, root, res):
        if root is None: return
        res.append(root.val)
        for child in root.children:
            self.traverse(child, res)
    """
    def preorder(self, root):
        res = []
        self.traverse(root, res)
        return res
    """

    def preorder(self, root):
        if root is None:
            return []

        stack = [root]
        output = []

        while stack:
            temp = stack.pop()

            output.append(temp.val)

            stack.extend(temp.children[::-1])

        return output