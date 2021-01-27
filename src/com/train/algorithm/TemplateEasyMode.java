package com.train.algorithm;

import com.train.algorithm.tree.TreeTravel;

import java.util.*;

public class TemplateEasyMode {
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

    // NC-61
    public int[] twoSum (int[] numbers, int target) {
        int len = numbers.length;
        Map<Integer,Integer> map = new HashMap<>();
        for (int i = 0;i < len;i++){
            if (map.containsKey(target - numbers[i])){
                return new int[]{map.get(target - numbers[i]),i+1};
            }
            map.put(numbers[i],i+1);
        }
        return new int[1];
    }

    // NC-22
    public void merge(int A[], int m, int B[], int n) {
        int len1 = m - 1,len2 = n - 1,len3 = n + m - 1;
        while (len1 >= 0 && len2 >= 0){
            A[len3--] = A[len1] < B[len2] ? B[len2--] : A[len1--];
        }
        System.arraycopy(B,0,A,0,len2 + 1);
    }

    // NC-52
    public boolean isValid (String s) {
        if (s.length() % 2 == 1){
            return false;
        }
        Map<Character,Character> pair = new HashMap<Character, Character>(){
            {
                put('}','{');
                put(']','[');
                put(')','(');
            }
        };
        Stack<Character> res = new Stack<>();
        for (int i = 0;i < s.length();i++){
            char c = s.charAt(i);
            if (pair.containsKey(c)){
                if (res.isEmpty() || !res.peek().equals(pair.get(c))){
                    return false;
                }
                res.pop();
            }else {
                res.push(c);
            }
        }
        return res.isEmpty();
    }

    // NC-19
    public int maxsumofSubarray (int[] arr) {
        int len = arr.length;
        int res = arr[0];
        for (int i = 1;i < len;i++){
            arr[i] += Math.max(arr[i - 1],0);
            res = Math.max(res,arr[i]);
        }
        return res;
    }

