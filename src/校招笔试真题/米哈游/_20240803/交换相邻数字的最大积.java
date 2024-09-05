package 校招笔试真题.米哈游._20240803;

import java.util.*;

public class 交换相邻数字的最大积 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }
        //只能选相邻元素进行交换一次
        //那么nums[i]的乘积可以是
        //i-2 i-1 i i+1 i+2 这四种结果
        //但其实 i-2 * i ,i - 1 * i 这种情况会被包含 在前面i*i+1 i*i+2 (i=i-2)里面
        //比如3 6 5 10 1,实际上 3*1 3*5 就包含了 5 * 1 ,5 * 3这种情况
        //其实就没必要往前算，直接一直向后递推就行了。

        long res = Long.MIN_VALUE;

        //一遍 i* (i+1) 一遍 i * (i + 2)
        for (int i = 0; i < n - 1; i++) {
            res = Math.max(res, (long) nums[i] * nums[i + 1]);
        }
        for (int i = 0; i < n - 2; i++) {
            res = Math.max(res, (long) nums[i] * nums[i + 2]);
        }
        System.out.println(res);
    }
}
