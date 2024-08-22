package LeetCode数据结构与算法基础.动态规划;

/**
 * @author by KingOfTetris
 * @date 2024/8/22
 */
public class 最长重复子序列 {


    /**
     * 这题和子数组的区别就是，这题变成子序列，可以不连续了。
     *
     * @param text1
     * @param text2
     * @return
     */
    public int longestCommonSubsequence(String text1, String text2) {

        //首先DP的含义
        /**
         * dp[i][j]：长度为[0, i - 1]的字符串text1
         * 与长度为[0, j - 1]的字符串text2的最长公共子序列为dp[i][j]
         *
         * 这种公共子序列，子串都是这样定义，避免DP初始化麻烦
         */
        char[] char1 = text1.toCharArray();
        char[] char2 = text2.toCharArray();
        // 可以在一開始的時候就先把text1, text2 轉成char[]，之後就不需要有這麼多爲了處理字串的調整
        // 就可以和卡哥的code更一致
        int[][] dp = new int[text1.length() + 1][text2.length() + 1]; // 先对dp数组做初始化操作

        //初始化值其实无所谓，全是0，可以被公式覆盖

        //遍历顺序从1 ，1 开始
        for (int i = 1; i <= text1.length(); i++) {
            for (int j = 1; j <= text2.length(); j++) {
                //如果结尾字符相同 dp[i - 1][j - 1] + 1;
                if (char1[i - 1] == char2[j - 1]) { // 开始列出状态转移方程
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }
                //如果不同，那么就要取 Math.max(dp[i - 1][j], dp[i][j - 1]);
                /**
                 * 举例子看看
                 * 比如
                 * abc
                 * ace
                 *
                 * 显然结尾 'c' 和 'e'不同
                 */
                else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[text1.length()][text2.length()];
    }
}
