package com.train.algorithm;

import java.util.*;

public class TemplateHardMode {
    public class Node{
        int key;
        int value;
        int frep=1;
        public Node(){
        }
        public Node(int key,int value){
            this.key = key;
            this.value = value;
        }
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

    // NC-95
    public int MLS (int[] arr) {
        HashMap<Integer,Integer> map = new HashMap<>();
        int max = 0;
        for (int a : arr){
            if (!map.containsKey(a)){
                int left = map.getOrDefault(a-1,0);
                int right = map.getOrDefault(a+1,0);
                int sum = left + right + 1;
                map.put(a,sum);
                map.put(a-left,sum);
                map.put(a+right,sum);
                max = Math.max(max,sum);
            }
        }
        return max;
    }

    // NC-94
    Map<Integer,Node> cache;
    Map<Integer,LinkedHashSet<Node>> freqMap;
    int size;
    int capacity;
    int min;
    public int[] LFU (int[][] operators, int k) {
        this.capacity = k;
        cache = new HashMap<>();
        freqMap = new HashMap<>();
        List<Integer> out = new ArrayList<>();
        for (int i = 0;i < operators.length;i++){
            if (operators[i][0] == 1){
                put(operators[i][1],operators[i][2]);
            }else if (operators[i][0] == 2){
                out.add(get(operators[i][1]));
            }
        }
        int[] res = new int[out.size()];
        for (int i = 0;i < out.size();i++){
            res[i] = out.get(i);
        }
        return res;
    }

    public int get(int key){
        Node node = cache.get(key);
        if (node == null){
            return -1;
        }
        freqInc(node);
        return node.value;
    }

    public void put(int key,int value){
        if (capacity == 0){
            return;
        }
        Node node = cache.get(key);
        if (node != null){
            node.value = value;
            freqInc(node);
        }else {
            if (size == capacity){
                Node deadNode = removeNode();
                cache.remove(deadNode.key);
                size--;
            }
            node = new Node(key,value);
            cache.put(key,node);
            addNode(node);
            size++;
        }
    }

    public void freqInc(Node node){
        int freq = node.frep;
        LinkedHashSet<Node> set = freqMap.get(freq);
        set.remove(node);
        if (freq == min && set.size() == 0){
            min = freq + 1;
        }
        node.frep++;
        LinkedHashSet<Node> newSet = freqMap.computeIfAbsent(freq+1,k -> new LinkedHashSet<>());
        newSet.add(node);
    }

    public Node removeNode(){
        LinkedHashSet<Node> set = freqMap.get(min);
        Node deadNode = set.iterator().next();
        set.remove(deadNode);
        return deadNode;
    }

    public void addNode(Node node){
        LinkedHashSet<Node> set = freqMap.computeIfAbsent(1,k -> new LinkedHashSet<>());
        set.add(node);
        min = 1;
    }

    // NC-28
    public String minWindow (String S, String T) {
        if (S == null || S == "" || T == null || T == "" || S.length() < T.length() ){
            return "";
        }
        int[] needs = new int[128];
        int[] widow = new int[128];
        for (int i = 0;i < T.length();i++){
            needs[T.charAt(i)]++;
        }
        int left = 0;
        int right = 0;
        String res = "";
        int count = 0;
        int minLength = S.length() + 1;
        while (right < S.length()){
            char ch = S.charAt(right);
            widow[ch]++;
            if (needs[ch] > 0 && needs[ch] >= widow[ch]){
                count++;
            }
            while (count == T.length()){
                ch = S.charAt(left);
                if (needs[ch] > 0 && needs[ch] >= widow[ch]){
                    count--;
                }
                if (right - left + 1< minLength){
                    minLength = right - left + 1;
                    res = S.substring(left,right + 1);
                }
                widow[ch]--;
                left++;
            }
            right++;
        }
        return res;
    }

