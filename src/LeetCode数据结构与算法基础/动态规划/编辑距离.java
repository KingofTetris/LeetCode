package LeetCode数据结构与算法基础.动态规划;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author by KingOfTetris
 * @date 2024/8/22
 */
public class 编辑距离 {


    @Test
    public void test(){
        String s = "horse";
        String t = "rse";
        int minDistance = minDistance(s, t);
        System.out.println(minDistance);
    }
    /**
     * 给你两个单词 word1 和 word2， 请返回将 word1 转换成 word2 所使用的最少操作数  。
     *
     * 你可以对一个单词进行如下三种操作：
     *
     * 插入一个字符
     * 删除一个字符
     * 替换一个字符
     *
     * 搞了那么多，就是为了做这道题
     *
     * @param word1
     * @param word2
     * @return
     */
    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();

        //dp[i][j] 含义和 两个字符串的删除操作相同
        //dp[i][j] 表示以为word1[i-1]结尾的字符串最少要dp[i][j]次操作得到word2[j-1]

        /**
         * if(word1[i-1] == word2[j-1]) dp[i][j] = dp[i-1][j-1]
         * else
         *  增
         *  删
         *  //其实增删相当于同一种操作，
         *  * 1.增加/删除 word1[i - 1],最少操作次数为dp[i - 1][j] + 1
         * * 2.增加/删除 word2[j - 1],最少操作次数为dp[i][j - 1] + 1
         *
         *  dp[i][j] = min (dp[i-1][j] + 1 ,dp[i][j-1] + 1)
         *  3.替换，那么至少这个字符串的长度是一样的，只是最后一位不一致
         *  dp[i][j] = dp[i-1][j-1] + 1,把word1[i-1] 替换成 word2[j-1]，或者反过来
         *  总之操作就是一次
         */

        int[][] dp = new int[m + 1][n + 1];
        // 初始化
        for (int i = 1; i <= m; i++) {
            dp[i][0] =  i;
        }
        for (int j = 1; j <= n; j++) {
            dp[0][j] = j;
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j - 1], dp[i][j - 1]),
                            dp[i - 1][j]) + 1;
                }
            }
        }

        for (int[] row : dp) {
            System.out.println(Arrays.toString(row));
        }
        return dp[m][n];
    }

}
