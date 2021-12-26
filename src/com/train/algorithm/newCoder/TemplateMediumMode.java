package com.train.algorithm.newCoder;

import java.util.*;

public class TemplateMediumMode {
    public class Interval {
        int start;
        int end;
        Interval() { start = 0; end = 0; }
        Interval(int s, int e) { start = s; end = e; }
    }
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

    // NC-109
    private static final int[][] DIRECTIONS = {{-1,0},{0,-1},{1,0},{0,1}};
    private boolean[][] visited;
    private int rows,cols;
    private char[][] grid;
    public int solve (char[][] grid) {
        rows = grid.length;;
        if(rows == 0){
            return 0;
        }
        cols = grid[0].length;
        this.grid = grid;
        visited = new boolean[rows][cols];
        int count = 0;
        for (int i = 0;i < rows;i++){
            for (int j = 0;j < cols;j++){
                if(!visited[i][j] && grid[i][j] == '1'){
                    dfs(i,j);
                    count++;
                }
            }
        }
        return count;
    }

    public void dfs(int i,int j){
        visited[i][j] = true;
        for (int k = 0;k < 4;k++){
            int newX = i + DIRECTIONS[k][0];
            int newY = j + DIRECTIONS[k][1];
            if (inArea(newX,newY) && grid[newX][newY] == '1' && !visited[newX][newY]){
                dfs(newX,newY);
            }
        }
    }

    public boolean inArea(int x,int y){
        return x >= 0 && x < rows && y >=0 && y < cols;
    }

    // NC-141
    public boolean judge (String str) {
        if (str == null || str.length() == 0){
            return false;
        }
        for (int i = 0,j = str.length() - 1;i < j;i++,j--){
            if (str.charAt(i) != str.charAt(j)){
                return false;
            }
        }
        return true;
    }

    // NC-97
    public String[][] topKstrings (String[] strings, int k) {
        if (k == 0){
            return new String[][]{};
        }
        String[][] res = new String[k][2];
        TreeMap<String,Integer> map = new TreeMap<>();
        for (int i = 0;i < strings.length;i++){
            String s = strings[i];
            if (!map.containsKey(s)){
                map.put(s,1);
            }else {
                map.put(s,map.get(s) + 1);
            }
        }

        ArrayList<Map.Entry<String,Integer>> list = new ArrayList<>(map.entrySet());
        Collections.sort(
                list,(o1, o2) -> (o1.getValue().compareTo(o2.getValue())) == 0 ? o1.getKey().compareTo(o2.getKey()) : o2.getValue().compareTo(o1.getValue())
        );
        for(int i = 0;i < k;i++){
            res[i][0] = list.get(i).getKey();
            res[i][1] = String.valueOf(list.get(i).getValue());
        }
        return res;

    }

    // NC-59
    public int minPathSum (int[][] matrix) {
        for (int i = 0;i < matrix.length;i++){
            for (int j = 0;j < matrix[0].length;j++){
                if (i == 0 && j == 0){
                    continue;
                }else if (i == 0){
                    matrix[i][j] = matrix[i][j - 1] + matrix[i][j];
                }else if (j == 0){
                    matrix[i][j] = matrix[i - 1][j] + matrix[i][j];
                }else {
                    matrix[i][j] = Math.min(matrix[i - 1][j],matrix[i][j - 1]) + matrix[i][j];
                }
            }
        }
        return matrix[matrix.length - 1][matrix[0].length - 1];
    }

    // NC-8
    ArrayList<ArrayList<Integer>> res = new ArrayList<>();
    LinkedList<Integer> path = new LinkedList<>();
    public ArrayList<ArrayList<Integer>> pathSum (TreeNode root, int sum) {
        recur(root,sum);
        return res;
    }

    public void recur(TreeNode root,int tar){
        if (root == null){
            return;
        }
        path.add(root.val);
        tar -= root.val;
        if (tar == 0 && root.left == null && root.right == null){
            res.add(new ArrayList<>(path));
        }
        recur(root.left, tar);
        recur(root.right, tar);
        path.removeLast();
    }

    // NC-30
    public int minNumberdisappered (int[] arr) {
        if (arr == null || arr.length == 0){
            return 1;
        }
        Arrays.sort(arr);
        int first = 1;
        for (int i = 0;i < arr.length;i++){
            if (arr[i] < 0){
                continue;
            }else if (arr[i] > first){
                return first;
            }else {
                first++;
            }
        }
        return first;
    }

