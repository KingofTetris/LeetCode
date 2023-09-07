package LeetCode数据结构基础.day2.字符串;

/**
 * @author by KingOfTetris
 * @date 2023/9/4
 */

//给你两个单词 word1 和 word2，请你计算出将 word1 转换成 word2 所使用的最少操作数 。

/**
 * 你可以对一个单词进行如下三种操作：
 *
 * 插入一个字符
 *
 * 删除一个字符
 *
 * 替换一个字符
 *
 * //注意没有交换字符。交换等于修改两次。这题的编辑距离II就是加上交换这个条件。
 *
 * 示例 1：
 *
 * 输入：word1 = "horse", word2 = "ros"
 *
 * 输出：3
 *
 * 解释： horse -> rorse (将 'h' 替换为 'r') rorse -> rose (删除 'r') rose -> ros (删除 'e')
 *
 * 示例 2：
 *
 * 输入：word1 = "intention", word2 = "execution"
 *
 * 输出：5
 *
 * 解释： intention -> inention (删除 't') inention -> enention (将 'i' 替换为 'e') enention -> exention (将 'n' 替换为 'x') exention -> exection (将 'n' 替换为 'c') exection -> execution (插入 'u')
 *
 * 提示：
 *
 * 0 <= word1.length, word2.length <= 500
 * word1 和 word2 由小写英文字母组成
 *
 */
public class 编辑距离 {

    public static void main(String[] args) {
        String word1 = "horse";
        String word2 = "elephant";
        System.out.println(minDistance(word1, word2));
    }

    public static int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
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
                // 因为dp数组有效位从1开始
                // 所以当前遍历到的字符串的位置为i-1 | j-1
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j - 1], dp[i][j - 1]), dp[i - 1][j]) + 1;
                }
            }
        }
        return dp[m][n];
    }
}
