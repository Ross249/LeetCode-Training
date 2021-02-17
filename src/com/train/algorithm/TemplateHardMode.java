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

    // NC-6
    int res1 = Integer.MIN_VALUE;
    public int maxPathSum (TreeNode root) {
        getMax(root);
        return res1;
    }

    public int getMax(TreeNode root){
        if (root == null){
            return 0;
        }
        int leftMax = Math.max(0,getMax(root.left));
        int rightMax = Math.max(0,getMax(root.right));
        res1 = Math.max(res1,Math.max(root.val + Math.max(leftMax,rightMax), root.val+ leftMax + rightMax));
        return Math.max(leftMax , rightMax) + root.val;
    }

    // NC-100
    public int atoi (String str) {
        if (str.isEmpty()){
            return 0;
        }
        str = str.replaceAll(" ","");
        char first = '0';
        char last = '9';
        int count = 0;
        int negative = 0;
        int positive = 0;
        int[] res = new int[100];
        double val = 0;
        for (int i = 0;i < str.length();i++){
            char tmp = str.charAt(i);
            if (tmp >= first && tmp <= last){
                res[count] = tmp - '0';
                count++;
            }else if (count == 0 && (tmp == '+' || tmp == '-')){
                if (tmp == '+'){
                    positive = 1;
                }else if (tmp == '-'){
                    negative = 1;
                }
            }else {
                break;
            }
        }

        for (int i = 0;i < count;i++){
            double tmp = 1;
            for (int j = count -1 -i;j>0;j--){
                tmp = tmp * 10;
            }
            val = res[i] * tmp + val;
        }
        if (negative == 1){
            val = -val;
        }
        if (val >= Integer.MAX_VALUE){
            return Integer.MAX_VALUE;
        }
        if (val <= Integer.MIN_VALUE){
            return Integer.MIN_VALUE;
        }
        return (int) val;
    }

    // NC-49
    public int longestValidParentheses (String s) {
//        int len = s.length();
//        int[] dp = new int[len];
//        int max = 0;
//        for (int i = 1;i < len;i++){
//            if (s.charAt(i) == ')'){
//                int pre = i - 1 - dp[i-1];
//                if (pre >= 0 && s.charAt(pre) == '('){
//                    dp[i] = dp[i-1] + 1;
//                    if (pre - 1 >= 0){
//                        dp[i] += dp[pre-1];
//                    }
//                }
//            }
//            max = Math.max(dp[i],max);
//        }
//        return max;
        int max = 0;
        Deque<Integer> stack = new LinkedList<>();
        stack.push(-1);
        for (int i = 0;i < s.length();i++){
            if (s.charAt(i) == '('){
                stack.push(i);
            }else {
                stack.pop();
                if (stack.isEmpty()){
                    stack.push(i);
                }else {
                    max = Math.max(max,i - stack.peek());
                }
            }
        }
        return max;
    }

    // NC-82
    ArrayList<Integer> result = new ArrayList<>();
    PriorityQueue<Integer> queue1 = new PriorityQueue<>((o1, o2) -> o2-o1);
    public ArrayList<Integer> maxInWindows(int [] num, int size){
        if (num == null || num.length <= 0 || size <= 0 || size > num.length){
            return result;
        }
        int count = 0;
        for (;count < size;count++){
            queue1.offer(num[count]);
        }
        while (count < num.length){
            result.add(queue1.peek());
            queue1.remove(num[count - size]);
            queue1.add(num[count]);
            count++;
        }
        result.add(queue1.peek());
        return result;
    }

    // NC-123
    int index = -1;
    String Serialize(TreeNode root) {
        if (root == null){
            return "#";
        }else {
            return root.val + "," + Serialize(root.left) + "," + Serialize(root.right);
        }
    }
    TreeNode Deserialize(String str) {
        String[] s = str.split(",");
        index++;
        int len = s.length;
        if (index > len){
            return null;
        }
        TreeNode treeNode = null;
        if (!s[index].equals("#")){
            treeNode = new TreeNode(Integer.parseInt(s[index]));
            treeNode.left = Deserialize(str);
            treeNode.right = Deserialize(str);
        }
        return treeNode;
    }
}
