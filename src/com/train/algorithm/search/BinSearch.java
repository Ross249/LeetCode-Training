package com.train.algorithm.search;

public class BinSearch {
    public static int binSearch(int[] srcArray,int key){
        int mid;
        int start = 0;
        int end = srcArray.length-1;
        while (start <= end){
            mid = (end-start)/2+start;
            if (key < srcArray[mid]){
                end = mid-1;
            }else if (key > srcArray[mid]){
                start = mid+1;
            }else {
                return mid;
            }
        }
        return -1;
    }

    public static int binSearch(int[] srcArray,int start,int end,int key){
        int mid = (end-start)/2 + start;
        if (srcArray[mid] == key){
            return mid;
        }
        if (start >= end){
            return -1;
        }else if (key > srcArray[mid]){
            return binSearch(srcArray, mid+1, end, key);
        }else if (key < srcArray[mid]){
            return binSearch(srcArray, start, mid-1, key);
        }
        return -1;
    }
}
