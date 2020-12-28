package com.train.algorithm.sort;

import java.util.Arrays;

public class ShellSort {
    public static void shellSort(int[] arr){
        int k = 0;
        int temp = 0;
        for (int i = arr.length / 2;i > 0;i /= 2){
            for (int j = i; j <arr.length;j++){
                temp = arr[j];
                for (k = j - i;k >= 0;k -= i){
                    if (temp < arr[k]){
                        arr[k + i] = arr[k];
                    }else {
                        break;
                    }
                }
                arr[k + i] = temp;
            }
        }
    }

    public static void main(String[] args) {
        int[] data = new int[] { 26, 53, 67, 48, 57, 13, 48, 32, 60, 50 };
        shellSort(data);
        System.out.println(Arrays.toString(data));
    }
}
