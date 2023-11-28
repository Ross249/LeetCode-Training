class Node(object):
    def __init__(self, val=None, children=None):
        self.val = val
        self.children = children
class Solution(object):
    def levelOrder(self, root):
        if not root:
            return []
        res = []
        level = [root]
        while level:
            cur = []
            n_level = []
            for node in level:
                cur.append(node.val)
                n_level += node.children

            res.append(cur)
            level = n_level

        return res
