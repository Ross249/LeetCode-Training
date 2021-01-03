package com.train.algorithm.list;

// 快慢指针
public class FastAndSlowPointers {
    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    // leetcode-141
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        ListNode a = head,b = head.next;
        while(a!=b){
            if (b == null || b.next == null) {
                return false;
            }
            a = a.next;
            b = b.next.next;
        }
        return true;
    }

    // leetcode-202
    public int deliverSum(int n){
        int sum = 0;
        while(n > 0){
            int a = n % 10;
            sum += a*a;
            n = n / 10;
        }
        return sum;
    }
    public boolean isHappy(int n) {
        int fast = n,slow = n;
        do{
            slow = deliverSum(slow);
            fast = deliverSum(fast);
            fast = deliverSum(fast);
        }while(slow != fast);
        return slow == 1;
    }
}
