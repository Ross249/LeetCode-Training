package com.train.algorithm;

import java.util.*;

public class TemplateMediumMode {
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

    // NC-140
    public static int[] MySort (int[] arr) {
        quickSort(arr,0,arr.length-1);
        return arr;
    }

    public static void quickSort(int[] arr,int low,int high){
        int pivot;
        if (low < high){
            pivot = partition(arr,low,high);
            quickSort(arr, low, pivot - 1);
            quickSort(arr, pivot + 1, high);
        }
    }

    public static int partition(int[] arr,int low,int high){
        int temp = arr[low];
        while (low < high){
            while (low < high && arr[high] >= temp){
                high--;
            }
            arr[low] = arr[high];
            while (low < high && arr[low] <= temp){
                low++;
            }
            arr[high] = arr[low];
        }
        arr[low] = temp;
        return low;
    }

    // NC-93
    public int[] LRU (int[][] operators, int k) {
        LinkedHashMap<Integer,Integer> lruM = new LinkedHashMap<>();
        ArrayList<Integer> temp = new ArrayList<>();
        for (int[] op:operators){
            switch (op[0]){
                case 1:
                    if (lruM.size() >= k){
                        Iterator d = lruM.keySet().iterator();
                        lruM.remove(d.next());
                        lruM.put(op[1],op[2]);
                    }else {
                        lruM.put(op[1],op[2]);
                    }
                    break;
                case 2:
                    if (lruM.containsKey(op[1])){
                        int t = lruM.get(op[1]);
                        lruM.remove(op[1]);
                        lruM.put(op[1],t);
                        temp.add(t);
                    }else {
                        temp.add(-1);
                    }
                    break;
                default:;
            }
        }
        int[] res = new int[temp.size()];
        int i = 0;
        for (int r : temp){
            res[i] = r;
            i++;
        }
        return res;
    }

    // NC-45
    public int[][] threeOrders (TreeNode root) {
        List<Integer> pre = new ArrayList<>();
        List<Integer> in = new ArrayList<>();
        List<Integer> post = new ArrayList<>();
        pre(pre,root);
        in(in,root);
        post(post,root);
        int[][] res = new int[3][pre.size()];
        for (int i = 0;i < pre.size();i++){
            res[0][i] = pre.get(i);
        }
        for (int i = 0;i < pre.size();i++){
            res[1][i] = in.get(i);
        }
        for (int i = 0;i < pre.size();i++){
            res[2][i] = post.get(i);
        }
        return res;
    }

    public void pre(List<Integer> pre,TreeNode root){
        if (root != null){
            pre.add(root.val);
            pre(pre,root.left);
            pre(pre,root.right);
        }
    }

    public void in(List<Integer> in,TreeNode root){
        if (root != null){
            in(in,root.left);
            in.add(root.val);
            in(in,root.right);
        }
    }

    public void post(List<Integer> post,TreeNode root){
        if (root != null){
            post(post,root.left);
            post(post,root.right);
            post.add(root.val);
        }
    }

