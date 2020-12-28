package com.train.algorithm.sort;

public class QuickSort {
    public static void quickSort(int[] arr){
        qSort(arr,0,arr.length-1);
    }

    private static void qSort(int[] arr,int low,int high){
        if (low < high){
            int pivot = partition(arr, low, high);
            qSort(arr, low, pivot-1);
            qSort(arr, pivot-1, high);
        }
    }

    private static int partition(int[] arr,int low,int high){
        int pivot = arr[low];
        while (low < high){
            while (low < high && arr[high] >= pivot){
                --high;
            }
            arr[low] = arr[high];
            while (low < high && arr[low] <= pivot){
                ++low;
            }
            arr[high] = arr[low];
        }
        arr[low] = pivot;
        return low;
    }

}