    // NC-21
    public ListNode reverseBetween (ListNode head, int m, int n) {
        ListNode nHead = new ListNode(-1);
        nHead.next = head;
        ListNode p1 = nHead;

        for (int i = 1;i < m && p1 != null;++i){
            p1 = p1.next;
        }

        ListNode cur = p1.next;
        p1.next = null;
        ListNode tail = null;
        for (int i = 1;i <= n - m + 1 && cur != null;i++){
            ListNode tmp = cur;
            cur = cur.next;
            tmp.next = p1.next;
            p1.next = tmp;
            if (tail == null){
                tail = tmp;
            }
        }
        tail.next = cur;
        return nHead.next;
    }

    // NC-5
    public int sumNumbers (TreeNode root) {
        return dfs(root,0);
    }

    public int dfs(TreeNode root,int prev){
        if (root == null){
            return 0;
        }
        int sum = 10 * prev + root.val;
        if (root.left == null && root.right == null){
            return sum;
        }else {
            return dfs(root.left,sum) + dfs(root.right,sum);
        }
    }

    // NC-86
    public int[] findElement(int[][] mat, int n, int m, int x) {
        int lenN = n - 1,lenM = 0;
        while (lenN >= 0 && lenM <= m-1){
            if (mat[lenN][lenM] == x){
                return new int[]{lenN,lenM};
            }else if (mat[lenN][lenM] > x){
                lenN--;
            }else {
                lenM++;
            }
        }
        return new int[]{};
    }

    // NC-24
    public ListNode deleteDuplicates (ListNode head) {
        ListNode nHead = new ListNode(-1);
        nHead.next = head;
        ListNode a = head;
        ListNode b = nHead;
        while (a != null && a.next != null){
            if (a.val == a.next.val){
                ListNode tmp = a.next;
                while (tmp != null && tmp.val == a.val){
                    tmp = tmp.next;
                }
                b.next = tmp;
                a = tmp;
            }else {
                a = a.next;
                b = b.next;
            }
        }
        return nHead.next;
    }

    // NC-133
    public ListNode oddEvenList (ListNode head) {
        if (head == null || head.next == null){
            return head;
        }
        ListNode odd = head,oddHead = head,even = head.next,evenHead = head.next;
        while (even != null && even.next != null){
            odd.next = even.next;
            odd = odd.next;
            even.next = odd.next;
            even = even.next;
        }
        odd.next = evenHead;
        return oddHead;
    }

    // NC-20
    ArrayList<String> res1 = new ArrayList<>();
    public ArrayList<String> restoreIpAddresses (String s) {
        if (s.length() == 0){
            return res1;
        }
        backTrack(s,0,3);
        return res1;
    }

    public void backTrack(String s,int start,int count){
        if (count == 0){
            String[] splits = s.split("\\.");
            if (splits.length < 4){
                return;
            }
            for (String str : splits){
                if (str.length() > 1 && str.charAt(0) == '0'){
                    return;
                }
                if (Integer.valueOf(str) > 255){
                    return;
                }
            }
            res1.add(s);
            return;
        }

        if (start >= s.length()){
            return;
        }
        int len = s.length();
        backTrack(s.substring(0,start+1)+'.'+s.substring(start+1,len),start+2,count-1);
        if (start < len - 2){
            backTrack(s.substring(0,start + 2)+'.'+s.substring(start+2,len),start+3,count-1);
        }
        if (start < len - 3){
            backTrack(s.substring(0,start + 3)+'.'+s.substring(start+3,len),start+4,count-1);
        }
    }

    // NC-26
    public ArrayList<String> generateParenthesis (int n) {
        ArrayList<String> res = new ArrayList<>();
        if (n == 0){
            return res;
        }
        dfs("",n,n,res);
        return res;
    }

    public void dfs(String cur,int left,int right,ArrayList<String> res){
        if (left == 0 && right == 0){
            res.add(cur);
            return;
        }
        if (left > right){
            return;
        }
        if (left > 0){
            dfs(cur+"(",left-1,right,res);
        }
        if (right > 0){
            dfs(cur + ")",left,right - 1,res);
        }
    }