    // NC-15
    public ArrayList<ArrayList<Integer>> levelOrder (TreeNode root) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        if (root == null){
            return res;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            ArrayList<Integer> level = new ArrayList<>();
            int size = queue.size();
            for (int i = 0;i < size;i++){
                TreeNode node = queue.poll();
                level.add(node.val);
                TreeNode left = node.left,right = node.right;
                if (left != null){
                    queue.offer(left);
                }
                if (right != null){
                    queue.offer(right);
                }
            }
            res.add(level);
        }
        return res;
    }

    // NC-88
    public int findKth(int[] a, int n, int K) {
        int left = 0,right = n - 1;
        int target = n - K;
        while (true){
            int index = partition1(a,left,right);
            if (index == target){
                return a[index];
            }else if (index < target){
                left = index + 1;
            }else {
                right = index - 1;
            }
        }
    }

    public int partition1(int[] a, int left, int right) {
        int pivot = a[left];
        int j = left;
        for (int i = left + 1;i <= right;i++){
            if (a[i] < pivot){
                j++;
                swap(a,j,i);
            }
        }
        swap(a,j,left);
        return j;
    }

    private void swap(int[] a, int index1, int index2) {
        int temp = a[index1];
        a[index1] = a[index2];
        a[index2] = temp;
    }

    // NC-41
    public int maxLength (int[] arr) {
        Map<Integer,Integer> dict = new HashMap<>();
        int i = -1,res = 0;
        for (int j = 0;j < arr.length;j++){
            if (dict.containsKey(arr[j])){
                i = Math.max(i,dict.get(arr[j]));
            }
            dict.put(arr[j],j);
            res = Math.max(res,j - i);
        }
        return res;
    }

    // NC-3
    public ListNode detectCycle(ListNode head) {
        if (head == null){
            return head;
        }
        ListNode a = head;
        ListNode b = head;
        ListNode flag = null;
        while (b != null && b.next != null){
            a = a.next;
            b = b.next.next;
            if (a == b){
                flag = a;
                break;
            }
        }
        if (flag == null){
            return null;
        }
        b = head;
        while (a != b){
            b = b.next;
            a = a.next;
        }
        return a;
    }

    // NC-53
    public ListNode removeNthFromEnd (ListNode head, int n) {
        if (head == null){
            return head;
        }
        ListNode a = head;
        int len = 0;
        while (a != null){
            a = a.next;
            len++;
        }
        int index = len - n;
        if (index == 0){
            return head.next;
        }
        ListNode b = head;
        int count = 0;
        while (b != null){
            count++;
            if (count == index){
                b.next = b.next.next;
                break;
            }
            b = b.next;
        }
        return head;
    }

    // NC-127
    public String LCS (String str1, String str2) {
        if (str1 == null || str2 == null || str1.length() == 0 || str2.length() == 0){
            return "-1";
        }
        int len1 = str1.length(),len2 = str2.length();
        int[][] dp = new int[len1][len2];
        int len = 0,begin = 0;
        for (int i = 0;i < len1;i++){
            for (int j = 0;j < len2;j++){
                if (str1.charAt(i) == str2.charAt(j)){
                    if(i == 0||j == 0){
                        dp[i][j] = 1;
                    }else {
                        dp[i][j] = dp[i-1][j-1] + 1;
                    }
                }else {
                    dp[i][j] = 0;
                }
                if (dp[i][j] > len){
                    len = dp[i][j];
                    begin = i;
                }
            }
        }
        if (len == 0){
            return "-1";
        }else {
            return str1.substring(begin - len + 1,begin + 1);
        }
    }

    // NC-40
    public ListNode addInList (ListNode head1, ListNode head2) {
        head1 = reverse(head1);
        head2 = reverse(head2);
        ListNode head = new ListNode(-1);
        ListNode cur = head;
        int carry = 0;
        while (head1 != null || head2 != null){
            int var = carry;
            if (head1 != null){
                var += head1.val;
                head1 = head1.next;
            }
            if (head2 != null){
                var += head2.val;
                head2 = head2.next;
            }
            cur.next = new ListNode(var % 10);
            carry = var / 10;
            cur = cur.next;
        }
        if (carry > 0){
            cur.next = new ListNode(carry);
        }
        return reverse(head.next);
    }

    public ListNode reverse(ListNode head){
        ListNode cur = head;
        ListNode pre = null;
        while (cur != null){
            ListNode temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;
        }
        return pre;
    }

    // NC-102
    public int lowestCommonAncestor (TreeNode root, int o1, int o2) {
        return dfs(root, o1, o2).val;
    }

    public TreeNode dfs(TreeNode root ,int o1,int o2){
        if (root == null || root.val == o1 || root.val == o2){
            return root;
        }
        TreeNode left = dfs(root.left,o1,o2);
        TreeNode right = dfs(root.right, o1, o2);
        if (left == null){
            return right;
        }
        if (right == null){
            return left;
        }
        return root;
    }
}
