package com.train.algorithm.tree;

public class TreeTravel {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
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
}
