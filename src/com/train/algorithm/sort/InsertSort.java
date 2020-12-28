package com.train.algorithm.sort;

import java.util.Arrays;

public class InsertSort {
    public static void insertSort(int[] arr){
        int i,j,insertNote;
        for (i = 1;i < arr.length;i++){
            insertNote = arr[i];
            j = i - 1;
            while (j >= 0 && insertNote < arr[j]){
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = insertNote;
        }
    }

    public static void main(String[] args) {
        int a[] = { 38,65,97,76,13,27,49 };
        insertSort(a);
        System.out.println(Arrays.toString(a));
    }
}
