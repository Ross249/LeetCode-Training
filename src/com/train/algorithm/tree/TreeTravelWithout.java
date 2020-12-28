package com.train.algorithm.tree;

import java.util.*;

public class TreeTravelWithout {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public void level(TreeNode root){
        if (root == null){
            return;
        }
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            TreeNode node = queue.poll();
            System.out.println(node.val);
            if (node.left!=null){
                queue.offer(node.left);
            }
            if (node.right!=null){
                queue.offer(node.right);
            }
        }
    }

    public void pre(TreeNode root){
        if (root == null){
            return;
        }
        LinkedList<TreeNode> stack = new LinkedList<>();
        TreeNode pNode = root;
        while (pNode != null || !stack.isEmpty()){
            if (pNode!=null){
                System.out.println(pNode.val);
                stack.push(pNode);
                pNode = pNode.left;
            }else {
                TreeNode node = stack.pop();
                pNode = node.right;
            }
        }
    }

    public List<Integer> in(TreeNode root){
        List<Integer> list = new ArrayList<>();
        ArrayDeque<TreeNode> stack = new ArrayDeque<>();
        while (root!=null||!stack.isEmpty()){
            while (root != null){
                stack.addFirst(root);
                root = root.left;
            }
            root = stack.removeFirst();
            list.add(root.val);
            root = root.right;
        }
        return list;
    }

    public List<Integer> post(TreeNode root){
        List<Integer> list = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode pre = null;
        while (!stack.isEmpty()||root!=null){
            while (root!=null){
                stack.push(root);
                root = root.left;
            }
            root = stack.peek();
            if (root.right!=null && root.right!=pre){
                root = root.right;
            }else {
                root = stack.pop();
                list.add(root.val);
                pre = root;
                root = null;
            }
        }
        return list;
    }
}
