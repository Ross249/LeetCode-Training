# Definition for singly-linked list.
class ListNode(object):
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next
class Solution(object):
    def insertionSortList(self, head):
        s_head = ListNode(0)
        s_head.next = head
        s_tail = head

        cur = head.next
        while cur:
            if cur.val >= s_tail.val:
                s_tail = cur
                cur = cur.next
            else:
                s_tail.next = cur.next
                insert_pos = s_head
                while insert_pos.next.val < cur.val:
                    insert_pos = insert_pos.next
                cur.next = insert_pos.next
                insert_pos.next = cur

                cur = s_tail.next

        return s_head.next