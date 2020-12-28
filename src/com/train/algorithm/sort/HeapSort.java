package com.train.algorithm.sort;

import java.util.Arrays;

public class HeapSort {
    private static void heapSort(int[] arr){
        // 将待排序的序列构建成一个大顶堆
        for (int i = arr.length / 2;i >= 0;i--){
            heapAdjust(arr,i,arr.length);
        }

        // 逐步将每个最大值的根节点与末尾元素交换，并且再调整二叉树，使其成为大顶堆
        for (int i = arr.length - 1;i > 0;i--){
            // 将堆顶记录和当前未经排序子序列的最后一个记录交换
            swap(arr,0,i);
            // 交换之后，需要重新检查堆是否符合大顶堆，不符合则要调整
            heapAdjust(arr,0,i);
        }
    }

    private static void heapAdjust(int[] arr,int i,int n){
        int child;
        int father;
        for (father = arr[i];leftChild(i) < n;i = child){
            child = leftChild(i);

            // 如果左子树小于右子树，则需要比较右子树和父节点
            if (child != n - 1 && arr[child] < arr[child + 1]){
                child++;
            }

            // 如果父节点小于孩子结点，则需要交换
            if (father < arr[child]){
                arr[i] = arr[child];
            }else {
                // 大顶堆结构未被破坏，不需要调整
                break;
            }
        }
        arr[i] = father;
    }

    private static int leftChild(int i){
        return 2 * i + 1;
    }

    private static void swap(int[] arr,int index1,int index2){
        int temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
    }

    public static void main(String[] args) {
        int[] arr = { 50, 10, 90, 30, 70, 40, 80, 60, 20 };
        System.out.println("排序之前：");
        System.out.println(Arrays.toString(arr));

        // 堆排序
        heapSort(arr);

        System.out.println();
        System.out.println("排序之后：");
        System.out.println(Arrays.toString(arr));
    }
}
