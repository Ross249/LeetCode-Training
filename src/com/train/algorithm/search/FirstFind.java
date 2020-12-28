package com.train.algorithm.search;

import java.util.Vector;

public class FirstFind {
    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public ListNode findFirstCommon(ListNode pHead1,ListNode pHead2){
        if (null == pHead1||null ==pHead2){
            return null;
        }
        Vector<ListNode> listNodes1 = new Vector<>();
        while (null != pHead1){
            listNodes1.add(pHead1);
            pHead1 = pHead1.next;
            if (listNodes1.contains(pHead1)){
                break;
            }
        }
        Vector<ListNode> listNodes2 = new Vector<>();
        while (null!=pHead2){
            if (listNodes1.contains(pHead2)){
                return pHead2;
            }
            listNodes2.add(pHead2);
            pHead2 = pHead2.next;
            if (listNodes2.contains(pHead2)){
                break;
            }
        }
        return null;
    }
}
