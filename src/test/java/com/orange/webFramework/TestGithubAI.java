package com.orange.webFramework;

import edu.emory.mathcs.backport.java.util.Arrays;

import java.util.ArrayList;

/**
 * @Package : com.orange.spring_boot2_web
 * @Author : yilantingfeng
 * @Date : 2022/4/4 2:22 上午
 * @Version : V1.0
 */
public class TestGithubAI {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        // 生成一个乱序的数组
        Integer[] arr = new Integer[10];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random(100);
        }
        ArrayList<Integer> oldArr = new ArrayList<Integer>(Arrays.asList(arr));
        System.out.println("原始数组："+oldArr);
        // 冒泡排序
        bubbleSort(arr);
        ArrayList<Integer> newArr = new ArrayList<Integer>(Arrays.asList(arr));
        System.out.println("冒泡排序："+newArr);
    }

    // 随机数
    public static int random(int max) {
        return (int) (Math.random() * max);
    }

    //冒泡排序
    public static void bubbleSort(Integer[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    //选择排序
    public static void selectSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[min]) {
                    min = j;
                }
            }
            if (min != i) {
                int temp = arr[i];
                arr[i] = arr[min];
                arr[min] = temp;
            }
        }
    }

    //插入排序
    public static void insertSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i];
            int j = i - 1;
            for (; j >= 0; j--) {
                if (arr[j] > temp) {
                    arr[j + 1] = arr[j];
                } else {
                    break;
                }
            }
            arr[j + 1] = temp;
        }
    }

    //希尔排序
    public static void shellSort(int[] arr) {
        int gap = arr.length / 2;
        while (gap > 0) {
            for (int i = gap; i < arr.length; i++) {
                int temp = arr[i];
                int j = i - gap;
                for (; j >= 0; j -= gap) {
                    if (arr[j] > temp) {
                        arr[j + gap] = arr[j];
                    } else {
                        break;
                    }
                }
                arr[j + gap] = temp;
            }
            gap /= 2;
        }
    }

    //快速排序
    public static void quickSort(int[] arr, int low, int high) {
        if (low >= high) {
            return;
        }
        int i = low;
        int j = high;
        int temp = arr[low];
        while (i < j) {
            while (i < j && arr[j] >= temp) {
                j--;
            }
        }
    }
}