    // NC-66
    public ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
        ListNode a = pHead1,b = pHead2;
        while (pHead1 != pHead2){
            pHead1 = pHead1 != null ? pHead1.next : b;
            pHead2 = pHead2 != null ? pHead2.next : a;
        }
        return pHead1;
    }

    // NC-32
    public int sqrt (int x) {
        int left = 0,right = x,ans = -1;
        while (left <= right){
            int mid = (right - left) / 2 + left;
            if ((long) mid * mid <= x){
                ans = mid;
                left = mid + 1;
            }else {
                right = mid - 1;
            }
        }
        return ans;
    }

    // NC-70
    public ListNode sortInList (ListNode head) {
        if (head == null || head.next == null){
            return head;
        }
        ListNode fast = head.next,slow = head;
        while (fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode temp = slow.next;
        slow.next = null;
        ListNode left = sortInList(head);
        ListNode right = sortInList(temp);
        ListNode a = new ListNode(0);
        ListNode res = a;
        while (left != null && right != null){
            if (left.val < right.val){
                a.next = left;
                left = left.next;
            }else {
                a.next = right;
                right = right.next;
            }
            a = a.next;
        }
        a.next = left != null ? left : right;
        return res.next;
    }

    // NC-62
    public boolean IsBalanced_Solution(TreeNode root) {
        return recur(root) != -1;
    }

    private int recur(TreeNode root){
        if (root == null){
            return 0;
        }
        int left = recur(root.left);
        if (left == -1){
            return -1;
        }
        int right = recur(root.right);
        if (right == -1){
            return -1;
        }
        return Math.abs(right - left) < 2 ? Math.max(left,right) + 1 : -1;
    }

    // NC-72
    public void Mirror(TreeNode root) {
        Mirror1(root);
    }
    public TreeNode Mirror1(TreeNode root){
        if (root == null){
            return null;
        }
        TreeNode temp = root.left;
        root.left = Mirror1(root.right);
        root.right = Mirror1(temp);
        return root;
    }

    // NC-90
    Stack<Integer> stack1 = new Stack<>();
    Stack<Integer> minStack = new Stack<>();
    public int[] getMinStack (int[][] op) {
        List<Integer> list = new ArrayList<>();
        for (int[] opt : op){
            if (opt[0] == 1){
                push(opt[1]);
            }else if (opt[0] == 2){
                pop();
            }else {
                list.add(min());
            }
        }
        int[] res = new int[list.size()];
        for (int i = 0;i < list.size();i++){
            res[i] = list.get(i);
        }
        return res;
    }
    public void push(int x) {
        if (minStack.isEmpty()){
            minStack.push(x);
        }else if (x <= min()){
            minStack.push(x);
        }

        stack1.push(x);
    }

    public void pop() {
        if (stack1.isEmpty() || minStack.isEmpty()){
            return;
        }
        if (stack1.peek().equals(minStack.peek())){
            minStack.pop();
        }
        stack1.pop();
    }

    public int min() {
        return minStack.peek();
    }

    // NC-13
    public int maxDepth (TreeNode root) {
        if (root == null){
            return 0;
        }
        return Math.max(maxDepth(root.left),maxDepth(root.right)) + 1;
    }

    // NC-112
    public static String solve (int M, int N) {
        String res = "";
        if (M == 0){
            return res;
        }
        boolean count = true;
        if (M < 0){
            count = false;
        }
        String table = "0123456789ABCDEF";
        while (M != 0){
            if (M < 0){
                M = -M;
            }
            res = table.charAt(M % N) + res;
            M = M / N;
        }
        return count ? res : "-" + res;
    }

    // NC-75
    public void FindNumsAppearOnce(int [] array,int num1[] , int num2[]) {
        int temp = 0;
        int flag = 1;
        for (int a : array){
            temp ^= a;
        }
        while ((temp & flag) == 0){
            flag <<= 1;
        }
        for (int a : array){
            if ((a & flag) == flag){
                num1[0] ^= a;
            }else {
                num2[0] ^= a;
            }
        }
    }

    // NC-7
    public int maxProfit (int[] prices) {
        int len = prices.length;
        if (len < 2){
            return 0;
        }
        int[] dp = new int[2];
        dp[0] = 0;
        dp[1] = -prices[0];
        for (int i = 1;i < prices.length;i++){
            dp[0] = Math.max(dp[0],dp[1] + prices[i]);
            dp[1] = Math.max(dp[1],-prices[i]);
        }
        return dp[0];
    }

    // NC-96
    public boolean isPail (ListNode head) {
        ListNode a = head;
        List<Integer> temp = new ArrayList<>();
        while (a != null){
            temp.add(a.val);
            a = a.next;
        }
        int left = 0,right = temp.size() - 1;
        while (left <= right){
            if (temp.get(left).equals(temp.get(right))){
                left++;
                right--;
            }else {
                return false;
            }
        }
        return true;
    }

    // NC-73
    public int MoreThanHalfNum_Solution(int [] array) {
        int x = 0,votes = 0,count = 0;
        for (int a : array){
            if (votes == 0){
                x = a;
            }
            votes += a == x ? 1 : -1;
        }
        for (int a : array){
            if (a == x){
                count++;
            }
        }
        return count > array.length / 2 ? x : 0;
    }

    // NC-16
    public boolean isSymmetric (TreeNode root) {
        return root == null ? true : recur(root.left,root.right);
    }
    public boolean recur(TreeNode l,TreeNode r){
        if (l == null && r == null){
            return true;
        }
        if (l == null || r == null || l.val != r.val){
            return false;
        }
        return recur(l.left,r.right) && recur(l.right,r.left);
    }

    // NC-69
    public ListNode FindKthToTail(ListNode head,int k) {
        if (head == null){
            return head;
        }
        ListNode a = head,b = head,c = head;
        ListNode d = null;
        int count = 0;
        while (c != null){
            count++;
            c = c.next;
        }
        if (count < k){
            return d;
        }
        for (int i = 0;i < k;i++){
            a = a.next;
        }
        while (a != null){
            a = a.next;
            b = b.next;
        }
        return b;
    }

    // NC-34
    public int uniquePaths (int m, int n) {
        if (m == 0 && n == 0){
            return 0;
        }
        int[][] dp = new int[m][n];
        for (int i = 0;i < m;i++){
            dp[i][0] = 1;
        }
        for (int i = 0;i < n;i++){
            dp[0][i] = 1;
        }
        for (int i = 1;i < m;i++){
            for (int j = 1;j < n;j++){
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }

    // NC-57
    public static int reverse (int x) {
        int res = 0;
        while (x != 0){
            int a = x % 10;
            x /= 10;
            if (res > Integer.MAX_VALUE / 10 || (res == Integer.MAX_VALUE / 10 && a > 7)){
                return 0;
            }
            if (res < Integer.MIN_VALUE / 10 || (res == Integer.MIN_VALUE / 10 && a < -8)){
                return 0;
            }
            res = res * 10 + a;
        }
        return res;
    }
}
