package 校招笔试真题.美团.美团2023秋招0819;

import java.util.Scanner;

/**
 * @author by KingOfTetris
 * @date 2023/8/25
 */


//给你一个原数组A
    //限制每个数都是正整数
    //请你构造一个新数组B。A和B每个位置上的值都不同，但是最后的和要一样
    //请问有多少种构造方法？
public class 小美的数组构造 {

    static int MOD = (int) (1e9 + 7);
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] A = new int[n];
        int sum = 0;
        for (int i = 0; i < n; i++) {
            A[i] = sc.nextInt();
            sum += A[i];
        }

    }

    private static int solve(int[] a) {
        return 0;
    }
}
