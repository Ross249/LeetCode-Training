# Definition for singly-linked list.
class ListNode(object):
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next


class Solution(object):
    def addTwoNumbers(self, l1, l2):
        """
        :type l1: ListNode
        :type l2: ListNode
        :rtype: ListNode
        """
        nHead = ListNode(0)
        tail = nHead
        carry = 0

        while l1 is not None or l2 is not None or carry != 0:
            d1 = l1.val if l1 is not None else 0
            d2 = l2.val if l2 is not None else 0

            sum = d1 + d2 + carry
            d = sum % 10
            carry = sum // 10

            nNode = ListNode(d)
            tail.next = nNode
            tail = tail.next

            l1 = l1.next if l1 is not None else None
            l2 = l2.next if l2 is not None else None

        res = nHead.next
        nHead.next = None
        return res