package com.train.algorithm.list;

// 双指针合集
public class TwoPoints {
    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
            next = null;
        }
    }
    // leetcode-剑指offer-57
    public int[] twoSum(int[] nums, int target) {
        int left = 0,right = nums.length - 1;
        int[] res = new int[2];
        while(left <= right){
            if (nums[left] + nums[right] == target) {
                res[0] = nums[left];
                res[1] = nums[right];
                break;
            }else if (nums[left] + nums[right] > target) {
                right--;
            }else if (nums[left] + nums[right] < target) {
                left++;
            }
        }
        return res;
    }

    // 面试题02.01.
    public ListNode removeDuplicateNodes(ListNode head) {
        ListNode p1 = head;
        while(p1 != null){
            ListNode p2 = p1;
            while(p2.next != null){
                if (p2.next.val == p1.val) {
                    p2.next = p2.next.next;
                }else{
                    p2 = p2.next;
                }
            }
            p1 = p1.next;
        }
        return head;
    }
}
