class ListNode(object):
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next


class Solution(object):
    def addTwoNumbers(self, l1, l2):
        s1,s2=0,0

        while l1!=None:
            s1 = s1*10 + l1.val
            l1 = l1.next

        while l2 != None:
            s2 = s2*10 + l2.val
            l2 = l2.next

        dList = dHead = ListNode(0)

        for i in str(s2 + s1):
            dHead.next = ListNode(i)
            dHead = dHead.next

        return dList.next