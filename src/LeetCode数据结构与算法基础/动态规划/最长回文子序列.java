package LeetCode数据结构与算法基础.动态规划;

/**
 * @author by KingOfTetris
 * @date 2024/08/22
 */
public class 最长回文子序列 {
    public int longestPalindromeSubseq(String s) {
        int len = s.length();

        /**
         * dp[i][j]：字符串s在[i, j]范围内最长的回文子序列的长度为dp[i][j]。
         */
        int[][] dp = new int[len][len];

        /**
         * 在判断回文子串的题目中，关键逻辑就是看s[i]与s[j]是否相同。
         *
         * //注意[i,j] 是往两边扩展，i在不断缩小，j在不断增大
         * 那么前一个区间应该是 [i + 1,j - 1]
         * 如果s[i]与s[j]相同，那么dp[i][j] = dp[i + 1][j - 1] + 2;
         *
         * 如果s[i]与s[j]不相同，
         * 说明s[i]和s[j]的同时加入 并不能增加[i,j]区间回文子序列的长度，
         * 那么分别加入s[i]、s[j]看看哪一个可以组成最长的回文子序列。
         *
         * 加入s[j]的回文子序列长度为dp[i + 1][j]。
         *
         * 加入s[i]的回文子序列长度为dp[i][j - 1]。
         *
         * 那么dp[i][j]一定是取最大的，即：dp[i][j] = max(dp[i + 1][j], dp[i][j - 1]);
         */
        for (int i = len - 1; i >= 0; i--) {
            dp[i][i] = 1;
            for (int j = i + 1; j < len; j++) {
                if (s.charAt(i) == s.charAt(j))
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                else
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
            }
        }
        return dp[0][len - 1];
    }
}
