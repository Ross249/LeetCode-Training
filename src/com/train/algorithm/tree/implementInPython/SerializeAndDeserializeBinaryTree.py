class TreeNode(object):
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None

class Codec:

    def serialize(self, root):
        if not root: return  'x'
        return ','.join([str(root.val), self.serialize(root.left), self.serialize(root.right)])
    def deserialize(self, data):
        self.data = data
        if self.data[0] == 'x': return None
        node = TreeNode(self.data[:self.data.find(',')])
        node.left = self.deserialize(self.data[self.data.find(',')+1:])
        node.right = self.deserialize(self.data[self.data.find(',')+1:])
        return node
