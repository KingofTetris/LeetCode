package 校招笔试真题.小米.小米2022秋招;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author by KingOfTetris
 * @date 2023/9/1
 */

/**
 * 问题描述:
 * 给定两个字符串str1和str2，输出两个字符串的最长公共子序列的长度。
 * 如果最长公共子序列为空，则返回"0"。目前给出的数据，仅仅会存在 一个最长的公共子序列
 * <p>
 * 输入样例: 1A2C3D4E56  A1B2345C6D
 * <p>
 * 子序列不要求是连续子串，但是要保证相对顺序
 * 输出 6 就是123456
 * <p>
 * 这种送分题你都不会做，当初不刷代码随想录。蠢啊！
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
        int n = s1.length;
        int m = s2.length;

        //求s1和s2的最长公共子序列
        //定义dp[i][j] 为以s1[i-1]为结尾和以s2[j-1]为结尾的字符串的最长公共子序列

        int[][] dp = new int[n + 1][m + 1];

        //递推公式其实都是老调重弹了
        //分两种情况
        /**
         * 1.s1[i-1] == s2[j-1]
         *  那么就直接dp[i][j] = dp[i-1][j-1] + 1;
         *
         * 2.s1[i-1] != s2[j-1]
         * dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1]);
         */
        //初始化
        //dp[i][j] 的递推方向是从第一行第一列推过来的，那么就要初始化第一行第一列
        //dp[0][j] 的含义就是 s1为空字符串，那么以s2[j-1]为结尾的字符串和空字符串的最长公共子序列长度应该是0
        //反过来dp[i][0] 也是一样 那么其实无需显示赋值了，为了方便，我还是写出来给你看
        for (int j = 0; j <= m; j++) {
            dp[0][j] = 0;
        }

        for (int i = 0; i <= n; i++) {
            dp[i][0] = 0;
        }

        int max = -1;
        //开始递推
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                //要注意这里，s1和s2是没有+1的
                //需要下标-1 才是0->n 0->m
                if (s1[i - 1] == s2[j - 1]){
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }
                else{
                    dp[i][j] = Math.max(dp[i][j - 1],dp[i - 1][j]);
                }
                max = Math.max(max,dp[i][j]);
            }
        }
        for (int[] row : dp) {
            System.out.println(Arrays.toString(row));
        }
        return max;
    }

}