    // NC-92
    public String LCS1 (String s1, String s2) {
        int len1 = s1.length();
        int len2 = s2.length();
        if (len1 == 0 || len2 == 0){
            return "-1";
        }
        int[][] dp = new int[len1+1][len2+1];
        for (int i = 1;i <= len1;i++){
            for (int j = 1;j <= len2;j++){
                if (s1.charAt(i-1) == s2.charAt(j-1)){
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }else {
                    dp[i][j] = Math.max(dp[i][j-1],dp[i-1][j]);
                }
            }
        }

        int i = len1,j = len2;
        StringBuilder sb = new StringBuilder();
        while (i > 0 && j > 0){
            if (s1.charAt(i-1) == s2.charAt(j-1)){
                sb.append(s1.charAt(i-1));
                i--;
                j--;
            }else {
                if (dp[i][j-1] >dp[i-1][j]){
                    j--;
                }else if (dp[i][j-1] < dp[i-1][j]){
                    i--;
                }else if (dp[i][j-1] == dp[i-1][j]){
                    j--;
                }
            }
        }
        if (sb.length() == 0){
            return "-1";
        }else {
            return sb.reverse().toString();
        }
    }

    // NC-87
    public int solve (int n, int k) {
        if (n < 1 || k < 1){
            return 0;
        }
        int time = (int) (Math.log(n) / Math.log(2)) + 1;
        if (k >= time){
            return time;
        }

        int[] dp = new int[k];
        int res = 0;
        while (true){
            res++;
            int pre = 0;
            for (int i = 0;i < k;i++){
                int temp = dp[i];
                dp[i] = dp[i] + pre + 1;
                pre = temp;
                if (dp[i] >= n){
                    return res;
                }
            }
        }
    }

    // NC-37
    public ArrayList<Interval> merge(ArrayList<Interval> intervals) {
        ArrayList<Interval> res = new ArrayList<>();
        Collections.sort(intervals,(a,b)->a.start - b.start);
        int len = intervals.size();
        int i = 0;
        while (i < len){
            int left = intervals.get(i).start;
            int right = intervals.get(i).end;
            while (i < len - 1 && intervals.get(i+1).start <= right){
                right = Math.max(right,intervals.get(i+1).end);
                i++;
            }
            res.add(new Interval(left,right));
            i++;
        }
        return res;
    }

    // NC-18
    public int[][] rotateMatrix(int[][] mat, int n) {
        for (int i = 0;i < n;i++){
            for (int j = i+1;j < n;j++){
                int tmp = mat[i][j];
                mat[i][j] = mat[j][i];
                mat[j][i] = tmp;
            }
        }

        for (int i = 0;i < n;i++){
            for (int j = 0;j < n / 2;j++){
                int tmp = mat[i][j];
                mat[i][j] = mat[i][n-j-1];
                mat[i][n-j-1] = tmp;
            }
        }
        return mat;
    }

    // NC-60
    public boolean[] judgeIt (TreeNode root) {
        boolean[] res = new boolean[2];
        res[0] = isBST(root,Long.MIN_VALUE,Long.MAX_VALUE);
        res[1] = isCompleteTree(root);
        return res;
    }

    public boolean isBST(TreeNode root,long lower,long upper){
        if (root == null){
            return true;
        }
        if (root.val <= lower || root.val >= upper){
            return false;
        }
        return isBST(root.left,lower,root.val) && isBST(root.right,root.val,upper);
    }

    public boolean isCompleteTree(TreeNode root){
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (queue.peek() != null){
            TreeNode node = queue.poll();
            queue.offer(node.left);
            queue.offer(node.right);
        }
        while (!queue.isEmpty() && queue.peek() == null){
            queue.poll();
        }
        return queue.isEmpty();
    }

    // NC-2
    public void reorderList(ListNode head) {
        if (head == null || head.next == null || head.next.next == null){
            return;
        }
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode mid = slow.next;
        slow.next = null;
        ListNode newHead = reverse1(mid);

        while (newHead != null){
            ListNode tmp = newHead.next;
            newHead.next = head.next;
            head.next = newHead;
            head = newHead.next;
            newHead = tmp;
        }
    }

    public ListNode reverse1(ListNode head){
        if (head == null){
            return head;
        }
        ListNode tail = head;
        head = head.next;
        tail.next = null;
        while (head != null){
            ListNode tmp = head.next;
            head.next = tail;
            tail = head;
            head = tmp;
        }
        return tail;
    }

