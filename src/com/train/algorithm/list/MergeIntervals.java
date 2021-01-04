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
            public int compare(int[] interval1,int[] interval2){
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
}
