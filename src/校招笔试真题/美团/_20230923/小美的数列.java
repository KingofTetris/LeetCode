package 校招笔试真题.美团._20230923;

import java.util.Scanner;

/**
 * @author by KingOfTetris
 * @date 2023/9/23
 */
public class 小美的数列 {
    static int MOD = (int) 1e9 + 7;

    public static void main(String[] args) {
        long n = 4; // 前 n 项和
        long sum = calculateSum(n);
        System.out.println("前 " + n + " 项和为: " + sum);
    }
    public static long calculateSum(long n) {
        long sum = 0;
        long currentNumber = 1;
        long count = 1;

        while (n > 0) {
            long partialSum = calculatePartialSum(currentNumber, count);
            sum += partialSum;

            currentNumber += 2;
            count++;
            n--;
        }

        return sum;
    }

    private static long calculatePartialSum(long start, long count) {
        long end = start + count - 1;
        long partialSum = ((start + end) * count) / 2;
        return partialSum % MOD;
    }
}
