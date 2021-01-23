package com.train.algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class TemplateEasyMode {
    public class ListNode {
        int val;
        ListNode next = null;
        ListNode(int val) {
            this.val = val;
        }
    }

    // NC-78
    public ListNode ReverseList(ListNode head) {
        if (head == null || head.next == null){
            return head;
        }
        ListNode pre = null;
        while (head != null){
            ListNode temp = head;
            head = head.next;
            temp.next = pre;
            pre = temp;
        }
        return pre;
    }

    // NC-4
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null){
            return false;
        }
        ListNode a = head,b = head.next;
        while (a!=b){
            if (b == null || b.next == null){
                return false;
            }
            a = a.next;
            b = b.next.next;
        }
        return true;
    }

    // NC-105
    public int upper_bound_ (int n, int v, int[] a) {
        int len = a.length;
        if (v > a[len - 1]){
            return len + 1;
        }
        if (v < a[0]){
            return 1;
        }
        int left = 0,right = a.length - 1;
        int mid = (right+left)/2;
        while (left < right){
            if (a[mid] >= v){
                right = mid;
            }else {
                left = mid + 1;
            }
            mid = (right-left)/2 + left;
        }
        return mid + 1;
    }

    // NC-68
    public int JumpFloor(int target) {
        int p = 0,q = 1,r = 1;
        for (int i = 0;i < target;i++){
            p = q;
            q = r;
            r = (p + q) % 1000000007;
        }
        return r;
    }

    // NC-103
    public String solve (String str) {
        if (str.length() > 1000){
            return null;
        }
        char[] c = str.toCharArray();
        StringBuilder res = new StringBuilder();
        for (int i = str.length() - 1;i >= 0;i--){
            res.append(c[i]);
        }
        return res.toString();
    }

    // NC-38
    public ArrayList<Integer> spiralOrder(int[][] matrix) {
        ArrayList<Integer> list = new ArrayList<>();
        if (matrix.length == 0 || matrix[0].length == 0){
            return list;
        }
        int top = 0,bottom = matrix.length - 1,left = 0,right = matrix[0].length - 1;
        while (true){
            for (int i = left;i <= right;i++){
                list.add(matrix[top][i]);
            }
            top++;
            if (left > right || top > bottom){
                break;
            }
            for (int i = right;i <= bottom;i++){
                list.add(matrix[i][right]);
            }
            right--;
            if (left > right || top > bottom){
                break;
            }
            for (int i = right;i >= left;i--){
                list.add(matrix[bottom][i]);
            }
            bottom--;
            if (left > right || top > bottom){
                break;
            }
            for (int i = bottom;i >= top;i--){
                list.add(matrix[i][left]);
            }
            left++;
            if (left > right || top > bottom){
                break;
            }
        }
        return list;
    }

    // NC-65
    public int Fibonacci(int n) {
        if (n == 0){
            return 0;
        }
        if (n == 1 || n == 2){
            return 1;
        }
        int p = 0,q = 0,r = 1;
        for (int i = 0;i < n - 1;i++){
            p = q;
            q = r;
            r = p + q;
        }
        return r;
    }

    // NC-107
    public int solve (int[] a) {
        int len = a.length;
        if (a == null || len == 0){
            return -1;
        }
        for (int i = len - 1;i >= 1;i--){
            if (a[i] >= a[i - 1]){
                return i;
            }
        }
        return 0;
    }

    // NC-110
    public int[] solve (int n, int m, int[] a) {
        m %= n;
        reverse(a,0,n-1);
        reverse(a,0,m-1);
        reverse(a,m,n-1);
        return a;
    }
    public static void reverse(int[] a,int start,int end){
        while (start < end){
            int temp = a[start];
            a[start++] = a[end];
            a[end--] = temp;
        }
    }

    // NC-33
    public ListNode mergeTwoLists (ListNode l1, ListNode l2) {
        ListNode c = null;
        if (l1 == null){
            return l2;
        }
        if (l2 == null){
            return l1;
        }
        if (l1.val < l2.val){
            c = l1;
            c.next = mergeTwoLists(l1.next,l2);
        }else {
            c = l2;
            c.next = mergeTwoLists(l1,l2.next);
        }
        return c;
    }

    // NC-76
    public class Solution {
        Stack<Integer> stack1 = new Stack<Integer>();
        Stack<Integer> stack2 = new Stack<Integer>();

        public void push(int node) {
            stack1.push(node);
        }

        public int pop() {
            if (stack2.size() <= 0){
                while (stack1.size()!=0){
                    stack2.push(stack1.pop());
                }
            }
            return stack2.pop();
        }
    }
}
