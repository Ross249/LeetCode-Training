package com.train.algorithm;

import java.util.*;

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

    // NC-50
    public ListNode reverseKGroup (ListNode head, int k) {
        ListNode h = new ListNode(0);
        h.next = head;
        ListNode pre = h;
        while (head!=null){
            ListNode tail = pre;
            for (int i = 0;i < k;i++){
                tail = tail.next;
                if (tail == null){
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
        while (pre != tail){
            ListNode tmp = p.next;
            p.next = pre;
            pre = p;
            p = tmp;
        }
        return new ListNode[]{tail,head};
    }

    // NC-51
    class Status implements Comparable<Status>{
        int val;
        ListNode node;

        Status(int val,ListNode node){
            this.val = val;
            this.node = node;
        }

        @Override
        public int compareTo(Status o) {
            return this.val - o.val;
        }
    }
    PriorityQueue<Status> queue = new PriorityQueue<>();
    public ListNode mergeKLists(ArrayList<ListNode> lists) {
        for (ListNode n : lists){
            if (n != null){
                queue.offer(new Status(n.val,n));
            }
        }
        ListNode head = new ListNode(0);
        ListNode tail = head;
        while (!queue.isEmpty()){
            Status tmp = queue.poll();
            tail.next = tmp.node;
            tail = tail.next;
            if (tmp.node.next != null){
                queue.offer(new Status(tmp.node.next.val,tmp.node.next));
            }
        }
        return head.next;
    }

    // NC-121
    ArrayList<String> res = new ArrayList<>();
    TreeSet<String> paths = new TreeSet<>();
    StringBuilder path = new StringBuilder();
    boolean[] visited;
    public ArrayList<String> Permutation(String str) {
        if (str == null || str.equals("")){
            return res;
        }
        char[] strs = str.toCharArray();
        Arrays.sort(strs);
        visited = new boolean[strs.length];
        combination(strs,0);
        res.addAll(paths);
        return res;
    }

    public void combination(char[] strs,int len){
        if (len == strs.length){
            paths.add(path.toString());
            return;
        }
        for (int i = 0;i < strs.length;i++){
            if (!visited[i]){
                visited[i] = true;
                path.append(strs[i]);
                combination(strs,len + 1);
                visited[i] = false;
                path.deleteCharAt(path.length()-1);
            }
        }
    }

    // NC-36
    public int findMedianinTwoSortedAray (int[] arr1, int[] arr2) {
        int len = arr1.length;
        int l = 0,r = len - 1;
        while (l <= r){
            int mid = (l + r) >> 1;
            if (mid == len - 1){
                return Math.min(arr1[len-1],arr2[0]);
            }
            if (arr1[mid] == arr2[len - mid -2]){
                return arr1[mid];
            }
            if (arr1[mid] < arr2[len - mid -2]){
                l = mid + 1;
            }else {
                r = mid - 1;
            }
        }
        if (r < 0){
            return Math.min(arr1[0],arr2[len-1]);
        }
        int a = Math.max(arr1[l],arr2[len-l-2]);
        int b = Math.max(arr1[r],arr2[len-r-2]);
        return Math.min(a,b);
    }

    // NC-36
    public int minEditCost (String str1, String str2, int ic, int dc, int rc) {
        int m = str1.length();
        int n = str2.length();
        int[][] dp = new int[m+1][n+1];
        for (int i = 1;i <= m;i++){
            dp[i][0] = i * dc;
        }
        for (int i = 1;i <= n;i++){
            dp[0][i] = i*ic;
        }
        for (int i = 1;i <= m;i++){
            char c1 = str1.charAt(i-1);
            for (int j = 1;j <= n;j++){
                char c2 = str2.charAt(j-1);
                if (c1 == c2){
                    dp[i][j] = dp[i-1][j-1];
                }else {
                    int insert = dp[i][j-1] + ic;
                    int delete = dp[i-1][j] + dc;
                    int replace = dp[i-1][j-1] + rc;
                    dp[i][j] = Math.min(replace,Math.min(insert,delete));
                }
            }
        }
        return dp[m][n];
    }
}
