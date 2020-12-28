package com.train.algorithm.sort;

import java.util.Arrays;

public class RadixSort {
    public static void radixSort(int[] arr,int d){
        int n = 1;
        int k = 0;
        int len = arr.length;
        int[][] bucket = new int[10][len];
        int[] order = new int[len];
        while (n < d){
            for (int num:arr){
                int digit = (num / n) % 10;
                bucket[digit][order[digit]] = num;
                order[digit]++;
            }
            for (int i = 0; i < len;i++){
                if (order[i] != 0){
                    for (int j = 0;j < order[i];j++){
                        arr[k] = bucket[i][j];
                        k++;
                    }
                }
                order[i] = 0;
            }
            n*=10;
            k = 0;
        }
    }

    public static void main(String[] args) {
        int[] A=new int[]{73,22, 93, 43, 55, 14, 28, 65, 39, 81};
        radixSort(A, 100);
        System.out.println(Arrays.toString(A));
    }
}
