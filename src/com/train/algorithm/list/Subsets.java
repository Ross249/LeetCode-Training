package com.train.algorithm.list;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// 子集问题(回朔法)
public class Subsets {
    // leetcode-78
    List<Integer> temp = new ArrayList<Integer>();
    List<List<Integer>> res = new ArrayList<List<Integer>>();
    public List<List<Integer>> subsets(int[] nums) {
        dfs(0,nums);
        return res;
    }
    public void dfs(int cur,int[] nums){
        if (cur == nums.length) {
            res.add(new ArrayList<Integer>(temp));
            return;
        }
        temp.add(nums[cur]);
        dfs(cur+1,nums);
        temp.remove(temp.size() - 1);
        dfs(cur+1,nums);
    }

    // leetcode-46
    public List<List<Integer>> permute(int[] nums) {
        List<Integer> temp = new ArrayList<Integer>();
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        for(int num:nums){
            temp.add(num);
        }
        int n = nums.length;
        dfs(n,temp,res,0);
        return res;
    }
    public void dfs(int n,List<Integer> temp,List<List<Integer>> res,int first){
        if (first == n) {
            res.add(new ArrayList<Integer>(temp));
        }
        for (int i = first;i < n ;i++ ) {
            Collections.swap(temp,first,i);
            dfs(n,temp,res,first+1);
            Collections.swap(temp,first,i);
        }
    }

    // leetcode-784
    public List<String> letterCasePermutation(String S) {
        List<String> res = new ArrayList<>();
        char[] charArray = S.toCharArray();
        dfs(charArray,0,res);
        return res;
    }
    private void dfs(char[] charArray,int index,List<String> res){
        if (index == charArray.length) {
            res.add(new String(charArray));
            return;
        }
        dfs(charArray,index+1,res);
        if (Character.isLetter(charArray[index])) {
            // 转大写
            charArray[index] ^= 1 << 5;
            dfs(charArray,index+1,res);
        }
    }
}
