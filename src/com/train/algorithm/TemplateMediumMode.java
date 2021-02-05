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

    // NC-14
    public ArrayList<ArrayList<Integer>> zigzagLevelOrder (TreeNode root) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        if (root == null){
            return res;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean order = true;
        while (!queue.isEmpty()){
            Deque<Integer> level = new LinkedList<>();
            int size = queue.size();
            for (int i = 0;i < size;i++){
                TreeNode node = queue.poll();
                if (order){
                    level.offer(node.val);
                }else {
                    level.offerFirst(node.val);
                }
                if (node.left != null){
                    queue.offer(node.left);
                }
                if (node.right != null){
                    queue.offer(node.right);
                }
            }
            res.add(new ArrayList<>(level));
            order = !order;
        }
        return res;
    }

    // NC-1
    public String solve (String s, String t) {
        int lens = s.length();
        int lent = t.length();
        String s1 = reverse(s);
        String t1 = reverse(t);
        int maxLen = lens > lent ? lens : lent;

        if (lens > lent){
            for (int i = lent;i < lens;i++){
                t1 += "0";
            }
        }else {
            for (int i = lens;i < lent;i++){
                s1 += "0";
            }
        }

        StringBuffer res = new StringBuffer();
        int carry = 0;
        for (int i = 0;i < maxLen;i++){
            int sum = Integer.parseInt(s1.charAt(i)+"") + Integer.parseInt(t1.charAt(i)+"") + carry;
            res.append(sum % 10);
            carry = sum / 10;
        }
        if (carry > 0){
            res.append(carry);
        }
        return res.reverse().toString();
    }

    public String reverse(String str){
        char[] chars = str.toCharArray();
        int l = 0,r = chars.length - 1;
        while (l < r){
            char c = chars[l];
            chars[l] = chars[r];
            chars[r] = c;
            l++;
            r--;
        }
        return new String(chars);
    }

    // NC-12
    public TreeNode reConstructBinaryTree(int [] pre,int [] in) {
        if (pre.length == 0|| in.length == 0){
            return null;
        }
        TreeNode root = new TreeNode(pre[0]);
        build(root,pre,0,pre.length,in,0,in.length);
        return root;
    }
    public void build(TreeNode root, int[] pre, int pleft, int pright, int[] in, int ileft, int iright) {
        int i;
        for (i = ileft; i < iright; i++) {
            if (in[i] == root.val) {//从中序序列寻找根节点的位置
                break;
            }
        }
        int t = i - ileft;
        if (t > 0) {//子树长度为0时不必生成子问题
            root.left = new TreeNode(pre[pleft + 1]);
            build(root.left, pre, pleft + 1, pleft + 1 + t, in, ileft, i);
        }

        if (pright - pleft - 1 - t > 0) {
            root.right = new TreeNode(pre[pleft + 1 + t]);
            build(root.right, pre, pleft + 1 + t, pright, in, i + 1, iright);
        }
    }

    // NC-91
    public int[] LIS (int[] arr) {
        List<Integer> res = new ArrayList<>();
        int[] maxLength = new int[arr.length];
        for (int i = 0;i < arr.length;i++){
            if (res.size() > 0){
                if (res.get(res.size()-1) < arr[i]){
                    res.add(arr[i]);
                    maxLength[i] = res.size();
                }else {
                    for (int j = res.size() - 1;j >= 0;j--){
                        if (res.get(j) < arr[i]){
                            res.set(j + 1,arr[i]);
                            maxLength[i] = j + 2;
                            break;
                        }
                        if (j == 0){
                            res.set(0,arr[i]);
                            maxLength[i] = 1;
                        }
                    }
                }
            }else {
                res.add(arr[i]);
                maxLength[i] = 1;
            }
        }
        int[] result = new int[res.size()];
        for (int i = arr.length - 1,j = res.size();j > 0;i--){
            if (maxLength[i] == j){
                result[--j] = arr[i];
            }
        }
        return result;
    }

    // NC-48
    public int search (int[] A, int target) {
        if (A[0] <= A[A.length - 1]){
            return binary(A,target,0,A.length-1);
        }
        int left = 0,right = A.length - 1;
        int mid;
        while (left <= right){
            mid = (left + right) / 2;
            if (A[mid] >= A[0]){
                left = mid + 1;
            }else {
                right = mid - 1;
            }
        }
        if (target >= A[0]){
            return binary(A,target,0,right);
        }else {
            return binary(A,target,left,A.length - 1);
        }
    }

    public int binary(int[] a,int target,int left,int right){
        int mid;
        while (left <= right){
            mid = (left + right) / 2;
            if (a[mid] > target){
                right = mid - 1;
            }else if (a[mid] == target){
                return mid;
            }else {
                left = mid + 1;
            }
        }
        return -1;
    }

    // NC-54
    public ArrayList<ArrayList<Integer>> threeSum(int[] num) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        Arrays.sort(num);
        for (int i = 0;i < num.length - 2;i++){
            int start = i + 1;
            int end = num.length - 1;
            while (start < end){
                int sum = num[i] + num[start] + num[end];
                if (sum < 0){
                    start++;
                }else if (sum > 0){
                    end--;
                }else {
                    ArrayList<Integer> temp = new ArrayList<>();
                    temp.add(num[i]);
                    temp.add(num[start]);
                    temp.add(num[end]);
                    if (!res.contains(temp)){
                        res.add(temp);
                    }
                    start++;
                    end--;
                }
            }
        }
        return res;
    }

    // NC-17
    public int getLongestPalindrome(String A, int n) {
        if (A == null || n == 0){
            return 0;
        }
        int maxLen = 1;
        boolean[][] dp = new boolean[n][n];

        for (int i = 0;i < n;i++){
            dp[i][i] = true;
        }
        for (int j = 1;j < n;j++){
            for (int i = 0;i < j;i++){
                if (A.charAt(i) != A.charAt(j)){
                    dp[i][j] = false;
                }else {
                    if (j - i < 3){
                        dp[i][j] = true;
                    }else {
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                }
                if (dp[i][j] && j - i + 1 >maxLen){
                    maxLen = j - i + 1;
                }
            }
        }
        return maxLen;
    }

    // NC-128
    public long maxWater (int[] arr) {
        if (arr == null || arr.length < 3){
            return 0;
        }
        long leftMax = arr[0],rightMax = arr[arr.length - 1];
        int l = 1,r = arr.length - 2;
        long res = 0;
        while (l <= r){
            if (leftMax <= rightMax){
                res += Math.max(leftMax-arr[l],0);
                leftMax = Math.max(leftMax,arr[l++]);
            }else {
                res += Math.max(rightMax-arr[r],0);
                rightMax = Math.max(rightMax,arr[r--]);
            }
        }
        return res;
    }

    // NC-136
    public int[] solve (int[] xianxu, int[] zhongxu) {
        TreeNode root = reBuild(xianxu, zhongxu);
        return bfs(root);
    }

    public TreeNode reBuild(int[] preOrder,int[] inOrder){
        if (preOrder == null || preOrder.length == 0){
            return null;
        }
        int val = preOrder[0],pos = 0,len = preOrder.length;
        TreeNode root = new TreeNode(val);

        for (;pos < len;pos++){
            if (inOrder[pos] == val){
                break;
            }
        }

        root.left = reBuild(Arrays.copyOfRange(preOrder , 1 ,pos + 1),Arrays.copyOfRange(inOrder, 0 ,pos));
        root.right = reBuild(Arrays.copyOfRange(preOrder,pos+1,len),Arrays.copyOfRange(inOrder,pos+1,len));
        return root;
    }

    public int[] bfs(TreeNode root){
        if (root == null){
            return null;
        }

        List<Integer> list = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        for (TreeNode temp = root;!queue.isEmpty();){
            for (int size = queue.size();size > 0;size-- ){
                temp = queue.poll();
                if (temp.left != null){
                    queue.offer(temp.left);
                }
                if (temp.right != null){
                    queue.offer(temp.right);
                }
            }
            list.add(temp.val);
        }
        return list.stream().mapToInt(Integer::valueOf).toArray();
    }

    // NC-137
    public int solve (String s) {
        Stack<Integer> stack = new Stack<>();
        int sum = 0,number = 0;
        char sign = '+';
        char[] chars = s.toCharArray();
        for (int i = 0;i < chars.length;i++){
            char c = chars[i];
            if (c == '('){
                int j = i + 1;
                int count = 1;
                while (count > 0){
                    if (chars[j] == '('){
                        count++;
                    }
                    if (chars[j] == ')') {
                        count--;
                    }
                    j++;
                }
                number = solve(s.substring(i+1,j-1));
                i = j - 1;
            }
            if (Character.isDigit(c)) {
                number = number * 10 + c - '0';
            }
            if (!Character.isDigit(c) || i == chars.length - 1){
                if (sign == '+'){
                    stack.push(number);
                }else if (sign == '-'){
                    stack.push(-1 * number);
                }else if (sign == '*'){
                    stack.push(stack.pop() * number);
                }else if (sign == '/'){
                    stack.push(stack.pop() / number);
                }
                number = 0;
                sign = c;
            }
        }
        while (!stack.isEmpty()){
            sum += stack.pop();
        }
        return sum;
    }
}
