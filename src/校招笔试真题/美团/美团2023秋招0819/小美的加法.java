package 校招笔试真题.美团.美团2023秋招0819;

import java.util.Scanner;

public class 小美的加法 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        long[] arr = new long[n];
        long max = 0;

        // 读取输入数组，并计算初始的数组元素和
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextLong();
            max += arr[i];
        }

        long x = 0;
        int y = 0;

        // 遍历数组，找到乘积最大的相邻两个元素
        for (int i = 0; i < n - 1; i++) { // 替换n-1个加号
            long temp = arr[i] * arr[i + 1];
            //记录最大的相邻乘积，和相邻下标
            if (temp > x) {
                x = temp;
                y = i;
            }
        }

        // 将数组中的加号替换为乘号，并计算新的数组元素和
        max = countNum(arr, y);

        System.out.print(max);
    }

    // 将数组中的指定位置处的加号替换为乘号，并计算新的数组元素和
    private static long countNum(long[] arr, int i) {
        long sum = 0;
        for (int j = 0; j < arr.length; j++) {
            if (j == i && j + 1 < arr.length) {
                sum += arr[j] * arr[j + 1]; //加上arr[i] * arr[j]
                j++; // 跳过下一个元素，因为它已经乘过了
            } else { //其他数字直接相加即可。
                sum = sum + arr[j];
            }
        }
        return sum;
    }
}
