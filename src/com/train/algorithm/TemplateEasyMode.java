package com.train.algorithm;

import java.util.ArrayList;
import java.util.List;

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
}
