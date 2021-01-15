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

    // leetcode-451
    public String frequencySort(String s) {
        char[] c = s.toCharArray();
        Map<Character,Integer> map = new HashMap<>();
        char[] re = new char[c.length];
        for (char c1 : c) {
            if (map.containsKey(c1)) {
                map.put(c1,map.get(c1) + 1);
            }else{
                map.put(c1,1);
            }
        }
        Queue<Character> queue = new PriorityQueue<Character>(new Comparator<Character>(){
            @Override
            public int compare(Character a, Character b){
                return map.get(a) - map.get(b);
            }
        });
        for (Character key:map.keySet()) {
            queue.add(key);
        }
        int i = 0;
        while(!queue.isEmpty()){
            char temp = queue.poll();
            int count = map.get(temp);
            for (int j = 0;j <  count;j++ ) {
                re[i] = temp;
                i++;
            }
        }
        String res = String.valueOf(re);
        String result = new StringBuilder(res).reverse().toString();
        return result;
    }

    // leetcode-703
    class KthLargest {
        private PriorityQueue<Integer> queue;
        private int limit;
        public KthLargest(int k, int[] nums) {
            limit = k;
            queue = new PriorityQueue<>(k);
            for (int num : nums) {
                add(num);
            }
        }

        public int add(int val) {
            if (queue.size() < limit) {
                queue.add(val);
            }else if (val > queue.peek()) {
                queue.poll();
                queue.add(val);
            }
            return queue.peek();
        }
    }

    // leetcode-658
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int len = arr.length;
        int left = 0;
        int right = len - 1;
        int removeNums = len - k;
        while(removeNums > 0){
            if (x - arr[left] <= arr[right] - x) {
                right--;
            }else{
                left++;
            }
            removeNums--;
        }
        List<Integer> res = new ArrayList<>();
        for (int i = left;i < left +k ;i++ ) {
            res.add(arr[i]);
        }
        return res;
    }

}
