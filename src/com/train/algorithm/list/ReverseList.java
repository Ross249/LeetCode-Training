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
}
