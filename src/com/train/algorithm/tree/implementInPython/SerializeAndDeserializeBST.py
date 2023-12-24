from collections import deque


class TreeNode(object):
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None

class Codec:

    def serialize(self, root):
        if not root: return ""
        stack = [root]
        serialized = ""
        while stack:
            node = stack.pop()
            if not node:
                serialized += "$,"
            else:
                serialized += str(node.val) + ","
                stack.append(node.right)
                stack.append(node.left)
        return serialized[:-1]

    def new_deserialize(self, queue):
        value = queue.popleft()
        if value == "$":
            return None
        node = TreeNode(int(value))
        node.left = self.new_deserialize(queue)
        node.right = self.new_deserialize(queue)
        return node

    def deserialize(self, data):
        if not data: return None
        values = data.split(",")
        queue = deque(values)
        return self.new_deserialize(queue)