package com.train.algorithm;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;

public class TemplateHardMode {
    public class ListNode {
        int val;
        ListNode next = null;
        ListNode(int val) {
            this.val = val;
        }
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    // NC-119
    public ArrayList<Integer> GetLeastNumbers_Solution(int [] input, int k) {
        ArrayList<Integer> res = new ArrayList<>();
        while (input == null || k <= 0 || k > input.length){
            return res;
        }
        Queue<Integer> queue = new PriorityQueue<>();
        for (int in : input){
            queue.add(in);
        }
        while (0 < k){
            res.add(queue.poll().intValue());
            k--;
        }
        return res;
    }
}
