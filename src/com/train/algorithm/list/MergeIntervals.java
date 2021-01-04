package com.train.algorithm.list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

// 合并区间
public class MergeIntervals {
    // leetcode-56
    public int[][] merge(int[][] intervals) {
        int l = intervals.length;
        if (l == 0) {
            return new int[0][2];
        }
        Arrays.sort(intervals,new Comparator<int[]>(){
            @Override
            public int compare(int[] interval1, int[] interval2){
                return interval1[0] - interval2[0];
            }
        });
        List<int[]> res = new ArrayList<int[]>();
        for (int i = 0;i < l ; i++) {
            int a = intervals[i][0],b = intervals[i][1];
            if (res.size() == 0 || res.get(res.size() - 1)[1] < a) {
                res.add(new int[]{a,b});
            }else{
                res.get(res.size() - 1)[1] = Math.max(res.get(res.size() - 1)[1],b);
            }
        }
        return res.toArray(new int[res.size()][]);
    }

    // leetcode-57
    public int[][] insert(int[][] intervals, int[] newInterval) {
        int left = newInterval[0],right = newInterval[1];
        boolean placed = false;
        List<int[]> res = new ArrayList<int[]>();
        for (int[] interval : intervals) {
            if (interval[0] > right) {
                if (!placed) {
                    res.add(new int[]{left,right});
                    placed = true;
                }
                res.add(interval);
            }else if(interval[1] < left){
                res.add(interval);
            }else {
                left = Math.min(left,interval[0]);
                right = Math.max(right,interval[1]);
            }
        }
        if (!placed) {
            res.add(new int[]{left,right});
        }
        int[][] result = new int[res.size()][2];
        for (int i = 0;i < res.size() ;i++ ) {
            result[i] = res.get(i);
        }
        return result;
    }

    // leetcode-986
    public int[][] intervalIntersection(int[][] A, int[][] B) {
        List<int[]> res = new ArrayList<int[]>();
        int i = 0,j = 0;
        while(i < A.length && j < B.length){
            int left = Math.max(A[i][0],B[j][0]);
            int right = Math.min(A[i][1],B[j][1]);
            if (left <= right) {
                res.add(new int[]{left,right});
            }
            if (A[i][1] < B[j][1]) {
                i++;
            }else{
                j++;
            }
        }
        return res.toArray(new int[res.size()][]);
    }

}
