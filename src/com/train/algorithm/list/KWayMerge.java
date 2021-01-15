package com.train.algorithm.list;

import java.util.PriorityQueue;

// 多路归并
public class KWayMerge {
    class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
    // leetcode-23
    class Status implements Comparable<Status>{
        int val;
        ListNode node;

        Status(int val,ListNode node){
            this.val = val;
            this.node = node;
        }

        @Override
        public int compareTo(Status node1){
            return this.val - node1.val;
        }
    }
    PriorityQueue<Status> queue = new PriorityQueue<>();
    public ListNode mergeKLists(ListNode[] lists) {
        for (ListNode n : lists) {
            if (n != null) {
                queue.offer(new Status(n.val,n));
            }
        }
        ListNode head = new ListNode(0);
        ListNode tail = head;
        while(!queue.isEmpty()){
            Status temp = queue.poll();
            tail.next = temp.node;
            tail = tail.next;
            if (temp.node.next != null) {
                queue.offer(new Status(temp.node.next.val,temp.node.next));
            }
        }
        return head.next;
    }
}