    // NC-122
    public boolean match(char[] str, char[] pattern) {
        int m = str.length + 1,n = pattern.length + 1;
        boolean[][] dp = new boolean[m][n];
        dp[0][0] = true;
        for (int j = 2;j < n;j+=2){
            dp[0][j] = dp[0][j-2] && pattern[j-1] == '*';
        }
        for (int i = 1;i < m;i++){
            for (int j = 1;j < n;j++){
                dp[i][j] = pattern[j-1] == '*' ?
                        dp[i][j-2]||dp[i][j-1]||dp[i-1][j]&&(str[i-1] == pattern[j-2])||pattern[j-2]=='.':
                        dp[i-1][j-1] && (pattern[j-1] == '.' || str[i-1] == pattern[j-1]);
            }
        }
        return dp[m-1][n-1];
    }

    // NC-39
    int result1;
    public int Nqueen (int n) {
        result1 = 0;
        dfs(new int[n],0);
        return result1;
    }
    public void dfs(int[] nums,int cur){
        int n = nums.length;
        if (cur == n){
            result1++;
            return;
        }
        boolean[] visited = new boolean[n];
        for (int i = 0;i < cur;i++){
            int e = cur - i;
            int v = nums[i];
            int r = v + e;
            int l = v - e;
            visited[v] = true;
            if (l >= 0){
                visited[l] = true;
            }
            if (r < n){
                visited[r] = true;
            }
        }
        for (int i = 0;i < n;i++){
            if (visited[i]){
                continue;
            }
            nums[cur] = i;
            dfs(nums,cur+1);
        }
    }

    // NC-44
    public boolean isMatch(String s, String p) {
        int row = s.length();
        int col = p.length();
        boolean[][] dp = new boolean[row + 1][col + 1];
        dp[0][0] = true;
        for (int j = 1;j < col + 1;j++){
            if (dp[0][j-1]){
                if (p.charAt(j-1) == '*'){
                    dp[0][j] = true;
                }else {
                    break;
                }
            }
        }
        for (int i = 0;i < row;i++){
            for (int j = 0;j < col;j++){
                if (p.charAt(j) == s.charAt(i) || p.charAt(j) == '?'){
                    dp[i+1][j+1] = dp[i][j];
                }else if (p.charAt(j) == '*'){
                    dp[i+1][j+1] = dp[i][j] || dp[i+1][j] || dp[i][j+1];
                }
            }
        }
        return dp[row][col];
    }

    // NC-47
    public void solveSudoku(char[][] board) {
        dfs(board,0,0);
    }

    public boolean dfs(char[][] board,int x,int y){
        if (x == 9){
            return true;
        }
        if (y == 9){
            return dfs(board, x+1, 0);
        }
        if (board[x][y] != '.'){
            return dfs(board,x,y+1);
        }
        for (char c = '1';c <= '9';++c){
            if (!isValid(board,x,y,c)){
                continue;
            }
            board[x][y] = c;
            if (dfs(board, x, y+1)){
                return true;
            }
            board[x][y] = '.';
        }
        return false;
    }

    public boolean isValid(char[][] board,int x,int y,char ch){
        for (int i = 0;i < 9;++i){
            if (board[x][i] == ch){
                return false;
            }
            if (board[i][y] == ch){
                return false;
            }
            if (board[(x/3)*3+i/3][(y/3)*3+i%3] == ch){
                return false;
            }
        }
        return true;
    }

    // NC-118
    int count;
    public int InversePairs(int [] array) {
        if (array == null || array.length == 0){
            return 0;
        }
        MergeSort(array,0,array.length-1);
        return count;
    }

    public void MergeSort(int[] arr,int start,int end){
        if (start >= end){
            return;
        }
        int mid = (start + end) / 2;
        MergeSort(arr, start, mid);
        MergeSort(arr, mid + 1, end);
        Merge(arr,start,mid,end);
    }

    public void Merge(int[] arr,int start,int mid,int end){
        int[] tmp = new int[end - start + 1];
        int k = 0,i = start,j = mid + 1;
        while (i <= mid && j <= end){
            if (arr[i] <= arr[j]){
                tmp[k++] = arr[i++];
            }else {
                tmp[k++] = arr[j++];
                count = (count + (mid + 1 - i)) % 1000000007;
            }
        }
        while (i <= mid){
            tmp[k++] = arr[i++];
        }
        while (j <= end){
            tmp[k++] = arr[j++];
        }
        for (int l = 0; l < k;l++){
            arr[start + l] = tmp[l];
        }
    }
}
