package com.train.algorithm.list;

public class MergeTwoLists {
    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
            next = null;
        }
    }
    public ListNode mergeTwoLists(ListNode l1,ListNode l2){
        if (l1 == null){
            return l2;
        }
        if (l2 == null){
            return l1;
        }
        ListNode head = null;
        if (l1.val < l2.val){
            head = l1;
            head.next = mergeTwoLists(l1.next,l2);
        }else {
            head = l2;
            head.next = mergeTwoLists(l1, l2.next);
        }
        return head;
    }

    public int Fibonacci(int n){
        if (1==n||2==n){
            return 1;
        }
        int num1 = 1,num2 = 1;
        int res = 1;
        for (int i = 2;i <= n;i++){
            res = num1+num2;
            num1 = num2;
            num2 = res;
        }
        return res;
    }
}
