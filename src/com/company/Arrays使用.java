package com.company;

import java.util.Arrays;

public class Arrays使用 {
    int[] a = {1, 2, 3};
    int[] a1 = new int[]{1, 2, 3};
    int[] b = {2, 4, 6};
    int[] b1= new int[5];
    void test() {
        //1.boolean equals(int[] a,int[] a2);判断是否相等
        boolean isEqual = Arrays.equals(a, a1);
        System.out.println(isEqual);
        //2.String toString(int[] a)
        String str = Arrays.toString(a);
        System.out.println(str);
        //3.void fill(int[] a,int val) 用于初始化
        Arrays.fill(b,10);
        //4.void sort(int[] a)
        Arrays.sort(a);//底层是双端快速排序
        //5.int binarySearch(int[] a,int key)
        int temp = Arrays.binarySearch(a,2);//注意二分查找的数组一定要有序
        //6.数组赋值的新招
        System.arraycopy(a,0,a1,0,a.length);
    }

}
