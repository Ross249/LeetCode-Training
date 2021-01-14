package com.train.algorithm.list;

import java.util.*;

// 最K个系列（优先队列）
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

    // leetcode-973
    public int[][] kClosest(int[][] points, int K) {
        PriorityQueue<int[]> queue = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                return b[0] * b[0] + b[1] * b[1] - a[0] * a[0] - a[1] * a[1] ;
            }
        });
        for (int[] p:points ) {
            queue.add(p);
            if (queue.size() > K) {
                queue.poll();
            }
        }
        int[][] res = new int[K][2];
        for (int i = 0;i < K ;i++ ) {
            int[] temp = queue.poll();
            res[i][0] = temp[0];
            res[i][1] = temp[1];
        }
        return res;
    }

    // leetcode-347
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer,Integer> map = new HashMap<>();
        int[] res = new int[k];
        for (int num:nums) {
            if (map.containsKey(num)) {
                map.put(num,map.get(num)+1);
            }else {
                map.put(num,1);
            }
        }
        Queue<Integer> queue = new PriorityQueue<Integer>(new Comparator<Integer>(){
            @Override
            public int compare(Integer a, Integer b){
                return map.get(a) - map.get(b);
            }
        });
        for (Integer key:map.keySet()) {
            if (queue.size() < k) {
                queue.add(key);
            }else if (map.get(key) > map.get(queue.peek())) {
                queue.poll();
                queue.add(key);
            }
        }
        int i = 0;
        while(!queue.isEmpty()){
            res[i++] = queue.poll();
        }
        return res;
    }
}
