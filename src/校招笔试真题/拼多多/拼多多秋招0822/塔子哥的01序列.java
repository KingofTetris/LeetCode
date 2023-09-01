package 校招笔试真题.拼多多.拼多多秋招0822;

import java.util.Scanner;

public class 塔子哥的01序列 {
    static int mod = (int) 1e9 + 7; // 取模数，用于防止结果过大
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // 输入 n，表示有 n 个 0 和 n 个 1
        int[][] dp = new int[2][n + 1]; // dp 数组用于存储状态信息
        dp[0][1] = 1; // 初始状态：以 0结尾的长度为 1 的合法序列有 1 种
        dp[1][1] = 1; // 初始状态：以 1结尾的长度为 1 的合法序列有 1 种
        for (int i = 2; i < dp[0].length; i++) {
            // 对于长度为 i 的序列，以 0 结尾的合法序列数
            //等于前一个状态的以 1 结尾的合法序列数
            dp[0][i] = dp[1][i - 1];
            // 对于长度为 i 的序列，
            //以 1 结尾的合法序列数
            //等于前一个状态的以 0 结尾的合法序列数加上以 1 结尾的合法序列数
            dp[1][i] = (dp[0][i - 1] + dp[1][i - 1]) % mod;
        }
        // 最终的结果为长度为 n 的序列中，以 0 和 1 结尾的合法序列数之和，对 mod 取余
        System.out.println((dp[0][n] + dp[1][n]) % mod);
    }
}