    // NC-42
    public ArrayList<ArrayList<Integer>> permuteUnique(int[] num) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        if (num == null || num.length == 0){
            return res;
        }
        Arrays.sort(num);
        boolean[] visit = new boolean[num.length];
        ArrayList<Integer> list = new ArrayList<>();
        dfs(res,list,visit,num);
        return res;
    }

    public void dfs(ArrayList<ArrayList<Integer>> res,ArrayList<Integer> list,boolean[] visit,int[] num){
        if (list.size() == num.length){
            res.add(new ArrayList<>(list));
        }
        for (int i = 0;i < num.length;i++){
            if (visit[i] == true || (i != 0 && num[i] == num[i-1]) && visit[i-1] == false){
                continue;
            }
            visit[i] = true;
            list.add(num[i]);
            dfs(res, list, visit, num);
            list.remove(list.size() - 1);
            visit[i] = false;
        }
    }

    // NC-111
    public String solve (int[] nums) {
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0;i < nums.length;i++){
            list.add(String.valueOf(nums[i]));
        }
        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return (o2 + o1).compareTo(o1 + o2);
            }
        });

        if (list.get(0).equals("0")){
            return "0";
        }
        StringBuilder res = new StringBuilder();
        for (int i = 0;i < list.size();i++){
            res.append(list.get(i));
        }
        return res.toString();
    }

    // NC-27
    ArrayList<ArrayList<Integer>> res2 = new ArrayList<>();
    public ArrayList<ArrayList<Integer>> subsets(int[] S) {
        LinkedList<Integer> track = new LinkedList<>();
        dfs(S,track,0);
        return res2;
    }

    public void dfs(int[] S,LinkedList<Integer> track,int start){
        res2.add(new ArrayList<>(track));
        for (int i = start;i < S.length;i++){
            track.add(S[i]);
            dfs(S, track, i+1);
            track.removeLast();
        }
    }

    // NC-46
    public ArrayList<ArrayList<Integer>> combinationSum2(int[] num, int target) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        ArrayList<Integer> tmp = new ArrayList<>();
        if (num == null || num.length == 0 || target < 0){
            return res;
        }
        Arrays.sort(num);
        dfs(res,tmp,target,num,0);
        return res;
    }

    public void dfs(ArrayList<ArrayList<Integer>> res,ArrayList<Integer> tmp,int target,int[] num,int start){
        if (target == 0){
            res.add(new ArrayList<>(tmp));
            return;
        }

        if (start >= num.length){
            return;
        }
        for (int i = start;i < num.length;i++){
            if (i > start && num[i] == num[i - 1]){
                continue;
            }
            if (num[i] <= target){
                tmp.add(num[i]);
                dfs(res,tmp,target-num[i],num,i+1);
                tmp.remove(tmp.size() - 1);
            }
        }
        return;
    }

    // NC-99
    List<List<Integer>> graph = new ArrayList<>();
    Map<Integer,Map<Integer,Integer>> table = new HashMap<>();
    int maxEdge = 0;
    int maxIndex = -1;
    public int solve (int n, Interval[] Tree_edge, int[] Edge_value) {
        for (int i = 0;i < n;i++){
            graph.add(new ArrayList<Integer>());
        }
        for (int i = 0;i < Tree_edge.length;i++){
            Interval edge = Tree_edge[i];
            graph.get(edge.start).add(edge.end);
            graph.get(edge.end).add(edge.start);
            if (!table.containsKey(edge.start)){
                table.put(edge.start,new HashMap<Integer,Integer>());
            }
            table.get(edge.start).put(edge.end,Edge_value[i]);
            if (!table.containsKey(edge.end)){
                table.put(edge.end,new HashMap<Integer, Integer>());
            }
            table.get(edge.end).put(edge.start,Edge_value[i]);
        }

        dfs(0,0,-1);
        dfs(0,maxIndex,-1);

        return maxEdge;
    }

    public void dfs(int count,int node,int parent){
        List<Integer> cur = graph.get(node);
        for (int i = 0;i < cur.size();i++){
            if(cur.get(i) == parent){
                continue;
            }
            int value = table.get(node).get(cur.get(i));
            dfs(count+value,cur.get(i),node);
        }
        if (count > maxEdge){
            maxEdge = count;
            maxIndex = node;
        }
    }

    // NC-10
    public String solve1 (String s, String t) {
        int len1 = s.length();
        int len2 = t.length();

        int[] num1 = new int[len1];
        int[] num2 = new int[len2];

        for (int i = 0;i < len1;i++){
            num1[i] = s.charAt(i) - '0';
        }
        for (int i = 0;i < len2;i++){
            num2[i] = t.charAt(i) - '0';
        }

        int[] res = new int[len1 + len2];
        for (int i = 0;i < len1;i++){
            for (int j = 0;j < len2;j++){
                res[i+j] += num1[i] * num2[j];
            }
        }

        for (int k = res.length - 1;k > 0;k--){
            res[k-1] += res[k] / 10;
            res[k] = res[k] % 10;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0;i < res.length - 1;i++){
            sb.append(res[i]);
        }
        return sb.toString();
    }

    // NC-113
    public String solve1 (String IP) {
        if (IP.chars().filter(ch -> ch == '.').count() == 3){
            return validateIPv4(IP);
        }else if (IP.chars().filter(ch -> ch == ':').count() == 7){
            return validateIPv6(IP);
        }else {
            return "Neither";
        }
    }

    public String validateIPv4(String IP){
        String[] nums = IP.split("\\.",-1);
        for (String x : nums){
            if (x.length() == 0 || x.length() > 3){
                return "Neither";
            }
            if (x.charAt(0) == '0' && x.length() != -1){
                return "Neither";
            }
            for (char ch : x.toCharArray()){
                if (!Character.isDigit(ch)){
                    return "Neither";
                }
            }
            if (Integer.parseInt(x) > 255){
                return "Neither";
            }
        }
        return "IPv4";
    }

    public String validateIPv6(String IP){
        String[] nums = IP.split(":",-1);
        String hex = "0123456789abcdefABCDEF";
        for (String x : nums){
            if (x.length() == 0 || x.length() > 4){
                return "Neither";
            }
            for (char ch : x.toCharArray()){
                if (hex.indexOf(ch) == -1){
                    return "Neither";
                }
            }
        }
        return "IPv6";
    }

    // NC-43
    ArrayList<ArrayList<Integer>> res3 = new ArrayList<ArrayList<Integer>>();
    public ArrayList<ArrayList<Integer>> permute(int[] num) {
        if (num == null || num.length == 0){
            return res3;
        }
        Arrays.sort(num);
        ArrayList<Integer> tmp = new ArrayList<>();
        dfs(tmp,num);
        return res3;
    }

    public void dfs(ArrayList<Integer> tmp,int[] num){
        if (tmp.size() == num.length){
            res3.add(new ArrayList<>(tmp));
            return;
        }
        for (int i = 0;i < num.length;i++){
            if(!tmp.contains(num[i])){
                tmp.add(num[i]);
                dfs(tmp, num);
                tmp.remove(tmp.size() - 1);
            }
        }
    }

    // NC-108
    public int solve1 (char[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0){
            return 0;
        }
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] dp = new int[rows][cols];
        int max = 0;
        for (int i = 0;i < rows;i++){
            if (matrix[i][0] == '1'){
                dp[i][0] = 1;
            }
        }
        for (int i = 0;i < cols;i++){
            if (matrix[0][i] == '1'){
                dp[0][i] = 1;
            }
        }

        for (int i = 1;i < rows;i++){
            for (int j = 1;j < cols;j++){
                if (matrix[i][j] == '1'){
                    dp[i][j] = Math.min(Math.min(dp[i-1][j-1],dp[i-1][j]),dp[i][j-1]) + 1;
                    if (dp[i][j] > max){
                        max = dp[i][j];
                    }
                }
            }
        }
        return max * max;
    }

    // NC-132
    public int ysf (int n, int m) {
        ListNode head = new ListNode(1);
        ListNode tail = head;
        for (int i = 2; i <= n;i++){
            tail.next = new ListNode(i);
            tail = tail.next;
        }

        tail.next = head;
        ListNode index = head;
        ListNode pre = tail;

        int k = 0;
        while (index.next != null && index.next != index){
            k++;
            ListNode next = index.next;
            if (k == m){
                pre.next = pre.next.next;
                k = 0;
            }
            pre = index;
            index = next;
        }
        return index.val;
    }

    // NC-120
    public int NumberOf1(int n) {
        int count = 0;
        int flag = 1;
        while (flag != 0){
            if ((n & flag) != 0){
                count++;
            }
            flag <<= 1;
        }
        return count;
    }

    // NC-131
    public double[] flowmedian (int[][] operations) {
        ArrayList<Double> arr = new ArrayList<>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);
            }
        });
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });

        for (int[] op : operations){
            if (op[0] == 1){
                if (maxHeap.isEmpty() || maxHeap.peek() > op[1]){
                    maxHeap.add(op[1]);
                }else {
                    minHeap.add(op[1]);
                }
                if (minHeap.size() == maxHeap.size() + 2){
                    maxHeap.add(minHeap.poll());
                }
                if (minHeap.size() + 2 == maxHeap.size()){
                    minHeap.add(maxHeap.poll());
                }
            }else {
                if (maxHeap.size() == 0){
                    double ans = -1;
                    arr.add(ans);
                    continue;
                }
                if (maxHeap.size() == minHeap.size()){
                    double num1 = maxHeap.peek();
                    double num2 = minHeap.peek();
                    arr.add((num1 + num2) / 2);
                }else if (maxHeap.size() > minHeap.size()){
                    double num1 = maxHeap.peek();
                    arr.add(num1);
                }else {
                    double num1 = minHeap.peek();
                    arr.add(num1);
                }
            }
        }

        double[] arrs = new double[arr.size()];
        for (int i = 0;i < arrs.length;i++){
            arrs[i] = arr.get(i);
        }
        return arrs;
    }

    // NC-124
    public String[] trieU (String[][] operators) {
        HashMap<String,Integer> dict = new HashMap<>();
        ArrayList<String> res = new ArrayList<>();
        for (int i = 0;i < operators.length;i++){
            String op = operators[i][0];
            String str = operators[i][1];
            if (op.equals("1")){
                if (dict.containsKey(str)){
                    dict.put(str,dict.get(str) + 1);
                }else {
                    dict.put(str,1);
                }
            }else if (op.equals("2")){
                if (dict.get(str) == 1 ){
                    dict.remove(str);
                }else {
                    dict.put(str, dict.get(str) - 1);
                }
            }else if (op.equals("3")){
                if (dict.containsKey(str)){
                    res.add("YES");
                }else {
                    res.add("NO");
                }
            }else if (op.equals("4")){
                int count = 0;
                for (String key : dict.keySet()){
                    if (key.startsWith(str)){
                        count++;
                    }
                }
                res.add(""+ count);
            }
        }
        int n = res.size();
        String[] res2 = new String[n];
        for (int i = 0;i < n;i++){
            res2[i] = res.get(i);
        }
        return res2;
    }

    // NC-80
    ArrayList<ArrayList<Integer> > Print(TreeNode pRoot) {
        ArrayList<ArrayList<Integer> > res = new ArrayList<>();
        if (pRoot == null){
            return res;
        }
        ArrayList<Integer> list;
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode cur;
        queue.offer(pRoot);
        while (!queue.isEmpty()){
            list = new ArrayList<>();
            int size = queue.size();
            while (size > 0){
                cur = queue.poll();
                list.add(cur.val);
                if (cur.left != null){
                    queue.offer(cur.left);
                }
                if (cur.right != null){
                    queue.offer(cur.right);
                }
                size--;
            }
            res.add(list);
        }
        return res;
    }

    // NC-29
    public boolean searchMatrix (int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;
        int i = 0,j = n-1;
        while (i < m && j >= 0){
            if (matrix[i][j] == target){
                return true;
            }else if (matrix[i][j] > target){
                j--;
            }else {
                i++;
            }
        }
        return false;
    }

    // NC-116
    public int solve2 (String nums) {
        if (nums.length() == 0 || nums.charAt(0) == '0'){
            return 0;
        }
        int[] dp = new int[nums.length()];
        dp[0] = 1;
        for (int i = 1;i < dp.length;i++){
            if (nums.charAt(i) != '0'){
                dp[i] = dp[i-1];
            }
            int num = (nums.charAt(i-1)-'0') * 10 + (nums.charAt(i)-'0');
            if (num >= 10 && num <= 26){
                if (i == 1){
                    dp[i] += 1;
                }else {
                    dp[i] += dp[i-2];
                }
            }
        }
        return dp[dp.length - 1];
    }

    // NC-83
    public double maxProduct(double[] arr) {
        double min = arr[0];
        double max = min;
        double res = min;
        for (int i = 1;i < arr.length;i++){
            double tmp = max;
            max = Math.max(Math.max(arr[i],arr[i] * max),min * arr[i]);
            min = Math.min(Math.min(arr[i],arr[i] * min),tmp * arr[i]);
            res = Math.max(res,max);
        }
        return res;
    }

    // NC-143
    public int[][] solve (int[][] a, int[][] b) {
        int m = a.length;
        int p = a[0].length;
        int n = b[0].length;
        int[][] res = new int[m][n];
        for (int i = 0;i < m;i++){
            for (int j = 0;j < n;j++){
                int t = 0;
                for (int k = 0;k < p;k++){
                    t += a[i][k] * b[k][j];
                }
                res[i][j] = t;
            }
        }
        return res;
    }

    // NC-138
    public int solve (int[][] matrix) {
        if (matrix.length == 0){
            return 0;
        }
        int[][] visited = new int[matrix.length][matrix[0].length];
        int max = 0;
        for (int i = 0;i < matrix.length;i++){
            for (int j = 0;j < matrix[0].length;j++){
                if (visited[i][j] == 0){
                    max = Math.max(max,dfs(i,j,matrix,visited));
                }
            }
        }
        return max;
    }

    public int dfs(int i,int j,int[][] matrix,int[][] visited){
        if (i < 0 || i >= matrix.length || j < 0 || j >= matrix[0].length){
            return 0;
        }
        if (visited[i][j] > 0){
            return visited[i][j];
        }
        int max = 0;
        if (i - 1 >= 0 && matrix[i-1][j] < matrix[i][j]){
            max = Math.max(max,dfs(i-1,j,matrix,visited));
        }
        if (i + 1 < matrix.length && matrix[i+1][j] < matrix[i][j]){
            max = Math.max(max,dfs(i+1,j,matrix,visited));
        }
        if (j - 1 >= 0 && matrix[i][j-1] < matrix[i][j]){
            max = Math.max(max,dfs(i,j-1,matrix,visited));
        }
        if (j + 1 < matrix[0].length && matrix[i][j+1] < matrix[i][j]){
            max = Math.max(max,dfs(i,j+1,matrix,visited));
        }
        visited[i][j] = max + 1;
        return max + 1;
    }

    // NC-64
    TreeNode pre = null;
    public TreeNode Convert(TreeNode pRootOfTree) {
        if (pRootOfTree == null){
            return null;
        }
        Convert(pRootOfTree.right);
        if (pre != null){
            pRootOfTree.right = pre;
            pre.left = pRootOfTree;
        }
        pre = pRootOfTree;
        Convert(pRootOfTree.left);
        return pre;
    }

    /**
     * 另外解法
     * @param pRootOfTree
     * @return
     */
    public TreeNode Convert1(TreeNode pRootOfTree) {
        if (pRootOfTree == null){
            return null;
        }
        ArrayList<TreeNode> list = new ArrayList<>();
        Convert1(list,pRootOfTree);
        return Convert1(list);
    }

    public void Convert1(ArrayList<TreeNode> list,TreeNode root){
        if (root != null){
            Convert1(list,root.left);
            list.add(root);
            Convert1(list,root.right);
        }
    }

    public TreeNode Convert1(ArrayList<TreeNode> list){
        TreeNode head = list.get(0);
        TreeNode cur = head;
        for (int i = 1; i < list.size();i++){
            TreeNode node = list.get(i);
            node.left  = cur;
            cur.right = node;
            cur = node;
        }
        return head;
    }

    // NC-142
    public int solve3 (String a) {
        if (a == null || a.length() <= 1){
            return 0;
        }
        char[] chars=  a.toCharArray();
        int len = chars.length;
        int maxLen = chars.length / 2;
        for (int i = maxLen;i >= 1;i--){
            for (int j = 0;j <= len - 2 * i;j++){
                if (check(chars,j,i)){
                    return 2 * i;
                }
            }
        }
        return 0;
    }

    public boolean check(char[] chars,int start,int len){
        for (int i = start;i < start + len;i++){
            if (chars[i] != chars[i + len]){
                return false;
            }
        }
        return true;
    }

    // NC-135
    public int maxProfit (int[] prices) {
        if (prices.length == 0){
            return 0;
        }
        int[][] dp = new int[prices.length][5];
        dp[0][1] = -prices[0];
        dp[0][3] = -prices[0];
        for (int i = 1;i < prices.length;i++){
            dp[i][0] = dp[i-1][0];
            dp[i][1] = Math.max(dp[i-1][1],dp[i-1][0]-prices[i]);
            dp[i][2] = Math.max(dp[i-1][2],dp[i-1][1]+prices[i]);
            dp[i][3] = Math.max(dp[i-1][3],dp[i-1][2]-prices[i]);
            dp[i][4] = Math.max(dp[i-1][4],dp[i-1][3]+prices[i]);
        }
        return dp[prices.length-1][4];
    }

    // NC-139
    public int LastRemaining_Solution(int n, int m) {
        if (n < 1 || m < 1){
            return -1;
        }
        List<Integer> list = new ArrayList<>();
        for (int i = 0;i < n;i++){
            list.add(i);
        }
        int cur = -1;
        while (list.size() > 1){
            for (int i = 0;i < m;i++){
                cur++;
                if (cur == list.size()){
                    cur = 0;
                }
            }
            list.remove(cur);
            cur--;
        }
        return list.get(0);
    }

    // NC-84
    public int nodeNum(TreeNode head) {
        if (head == null){
            return 0;
        }
        return 1 + dfs(head.left) + dfs(head.right);
    }

    public int dfs(TreeNode head){
        if (head == null){
            return 0;
        }
        int level = 0;
        TreeNode l = head,r = head;
        while (l != null && r!= null){
            level++;
            l = l.left;
            r = r.right;
        }
        if (l == null && r != null){
            return (1 << level) - 1;
        }else {
            return 1 + dfs(head.left) + dfs(head.right);
        }
    }

    // NC-104
    public int compare (String version1, String version2) {
        String[] str1 = version1.split("\\.");
        String[] str2 = version2.split("\\.");
        int n = Math.max(str1.length,str2.length);
        for (int i = 0;i < n;i++){
            int x = i < str1.length ? Integer.valueOf(str1[i]) : 0;
            int y = i < str2.length ? Integer.valueOf(str2[i]) : 0;
            if (x < y){
                return -1;
            }else if (x > y){
                return 1;
            }
        }
        return 0;
    }

    // NC-129
    public long thenumberof0 (long n) {
        if (n < 1){
            return 0;
        }
        long res = 0;
        while (n != 0){
            res = res + n / 5;
            n /= 5;
        }
        return res;
    }

    // NC-130
    public int candy (int[] arr) {
        int[] tmp = new int[arr.length];
        Arrays.fill(tmp,1);
        int count = 0;
        for (int i = 1;i < arr.length;i++){
            if (arr[i] > arr[i-1]){
                tmp[i] = tmp[i-1] + 1;
            }
        }

        for (int i = arr.length - 1;i > 0;i--){
            if (arr[i-1] > arr[i]){
                tmp[i-1] = Math.max(tmp[i-1] ,tmp[i] + 1);
            }
        }

        for (int i : tmp){
            count += i;
        }
        return count;
    }

    // NC-23
    public ListNode partition (ListNode head, int x) {
        if (head == null){
            return head;
        }
        ListNode head1 = new ListNode(0);
        ListNode head2 = new ListNode(0);
        ListNode node1 = head1;
        ListNode node2 = head2;
        ListNode tmp = head;
        while (tmp != null){
            if (tmp.val < x){
                node1.next = tmp;
                node1 = tmp;
            }else {
                node2.next = tmp;
                node2 = tmp;
            }
            tmp = tmp.next;
        }
        node2.next = null;
        node1.next = head2.next;
        return head1.next;
    }

    // NC-115
    public int[] solve3 (int[] a) {
        Stack<Integer> stack = new Stack<>();
        ArrayList<Integer> list = new ArrayList<>();
        int n = a.length;
        int[] maxs = new int[n];
        int max = Integer.MIN_VALUE;
        for (int i = n-1;i >= 0;i--){
            max = Math.max(a[i],max);
            maxs[i] = max;
        }

        for (int i = 0;i < n;i++){
            stack.push(a[i]);
            while (!stack.isEmpty() && i < n-1 && stack.peek() > maxs[i+1]){
                list.add(stack.pop());
            }
        }
        while (!stack.isEmpty()){
            list.add(stack.pop());
        }
        int[] res = new int[n];
        for (int i = 0;i < n;i++){
            res[i] = list.get(i);
        }
        return res;
    }

    // NC-34
    public int minPathSum1 (int[][] grid) {
        if (grid == null){
            return 0;
        }
        int row = grid.length;;
        int col = grid[0].length;
        int[][] dp = new int[row][col];
        dp[0][0] = grid[0][0];
        for (int i = 1;i < row;i++){
            dp[i][0] = dp[i-1][0] + grid[i][0];
        }
        for (int i = 1;i < col;i++){
            dp[0][i] = dp[0][i-1] + grid[0][i];
        }
        for (int i = 1;i < row;i++){
            for (int j = 1;j < col;j++){
                dp[i][j] = Math.min(dp[i-1][j],dp[i][j-1]) + grid[i][j];
            }
        }
        return dp[row-1][col-1];
    }
}
