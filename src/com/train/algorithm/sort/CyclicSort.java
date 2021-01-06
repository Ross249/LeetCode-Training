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

    // leetcode-287
    public int findDuplicate(int[] nums) {
        int length = nums.length;
        int left = 0,right = length - 1;
        int res = -1;
        while(left <= right){
            int mid = (right + left) / 2;
            int count = 0;
            for (int i = 0;i < length ;i++ ) {
                if (nums[i] <= mid) {
                    count++;
                }
            }
            if (count <= mid) {
                left = mid + 1;
            }else {
                right = mid - 1;
                res = mid;
            }
        }
        return res;
    }

    // leetcode-442
    public List<Integer> findDuplicates(int[] nums) {
        int len = nums.length;
        List<Integer> res = new ArrayList<Integer>();
        for (int i = 0;i < len ;i++ ) {
            int num = Math.abs(nums[i]);
            if (nums[num - 1] > 0) {
                nums[num - 1] *= -1;
            }else{
                res.add(num);
            }
        }
        return res;
    }
}
