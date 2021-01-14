package com.train.algorithm.list;

import java.util.Arrays;

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

    //leetcode-977
    public int[] sortedSquares(int[] nums) {
        int left = 0,right = nums.length - 1,index = nums.length - 1;
        int[] res = new int[nums.length];
        while(index >= 0){
            if(Math.abs(nums[left]) > Math.abs(nums[right])){
                res[index] = nums[left] * nums[left];
                index--;
                left++;
            }else{
                res[index] = nums[right] * nums[right];
                index--;
                right--;
            }
        }
        return res;
    }

    // leetcode713
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        if (k <= 1) {
            return 0;
        }
        int left = 0,count = 0,start = 1;
        for (int right = 0;right < nums.length ; right++) {
            start *= nums[right];
            while(start >= k){
                start /= nums[left];
                left++;
            }
            count += right - left + 1;
        }
        return count;
    }


    // 面试题16.06
    public int smallestDifference(int[] a, int[] b) {
        Arrays.sort(a);
        Arrays.sort(b);
        int n = a.length,m = b.length;
        int i = 0,j = 0;
        long res = Integer.MAX_VALUE;
        while(i < n && j < m){
            if (res == 0) {
                return 0;
            }
            if (a[i] < b[j]) {
                while(i < n && a[i] < b[j]){
                    i++;
                }
                if (i < n) {
                    res = Math.min(res,Math.abs((long) a[i] - b[j]));
                }
                res = Math.min(res,Math.abs((long) a[i - 1] - b[j]));
            }else if (a[i] >= b[j]) {
                while(j < m && a[i] > b[j]){
                    j++;
                }
                if (j < m) {
                    res = Math.min(res,Math.abs((long) a[i] - b[j]));
                }
                res = Math.min(res,Math.abs((long) a[i] - b[j-1]));
            }
        }
        return (int)res;
    }
}
