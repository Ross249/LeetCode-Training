package com.train.algorithm.list;


import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class ReverseList {
    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public ListNode reverseList(ListNode head){
        if (null == head || null == head.next){
            return head;
        }
        ListNode pre = null;
        while (null!=head){
            ListNode temp = head;
            head = head.next;
            temp.next = pre;
            pre = temp;
        }
        return pre;
    }

    public ListNode reverseList1(ListNode head){
        if (null == head || null == head.next){
            return head;
        }
        List<Integer> list = new LinkedList<>();
        ListNode tempNode = head;
        while (null!=tempNode){
            list.add(tempNode.val);
            tempNode = tempNode.next;
        }
        Collections.reverse(list);
        tempNode = head;
        while (null != tempNode){
            tempNode.val = list.remove(0);
            tempNode = tempNode.next;
        }
        return head;
    }

    // leetcode-25
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode h = new ListNode(0);
        h.next = head;
        ListNode pre = h;
        while(head!=null){
            ListNode tail = pre;
            for (int i = 0;i < k;i++ ) {
                tail = tail.next;
                if (tail == null) {
                    return h.next;
                }
            }
            ListNode nex = tail.next;
            ListNode[] revers = reverse(head,tail);
            head = revers[0];
            tail = revers[1];
            pre.next = head;
            tail.next = nex;
            pre = tail;
            head = tail.next;
        }
        return h.next;
    }
    public ListNode[] reverse(ListNode head,ListNode tail){
        ListNode pre = tail.next;
        ListNode p = head;
        while(pre!=tail){
            ListNode temp = p.next;
            p.next = pre;
            pre = p;
            p = temp;
        }
        return new ListNode[]{tail,head};
    }
}
