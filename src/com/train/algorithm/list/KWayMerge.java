package com.train.algorithm.list;

import java.util.Comparator;
import java.util.List;
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

    // leetcode-378
    public int kthSmallest(int[][] matrix, int k) {
        PriorityQueue<int[]> queue = new PriorityQueue<int[]>(new Comparator<int[]>(){
            @Override
            public int compare(int[] a, int[] b){
                return a[0]-b[0];
            }
        });
        int n = matrix.length;
        for (int i = 0;i < n ;i++ ) {
            queue.offer(new int[]{matrix[i][0],i,0});
        }
        for (int i = 0;i < k - 1 ;i++ ) {
            int[] temp = queue.poll();
            if (temp[2] != n - 1) {
                queue.offer(new int[]{matrix[temp[1]][temp[2] + 1],temp[1],temp[2] + 1});
            }
        }
        return queue.poll()[0];
    }

    // leetcode-632
    public int[] smallestRange(List<List<Integer>> nums) {
        int left = 0,right = Integer.MAX_VALUE;
        int min = right - left;
        int max = Integer.MIN_VALUE;
        int size = nums.size();
        int[] next = new int[size];
        PriorityQueue<Integer> queue = new PriorityQueue<Integer>(new Comparator<Integer>(){
            @Override
            public int compare(Integer index1,Integer index2){
                return nums.get(index1).get(next[index1]) - nums.get(index2).get(next[index2]);
            }
        });
        for (int i = 0;i < size ;i++ ) {
            queue.offer(i);
            max = Math.max(max,nums.get(i).get(0));
        }
        while(true){
            int minIndex = queue.poll();
            int cur = max - nums.get(minIndex).get(next[minIndex]);
            if (cur < min) {
                min = cur;
                left = nums.get(minIndex).get(next[minIndex]);
                right = max;
            }
            next[minIndex]++;
            if (next[minIndex] == nums.get(minIndex).size()) {
                break;
            }
            queue.offer(minIndex);
            max = Math.max(max,nums.get(minIndex).get(next[minIndex]));
        }
        return new int[]{left,right};
    }
}
