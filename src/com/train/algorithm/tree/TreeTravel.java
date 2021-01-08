package com.train.algorithm.tree;

import java.util.*;

public class TreeTravel {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    public void pre(TreeNode root){
        if (root!=null){
            System.out.println(root.val);
            pre(root.left);
            pre(root.right);
        }
    }

    public void in(TreeNode root){
        if (root!=null){
            in(root.left);
            System.out.println(root.val);
            in(root.right);
        }
    }

    public void post(TreeNode root){
        if (root!=null){
            post(root.left);
            post(root.right);
            System.out.println(root.val);
        }
    }

    // leetcode-107
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> res = new LinkedList<List<Integer>>();
        if (root == null) {
            return res;
        }
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        while(!queue.isEmpty()){
            List<Integer> level = new ArrayList<Integer>();
            int size = queue.size();
            for (int i = 0;i < size ;i++ ) {
                TreeNode node = queue.poll();
                level.add(node.val);
                TreeNode left = node.left,right = node.right;
                if (left != null) {
                    queue.offer(left);
                }
                if (right != null) {
                    queue.offer(right);
                }
            }
            res.add(0,level);
        }
        return res;
    }

    // leetcode-103
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new LinkedList<List<Integer>>();
        if (root == null) {
            return res;
        }
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        boolean order = true;
        while(!queue.isEmpty()){
            Deque<Integer> level = new LinkedList<Integer>();
            int size = queue.size();
            for (int i = 0;i < size ;i++ ) {
                TreeNode node = queue.poll();
                if (order) {
                    level.offerLast(node.val);
                }else{
                    level.offerFirst(node.val);
                }
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            res.add(new LinkedList<Integer>(level));
            order = !order;
        }
        return res;
    }

    // leetcode-637
    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        while(!queue.isEmpty()){
            double avg = 0,count = 0;
            int size = queue.size();
            for (int i = 0;i < size ;i++ ) {
                TreeNode node = queue.poll();
                count += node.val;
                TreeNode left = node.left,right = node.right;
                if (left != null) {
                    queue.offer(left);
                }
                if (right != null) {
                    queue.offer(right);
                }
            }
            avg = count / size;
            res.add(avg);
        }
        return res;
    }

    // leetcode-111
    class QueueNode{
        TreeNode node;
        int depth;
        public QueueNode(TreeNode node,int depth){
            this.node = node;
            this.depth = depth;
        }
    }
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<QueueNode> queue = new LinkedList<QueueNode>();
        queue.offer(new QueueNode(root,1));
        while(!queue.isEmpty()){
            QueueNode node = queue.poll();
            TreeNode treeNode = node.node;
            int depth = node.depth;
            if (treeNode.left == null && treeNode.right == null) {
                return depth;
            }
            if (treeNode.left != null) {
                queue.offer(new QueueNode(treeNode.left,depth + 1));
            }
            if (treeNode.right != null) {
                queue.offer(new QueueNode(treeNode.right,depth + 1));
            }
        }
        return 0;
    }

    // leetcode-112
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        if (root.left == null && root.right == null) {
            return sum == root.val;
        }
        return hasPathSum(root.left,sum - root.val) || hasPathSum(root.right,sum - root.val);
    }

    // leetcode-113
    List<List<Integer>> res = new LinkedList<>();
    Deque<Integer>  queue = new LinkedList<>();
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        dfs(root,sum);
        return res;
    }
    public void dfs(TreeNode root,int sum){
        if (root == null) {
            return;
        }
        queue.offer(root.val);
        sum -= root.val;
        if (root.left == null && root.right == null && sum == 0) {
            res.add(new LinkedList<Integer>(queue));
        }
        dfs(root.left,sum);
        dfs(root.right,sum);
        queue.pollLast();
    }

    // leetcode-437
    public int pathSum(TreeNode root, int sum) {
        if (root == null) {
            return 0;
        }
        int result = countPath(root,sum);
        int a = pathSum(root.left,sum);
        int b = pathSum(root.right,sum);
        return result + a + b;
    }
    public int countPath(TreeNode root,int sum){
        if (root == null) {
            return 0;
        }
        sum = sum - root.val;
        int result = sum == 0 ? 1 : 0;
        return result + countPath(root.left,sum) + countPath(root.right,sum);
    }

}
