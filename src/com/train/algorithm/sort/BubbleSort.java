package com.train.algorithm.sort;

public class BubbleSort {
    public void bubbleSort(int[] nums){
        if (null == nums ||nums.length<=1){
            return;
        }
        for (int i = 0;i < nums.length-1;i++){
            for (int j = i+1;j < nums.length;j++){
                if (nums[i] > nums[j]){
                    nums[i] = nums[i] + nums[j];
                    nums[j] = nums[i] - nums[j];
                    nums[i] = nums[i] - nums[j];
                }
            }
        }
    }

    public void bubbleSort1(int[] nums){
        if (null == nums ||nums.length<=1){
            return;
        }
        int length = nums.length;
        boolean flag = true;
        while (flag){
            flag = false;
            for (int i = 1;i < length;i++){
                if (nums[i-1] > nums[i]){
                    int temp;
                    temp = nums[i-1];
                    nums[i-1] = nums[i];
                    nums[i] = temp;
                    flag = true;
                }
            }
            length--;
        }
    }

    public void bubbleSort2(int[] nums){
        int j,k;
        int flag = nums.length;
        while (flag > 0){
            k = flag;
            flag = 0;
            for (j = 1;j < k;j++){
                if (nums[j-1] > nums[j]){
                    int temp;
                    temp = nums[j-1];
                    nums[j-1] = nums[j];
                    nums[j] = temp;
                    flag = j;
                }
            }
        }
    }
}
