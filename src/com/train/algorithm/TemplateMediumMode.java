package com.train.algorithm;

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
}
