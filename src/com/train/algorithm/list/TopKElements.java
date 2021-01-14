package com.train.algorithm.list;

import java.util.PriorityQueue;
import java.util.Queue;

// 最K个系列
public class TopKElements {
    // 剑指offer-40
    public int[] getLeastNumbers(int[] arr, int k) {
        if (k == 0 || arr.length == 0) {
            return new int[0];
        }
        Queue<Integer> queue = new PriorityQueue<Integer>();
        int t = 0;int[] res = new int[k];
        for (int num:arr) {
            queue.add(num);
        }
        while(t < k){
            res[t++] = queue.poll().intValue();
        }
        return res;
    }
}
