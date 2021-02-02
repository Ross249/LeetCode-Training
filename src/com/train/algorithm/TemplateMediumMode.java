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
}
