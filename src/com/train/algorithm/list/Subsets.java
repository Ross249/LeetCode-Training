package com.train.algorithm.list;

import java.util.ArrayList;
import java.util.List;

// 子集问题
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
}
