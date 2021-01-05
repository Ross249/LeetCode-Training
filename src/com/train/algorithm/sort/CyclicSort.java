package com.train.algorithm.sort;

import java.util.ArrayList;
import java.util.List;

// 循环排序
public class CyclicSort {
    // leetcode-448
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> res = new ArrayList<>();
        int len = nums.length;
        for (int i = 0;i < len ;i++ ) {
            int num = Math.abs(nums[i]);
            int index = num - 1;
            if (nums[index] > 0) {
                nums[index] *= -1;
            }
        }
        for (int i = 0;i < len ;i++ ) {
            if (nums[i] > 0) {
                int num = i + 1;
                res.add(num);
            }
        }
        return res;
    }
}
