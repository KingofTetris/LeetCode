package 校招笔试真题.小米.小米2022秋招;

import java.util.Scanner;

/**
 * @author by KingOfTetris
 * @date 2023/9/1
 */

/**
 * 问题描述:
 * 给定两个字符串str1和str2，输出两个字符串的最长公共子序列的长度。
 * 如果最长公共子序列为空，则返回"0"。目前给出的数据，仅仅会存在 一个最长的公共子序列
 *
 * 输入样例: 1A2C3D4E56  A1B2345C6D
 *
 * 子序列不要求是连续子串，但是要保证相对顺序
 * 输出 6 就是123456
 */
public class 最长公共子序列 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String s1 = sc.nextLine();
            String s2 = sc.nextLine();
            System.out.println(longestCommonSubsequence(s1, s2));
        }
    }

    public static int longestCommonSubsequence(String text1, String text2) {
        if (text1 == null || text2 == null || text1.length() == 0 || text2.length() == 0) {
            return 0;
        }
        char[] s1 = text1.toCharArray();
        char[] s2 = text2.toCharArray();
        int M = text1.length();
        int N = text2.length();
        int[][] dp = new int[M][N];
        dp[0][0] = s1[0] == s2[0] ? 1 : 0;
        for (int j = 1; j < N; j++) {
            dp[0][j] = s1[0] == s2[j] ? 1 : dp[0][j - 1];
        }
        for (int i = 1; i < M; i++) {
            dp[i][0] = s1[i] == s2[0] ? 1 : dp[i - 1][0];
        }
        for (int i = 1; i < M; i++) {
            for (int j = 1; j < N; j++) {
                int p1 = dp[i][j - 1];
                int p2 = dp[i - 1][j];
                int p3 = s1[i] == s2[j] ? (1 + dp[i - 1][j - 1]) : 0;
                dp[i][j] = Math.max(p1, Math.max(p2, p3));
            }
        }
        return dp[M - 1][N - 1];
    }

  /*  作者：shawnwb
    链接：https://www.nowcoder.com/exam/test/72970517/submission?examPageSource=Company&pid=39932559&testCallback=https%3A%2F%2Fwww.nowcoder.com%2Fexam%2Fcompany%3FcurrentTab%3Drecommand%26jobId%3D100%26keyword%3D%E5%B0%8F%E7%B1%B3%26selectStatus%3D0&testclass=%E8%BD%AF%E4%BB%B6%E5%BC%80%E5%8F%91
    来源：牛客网*/
}
