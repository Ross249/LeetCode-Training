class ListNode:
    def __init__(self, val):
        self.val = val
        self.next = None
class MyLinkedList(object):

    def __init__(self):
        self.head = None
        self.size = 0

    def get(self, index):
        if index < 0 or index >= self.size:
            return -1
        cur = self.head
        for _ in range(0, index):
            cur = cur.next

        return cur.val

    def addAtHead(self, val):
        self.addAtIndex(0, val)

    def addAtTail(self, val):
        self.addAtIndex(self.size, val)

    def addAtIndex(self, index, val):
        if index > self.size:
            return
        cur = self.head
        n_node = ListNode(val)

        if index <= 0:
            n_node.next = cur
            self.head = n_node
        else:
            for _ in range(0,index - 1):
                cur = cur.next
            n_node.next = cur.next
            cur.next = n_node
        self.size += 1

    def deleteAtIndex(self, index):
        if index < 0 or index >= self.size:
            return
        cur = self.head
        if index == 0:
            self.head = self.head.next
        else:
            for _ in range(0, index - 1):
                cur = cur.next
            cur.next = cur.next.next

        self.size -= 1

# Your MyLinkedList object will be instantiated and called as such:
# obj = MyLinkedList()
# param_1 = obj.get(index)
# obj.addAtHead(val)
# obj.addAtTail(val)
# obj.addAtIndex(index,val)
# obj.deleteAtIndex(index)