class ListNode(object):
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next
class Solution(object):
    def reverseList(self, head):
        """
        :type head: ListNode
        :rtype: ListNode
        """
        prev, curr, next = None, head, None
        while curr:
            next, curr.next = curr.next, prev
            prev, curr = curr, next

        return prev